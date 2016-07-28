/**
 * ClassService.java
 */
package co.shinetech.service.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Group;
import co.shinetech.service.ServiceProvider;

/**
 * This class supply class
 * @author Rodrigo
 */
public class GroupService implements ServiceProvider<Group> {
	private static final DAOProvider<Group> groupDao = (DAOProvider<Group>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_GROUP);
	
	public void create(Group d) throws PersistenceException {
		groupDao.create(d);
	}

	public void update(Group d) throws PersistenceException {
		groupDao.update(d);
	}

	public Group retrieveByID(int ID) throws PersistenceException {
		return groupDao.retrieveByID(ID);
	}

	public List<Group> retrieveAll() throws PersistenceException {
		return groupDao.retrieveAll();
	}

	public void delete(int ID) throws PersistenceException {
		groupDao.delete(ID);
	}

	public int count() throws PersistenceException {
		return groupDao.count();
	}

	public long nextId() throws PersistenceException {
		return groupDao.nextId();
	}
}