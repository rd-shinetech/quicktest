/**
 * 
 */
package co.shinetech.dao.impl;

import java.util.ArrayList;

import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.db.SerializerDB;
import co.shinetech.dto.Profile;
import co.shinetech.dto.Domain;

/**
 * @author Gonxaluh
 *
 */
public class ProfileDAOImp implements PersistenceProvider<Profile>{

	@Override
	public void create(Profile d) throws PersistenceException {
		SerializerDB.insert(SerializerDB.TABLE_PROFILE, d);
		
	}

	@Override
	public void update(Profile d) throws PersistenceException {
		SerializerDB.update(SerializerDB.TABLE_PROFILE, d);
		
	}

	@Override
	public Profile retrieveByID(long ID) throws PersistenceException {
		Domain d = SerializerDB.selectById(SerializerDB.TABLE_PROFILE, ID);
		return (Profile) d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Profile> retrieveAll() throws PersistenceException {
		ArrayList<? extends Domain> r = SerializerDB.selectAll(SerializerDB.TABLE_PROFILE);
		return (ArrayList<Profile>) r;
	}

	@Override
	public void delete(long ID) throws PersistenceException {
		SerializerDB.delete(SerializerDB.TABLE_PROFILE, ID);
		
	}

	@Override
	public int count() throws PersistenceException {
		int c = SerializerDB.count(SerializerDB.TABLE_PROFILE);
		return (int) c;
	}

}
