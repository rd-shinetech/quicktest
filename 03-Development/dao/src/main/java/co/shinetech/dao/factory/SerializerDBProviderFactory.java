/*
 * SerializerDBProviderFactory.java
 */
package co.shinetech.dao.factory;

import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.impl.ActivityDAOImp;
import co.shinetech.dao.impl.UserDAOImp;

/**
 * @author Rodrigo
 * @since 2015-07-07
 */
public class SerializerDBProviderFactory {
	public static final String TABLE_USER = "User";
	public static final String TABLE_ACTIVITY = "Activity";
            
    @SuppressWarnings("rawtypes")
    public static PersistenceProvider getPersistenceProvider(String table) {
        PersistenceProvider pv = null;

        if( table.equals(TABLE_USER) ) {        
            if( PersistenceProviderAbstractFactory.TABLE_USER.equals(table) )
                pv = new UserDAOImp();
        } else if( table.equals(TABLE_ACTIVITY) ){
        	if( PersistenceProviderAbstractFactory.TABLE_ACTIVITY.equals(table))
        		pv = new ActivityDAOImp();
        }
        return pv;
    }
}