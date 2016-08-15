/**
 * ActivityAreaService.java
 */
package co.shinetech.service.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.ActivityArea;
import co.shinetech.service.ServiceProvider;

/**
 * Service class for Activity Area entity.
 * @author Rodrigo
 * @since 15/8/2016
 * @version 1.0
 */
public class ActivityAreaService implements ServiceProvider<ActivityArea> {
	@SuppressWarnings("unchecked")
	private static final DAOProvider<ActivityArea> activityAreaDao = (DAOProvider<ActivityArea>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_ACTIVITY_AREA);

	public void create(ActivityArea aa) throws PersistenceException {
		activityAreaDao.create(aa);
	}

	public void update(ActivityArea aa) throws PersistenceException {
		activityAreaDao.update(aa);
	}

	public ActivityArea retrieveByID(int ID) throws PersistenceException {
		return activityAreaDao.retrieveByID(ID);
	}

	public List<ActivityArea> retrieveAll() throws PersistenceException {
		return activityAreaDao.retrieveAll();
	}

	public void delete(int ID) throws PersistenceException {
		activityAreaDao.delete(ID);
	}

	public int count() throws PersistenceException {
		return activityAreaDao.count();
	}

	public long nextId() throws PersistenceException {
		return activityAreaDao.nextId();
	}
}
