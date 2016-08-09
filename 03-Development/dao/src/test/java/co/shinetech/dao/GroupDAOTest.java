/**
 * GroupDAOTest.java
 */
package co.shinetech.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Group;
import co.shinetech.dto.User;

/**
 * @author Rodrigo
 * @since 07/2016
 */
public class GroupDAOTest {
	private static final DAOProvider<Group> groupDao = (DAOProvider<Group>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_GROUP);
	private static final DAOProvider<User> userDao = (DAOProvider<User>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_USER);
	private long userId;
	private long groupId;
	
	@Before
	public void setUp() throws PersistenceException {
		testCreateGroup();
	}
	
	public void testCreateGroup() throws PersistenceException {
		Group g1 = new Group(groupId = groupDao.nextId(),"Group 1");
		Group g2 = new Group(groupDao.nextId(),"Group 2");
		ArrayList<User> userList = new ArrayList<>();
		
		User u1 = new User(userId = userDao.nextId());
		u1.setLogin("Rodrigo");
		u1.setPassword(new String("Rodrigo").toCharArray());
		
		userDao.create(u1);
		userList.add(u1);
		g1.setUsers(userList);
		groupDao.create(g1);
		groupDao.create(g2);		
	}
	
	@Test
	public void testRetrieveGroup() throws PersistenceException {
		groupDao.retrieveAll().forEach(o->System.out.println(o));
		
		User u = userDao.retrieveByID(userId);
		Group g = groupDao.retrieveByID(groupId);
		
		assertTrue(u.equals(g.getUsers().get(0)));
	}
}
