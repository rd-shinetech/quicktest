/**
 * 
 */
package co.shinetech.service.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Activity;
import co.shinetech.service.ServiceProvider;

/**
 * @author Ricardo
 *
 */
public class ActivityService implements ServiceProvider<Activity> {
	@SuppressWarnings("unchecked")
	private static final DAOProvider<Activity> activityDao = (DAOProvider<Activity>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_ACTIVITY);

	public void create(Activity d) throws PersistenceException {
		activityDao.create(d);
		
	}

	public void update(Activity d) throws PersistenceException {
		activityDao.update(d);
		
	}

	public Activity retrieveByID(int ID) throws PersistenceException {
		return activityDao.retrieveByID(ID);
	}

	public List<Activity> retrieveAll() throws PersistenceException {
		return activityDao.retrieveAll();
	}

	public void delete(int ID) throws PersistenceException {
		activityDao.delete(ID);
		
	}

	public int count() throws PersistenceException {
		return activityDao.count();
	}

	public long nextId() throws PersistenceException {
		return activityDao.nextId();
	}

}
