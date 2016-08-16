/**
 * 
 */
package co.shinetech.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.ActivityArea;
import co.shinetech.dto.User;

/**
 * @author Ricardo
 * @since 2016-08
 *
 */
public class ActivityAreaDAOTest {
	@SuppressWarnings("unchecked")
	private static final DAOProvider<ActivityArea> activityAreaDao = (DAOProvider<ActivityArea>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_ACTIVITY_AREA);
	private long id;
	
	@Before
	public void setup() throws PersistenceException {
		testCreateUser();
	}
	
	public void testCreateUser() throws PersistenceException {
		ArrayList<ActivityArea> userList = new ArrayList<>();
		ActivityArea a1 = new ActivityArea(id = activityAreaDao.nextId());
		a1.setName("Activity Test");
		
		activityAreaDao.create(a1);
		userList.add(a1);		
	}
/*	
	@Test
	public void testUpdateUser() throws PersistenceException {
		if (!userExists())
			testCreateUser();
		User u1 = userDao.retrieveByID(id);
		userDao.update(u1);
		assertTrue(userExists());
	}
	
	@Test
	public void testDeleteUser() throws PersistenceException {
		if (!userExists())
			testCreateUser();
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
			testCreateUser();
		User u1 = userDao.retrieveByID(id);
		assertEquals(u, u1);
	}

	public void nextUserId() throws PersistenceException{
		long n = userDao.nextId();
		assertTrue(n > id);
	}
	
	private boolean userExists() throws PersistenceException {
		return userDao.retrieveByID(id) != null;
	}
*/
}
