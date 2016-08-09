package co.shinetech.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Profile;
import co.shinetech.dto.User;

/**
 * 
 * @author Robin
 *
 */
public class UserDAOTest {
	private static long id;
	private static String login;
	private static char[] pwd;
	private static char[] pwd2;
	private static Profile profile;
	private static User u;
	private static DAOProvider<User> userDao;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setup() throws PersistenceException {
		userDao = (DAOProvider<User>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_USER);
		DAOProvider<Profile> profileDao = (DAOProvider<Profile>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_PROFILE);
		id = userDao.nextId();
		login = "Robin " + id;
		pwd = "1b".toCharArray();//new Character[] {'1', 'b'};
		pwd2 = "5gjs2".toCharArray();
		long pid = profileDao.nextId();
		profile = new Profile(pid, "Student " + pid);
		profileDao.create(profile);
		u = new User(id, login, pwd, profile);
	}

	@Test
	public void createUser() throws PersistenceException {
		if (!userExists()) {
			int a = userDao.count();
			userDao.create(u);
			int b = userDao.count();
			assertTrue(b > a);
		}
	}

	@Test
	public void updateUser() throws PersistenceException {
		if (!userExists())
			createUser();
		User u2 = userDao.retrieveByID(id);
		u2.setPassword(pwd2);
		userDao.update(u2);
		assertTrue(userExists());
	}

	@Test
	public void deleteUser() throws PersistenceException {
		if (!userExists())
			createUser();
		int a = userDao.count();
		userDao.delete(id);
		int b = userDao.count();
		assertTrue(b < a);
	}

	@Test
	public void retriveAllUsers() throws PersistenceException {
		List<User> l = userDao.retrieveAll();
		int a = userDao.count();
		assertEquals(l.size(), a);
	}

	@Test
	public void retriveByIdUser() throws PersistenceException {
		if (!userExists())
			createUser();
		User u2 = userDao.retrieveByID(id);
		assertEquals(u, u2);
	}

	public void nextUserId() throws PersistenceException{
		long n = userDao.nextId();
		assertTrue(n > id);
	}

	private boolean userExists() throws PersistenceException {
		return userDao.retrieveByID(id) != null;
	}
}
