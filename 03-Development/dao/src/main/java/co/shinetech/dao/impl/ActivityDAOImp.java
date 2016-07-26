/**
 * 
 */
package co.shinetech.dao.impl;

import java.util.ArrayList;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.db.SerializerDB;
import co.shinetech.dto.Activity;
import co.shinetech.dto.Domain;

/**
 * @author Ricardo
 * 19/07/2016
 *
 */
public class ActivityDAOImp implements DAOProvider<Activity> {

	@Override
	public void create(Activity d) throws PersistenceException {
		SerializerDB.insert(SerializerDB.TABLE_ACTIVITY, d);
		
	}

	@Override
	public void update(Activity d) throws PersistenceException {
		SerializerDB.update(SerializerDB.TABLE_ACTIVITY, d);
		
	}

	@Override
	public Activity retrieveByID(long ID) throws PersistenceException {
		Domain d = SerializerDB.selectById(SerializerDB.TABLE_ACTIVITY, ID);
		return (Activity) d;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Activity> retrieveAll() throws PersistenceException {
		ArrayList<? extends Domain> r = SerializerDB.selectAll(SerializerDB.TABLE_ACTIVITY);
		return (ArrayList<Activity>) r;
	}

	@Override
	public void delete(long ID) throws PersistenceException {
		SerializerDB.delete(SerializerDB.TABLE_ACTIVITY, ID);
		
	}

	@Override
	public int count() throws PersistenceException {
		int c = SerializerDB.count(SerializerDB.TABLE_ACTIVITY);
		return c;
	}

	@Override
	public long nextId() throws PersistenceException {
		long l = SerializerDB.nextId(SerializerDB.TABLE_ACTIVITY);
		return l;
	}

}
