package co.shinetech.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.SerializerDBProviderFactory;
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
	private static PersistenceProvider<User> userDao;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() throws PersistenceException {
		userDao = SerializerDBProviderFactory
						.getPersistenceProvider(SerializerDBProviderFactory
							.TABLE_USER);
		id = userDao.nextId();
		login = "Robin";
		pwd = "1b".toCharArray();
		pwd2 = "5gjs2".toCharArray();
		profile = new Profile(1, "Student");
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
		ArrayList<User> l = userDao.retrieveAll();
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
