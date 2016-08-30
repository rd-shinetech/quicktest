/*
* SerializerDB.java
*/
package co.shinetech.dao.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import co.shinetech.dto.Activity;
import co.shinetech.dto.ActivityArea;
import co.shinetech.dto.Domain;
import co.shinetech.dto.Group;
import co.shinetech.dto.Profile;
import co.shinetech.dto.User;

/**
 * Class that implements Faceter's database.
 * @author Rodrigo
 * @since 2015-06-24
 */
public class SerializerDB {
    // Data Storage
	private final static HashMap<Long,Activity> activityData;
	private final static HashMap<Long,ActivityArea> activityAreaData;
    private final static HashMap<Long,Profile> profileData;
    private final static HashMap<Long,User> userData;
    private final static HashMap<String,Long> idControlMapData;
    private final static HashMap<Long, Group> groupData;
    
    // Table Storage
    private static final HashMap<String,HashMap> tablesMap;
        
    // Table Constants
    public static final String TABLE_ACTIVITY = "Activity";
    public static final String TABLE_PROFILE  = "Profile";
    public static final String TABLE_USER = "User";
    public static final String TABLE_ID_CONTROL = "Id_Control";
    public static final String TABLE_GROUP = "Group";
    public static final String TABLE_ACTIVITY_AREA = "Activity_Area";
    public static final String TABLE_QUESTION = "Question";
    
    public static final String TABLE_EXTENSION  = "qtest.db";
    public static String DB_PATH;
    
    static {
        try {
            try (InputStream fis = SerializerDB.class.getClassLoader().getResourceAsStream("qtest.properties")) {
                Properties p = new Properties();
                
                p.load(fis);
                DB_PATH = System.getProperty("user.home")+p.getProperty("databasePath");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
        activityData = new HashMap<>();
        activityAreaData = new HashMap<>();
        profileData = new HashMap<>();
        userData = new HashMap<>();
        idControlMapData = new HashMap<>();
        groupData = new HashMap<>();
        tablesMap = new HashMap<>();
        // Fill ID control table starting with zeros
        idControlMapData.put(TABLE_ACTIVITY,0L);
        idControlMapData.put(TABLE_PROFILE, 0L);
        idControlMapData.put(TABLE_USER, 0L);
        idControlMapData.put(TABLE_GROUP, 0L);
        idControlMapData.put(TABLE_ACTIVITY_AREA, 0L);
        
        tablesMap.put(TABLE_ACTIVITY, activityData);
        tablesMap.put(TABLE_ID_CONTROL,idControlMapData);
        tablesMap.put(TABLE_PROFILE, profileData);
        tablesMap.put(TABLE_USER, userData);
        tablesMap.put(TABLE_GROUP, groupData);
        tablesMap.put(TABLE_ACTIVITY_AREA, activityAreaData);
        load();
    }

    @SuppressWarnings("unchecked")
    private static void load() {
        FileInputStream fi;
        ObjectInputStream oi;
        Object o;
        File dir = new File(DB_PATH);
        File f;
        
        if( ! dir.exists() ) {
        	dir.mkdirs();
        }
        
        f = new File(DB_PATH + "/" + TABLE_EXTENSION);
        if( ! f.exists() )
        	return;
        try {
			fi = new FileInputStream(f);
	        oi = new ObjectInputStream(fi);
	        o = oi.readObject();
	        if( o != null )
	        	tablesMap.putAll((Map<? extends String, ? extends HashMap>) o);

        } catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
    }
    
    private static void write(String tableName) throws PersistenceException {
        FileOutputStream fo;
        ObjectOutputStream oo;
        File dir = new File(DB_PATH);
        
        try {
            if( ! dir.exists() )
                dir.mkdirs();
            fo = new FileOutputStream(DB_PATH +"/"+TABLE_EXTENSION);
            oo = new ObjectOutputStream(fo);
            oo.writeObject(tablesMap);
            oo.flush();
            oo.close();
            fo.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new PersistenceException("Error writting data to database.");
        }
    }
            
    @SuppressWarnings("unchecked")
    public static synchronized long nextId(String table) throws PersistenceException {
		HashMap<String,Long> idControl = tablesMap.get(TABLE_ID_CONTROL);
        Long id = idControl.get(table);
        
        idControl.put(table,++id);
        write(TABLE_ID_CONTROL);
        return id;
    }
    
    @SuppressWarnings("unchecked")
	public static void insert(String table,Domain value) throws PersistenceException {
        if ( tablesMap.get(table).containsKey(value.getPk()) )
        	throw new PersistenceException("Object " + table + ".ID=" + value.getPk() + " exists in database");
    	tablesMap.get(table).put(value.getPk(), value);
        write(table);
    }
    
    @SuppressWarnings("unchecked")
    public static void update(String table,Domain value) throws PersistenceException {
        Domain d = (Domain) tablesMap.get(table).get(value.getPk());
        
        if( d == null )
            throw new PersistenceException("Error: Object "+table+".ID="+value.getPk()+" not found in database.");
        
        tablesMap.get(table).put(value.getPk(),value);
        write(table);
    }
    
    public static void delete(String table,long id) throws PersistenceException {
        Domain d = (Domain) tablesMap.get(table).get(id);

        if( d == null ){
            throw new PersistenceException("Object "+table+".ID="+id+" not found in database.");        	
        }
        if( ! checkIntegrity(table,id) ) {
        	throw new PersistenceException("Referencial integrity violation. The selected item is being used in other entity.");
        }
        tablesMap.get(table).remove(id);
        write(table);
    }
    
    @SuppressWarnings("unchecked")
    public static ArrayList<? extends Domain> selectAll(String table) {
        ArrayList<? extends Domain> r = new ArrayList<>();
        
        r.addAll( tablesMap.get(table).values() );
        
        return r;
    }
    
    public static Domain selectById(String table,long ID) {
        Domain d = (Domain) tablesMap.get(table).get(ID);

        return d;
    }
    
    public static int count(String table) {
        return tablesMap.get(table).values().size();
    }
    
    /**
     * Method to check database integrity.
     * @param table
     * @param ID
     * @return
     */
    private static boolean checkIntegrity(String table,long id) {
    	boolean r = true;
    	
    	switch( table ) { 
    		case TABLE_PROFILE: {
    			HashMap<Long,User> users = tablesMap.get(TABLE_USER);
    			
    			for( User u : users.values() ) {
    				if( u.getProfile().getPk() == id ) {
    					r = false;
    					break;
    				}
    			}
    			break;
    		}
    	}
    	
    	return r;
    }
}