package co.shinetech.service.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Profile;
import co.shinetech.service.ServiceProvider;

public class ProfileService implements ServiceProvider<Profile>{
	@SuppressWarnings("unchecked")
	private static final DAOProvider<Profile> profilDao = (DAOProvider<Profile>) 
					PersistenceProviderFactory.getPersistenceProvider(
						PersistenceProviderFactory.PERSISTENCE_SERIALIZED)
					.getDAOProvider(PersistenceProvider.TABLE_PROFILE);
	
	public void create(Profile d) throws PersistenceException {
		profilDao.create(d);
	}

	public void update(Profile d) throws PersistenceException {
		profilDao.update(d);
	}

	public Profile retrieveByID(int ID) throws PersistenceException {
		return profilDao.retrieveByID(ID);
	}

	public List<Profile> retrieveAll() throws PersistenceException {
		return profilDao.retrieveAll();
	}

	public void delete(int ID) throws PersistenceException {
		profilDao.delete(ID);
	}

	public int count() throws PersistenceException {
		return profilDao.count();
	}

	public long nextId() throws PersistenceException {
		return profilDao.nextId();
	}

}
