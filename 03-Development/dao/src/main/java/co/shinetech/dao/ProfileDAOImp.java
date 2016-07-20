/**
 * 
 */
package co.shinetech.dao;

import java.util.ArrayList;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.db.SerializerDB;
import co.shinetech.dto.Profile;
import co.shinetech.dto.Domain;

/**
 * @author Gonxaluh
 *
 */
public class ProfileDAOImp implements PersistenceProvider<Profile>{

	/**
	 * 
	 */
	public ProfileDAOImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Profile d) throws PersistenceException {
		SerializerDB.insert(SerializerDB.TABLE_ACTIVITY, d);
		
	}

	@Override
	public void update(Profile d) throws PersistenceException {
		SerializerDB.update(SerializerDB.TABLE_ACTIVITY, d);
		
	}

	@Override
	public Profile retrieveByID(int ID) throws PersistenceException {
		Domain d = SerializerDB.selectById(SerializerDB.TABLE_ACTIVITY, ID);
		return (Profile) d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Profile> retrieveAll() throws PersistenceException {
		ArrayList<? extends Domain> r = SerializerDB.selectAll(SerializerDB.TABLE_ACTIVITY);
		return (ArrayList<Profile>) r;
	}

	@Override
	public void delete(int ID) throws PersistenceException {
		SerializerDB.delete(SerializerDB.TABLE_ACTIVITY, ID);
		
	}

	@Override
	public int count() throws PersistenceException {
		int c = SerializerDB.count(SerializerDB.TABLE_ACTIVITY);
		return (int) c;
	}

}
