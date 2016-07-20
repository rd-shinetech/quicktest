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
	private static final String login = "Robin";
	private static final char[] pwd = {'1', 'b'};
	private static final char[] pwd2 = {'5', 'g', 'b'};
	private static final Profile profile = new Profile(1, "Student");
	private static final User u = new User(id, login, pwd, profile);
	@SuppressWarnings("unchecked")
	private static final PersistenceProvider<User> userDao = SerializerDBProviderFactory
	.getPersistenceProvider(SerializerDBProviderFactory
		.TABLE_USER);
	 
	@Before
	public void setup() throws PersistenceException {
		id = userDao.nextId();
	}
	
	@Test
	public void createUser() throws PersistenceException {
		int a = userDao.count();
		userDao.create(u);
		int b = userDao.count();
		assertTrue(b > a);
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
