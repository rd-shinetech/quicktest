/**
 * 
 */
package co.shinetech.dao.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.db.SerializerDB;
import co.shinetech.dto.ActivityArea;

/**
 * @author Ricardo
 * @since 2016-08
 */
public class ActivityAreaDAOImp implements DAOProvider<ActivityArea> {
	
	public void create(ActivityArea aa) throws PersistenceException {
		SerializerDB.insert(SerializerDB.TABLE_ACTIVITY_AREA, aa);
	}
	
	public void update(ActivityArea aa) throws PersistenceException {
		SerializerDB.update(SerializerDB.TABLE_ACTIVITY_AREA, aa);
	}
	
	public ActivityArea retrieveByID(long ID) throws PersistenceException {
		return (ActivityArea)SerializerDB.selectById(SerializerDB.TABLE_ACTIVITY_AREA, ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityArea> retrieveAll() throws PersistenceException {
		return (List<ActivityArea>)SerializerDB.selectAll(SerializerDB.TABLE_ACTIVITY_AREA);
	}
	
	public void delete(long ID) throws PersistenceException {
		SerializerDB.delete(SerializerDB.TABLE_ACTIVITY_AREA, ID);
	}
	
	public int count() throws PersistenceException {
		return SerializerDB.count(SerializerDB.TABLE_ACTIVITY_AREA);
	}

	@Override
	public long nextId() throws PersistenceException {
		return SerializerDB.nextId(SerializerDB.TABLE_ACTIVITY_AREA);
	}
}
