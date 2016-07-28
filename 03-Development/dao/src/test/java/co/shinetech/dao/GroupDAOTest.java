/**
 * 
 */
package co.shinetech.dao;

import org.junit.Test;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Group;

/**
 * @author usuario
 *
 */
public class GroupDAOTest {
	private static final DAOProvider<Group> groupDao = (DAOProvider<Group>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_GROUP);

	@Test
	public void testCreateGroup() throws PersistenceException {
		Group g1 = new Group(groupDao.nextId(),"Group 1");
		Group g2 = new Group(groupDao.nextId(),"Group 2");
		
		groupDao.create(g1);
		groupDao.create(g2);		
	}
}
