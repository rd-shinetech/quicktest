package co.shinetech.dao;

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
	private static final long id = 2;
	private static final String login = "Robin";
	private static final char[] pwd = {'1', 'b'};
	private static final char[] pwd2 = {'5', 'g', 'b'};
	private static final Profile profile = new Profile(1, "Student");
	@SuppressWarnings("unchecked")
	private static final PersistenceProvider<User> userDao = SerializerDBProviderFactory
	.getPersistenceProvider(SerializerDBProviderFactory
		.TABLE_USER);
	 
	@Before
	public void setup() {
		
	}
	
	@Test
	public void createUser() throws PersistenceException {
		User u = new User(id, login, pwd, profile);
		userDao.create(u);
	}

	@Test
	public void updateUser() throws PersistenceException {
		if (!userExists())
			createUser();
		User u = userDao.retrieveByID(id);
		u.setPassword(pwd2);
		userDao.update(u);
	}

	@Test
	public void deleteUser() throws PersistenceException {
		if (!userExists())
			createUser();
		userDao.delete(id);
	}
	
	@Test
	

	private boolean userExists() throws PersistenceException {
		return userDao.retrieveByID(id) != null;
	}
}
