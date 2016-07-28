/**
 * 
 */
package co.shinetech.service.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.User;
import co.shinetech.service.ServiceProvider;

/**
 * @author Robin
 *
 */
public class UserService implements ServiceProvider<User>{
	@SuppressWarnings("unchecked")
	private static final DAOProvider<User> userDao = (DAOProvider<User>) 
					PersistenceProviderFactory.getPersistenceProvider(
						PersistenceProviderFactory.PERSISTENCE_SERIALIZED)
					.getDAOProvider(PersistenceProvider.TABLE_USER);
	
	public void create(User d) throws PersistenceException {
		userDao.create(d);
	}

	public void update(User d) throws PersistenceException {
		userDao.update(d);
	}

	public User retrieveByID(int ID) throws PersistenceException {
		return userDao.retrieveByID(ID);
	}

	public List<User> retrieveAll() throws PersistenceException {
		return userDao.retrieveAll();
	}

	public void delete(int ID) throws PersistenceException {
		userDao.delete(ID);
	}

	public int count() throws PersistenceException {
		return userDao.count();
	}

	public long nextId() throws PersistenceException {
		return userDao.nextId();
	}

}
