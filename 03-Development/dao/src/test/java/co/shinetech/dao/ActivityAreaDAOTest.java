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
		testCreateActivityArea();
	}
	
	public void testCreateActivityArea() throws PersistenceException {
		ArrayList<ActivityArea> activityAreaList = new ArrayList<>();
		ActivityArea a1 = new ActivityArea(id = activityAreaDao.nextId());
		a1.setName("Activity Test");
		
		activityAreaDao.create(a1);
		activityAreaList.add(a1);		
	}

	@Test
	public void testUpdateActivityArea() throws PersistenceException {
		if (!activityAreaExists())
			testCreateActivityArea();
		ActivityArea a1 = activityAreaDao.retrieveByID(id);
		activityAreaDao.update(a1);
		assertTrue(activityAreaExists());
	}
	
	@Test
	public void testDeleteActivityArea() throws PersistenceException {
		if (!activityAreaExists())
			testCreateActivityArea();
		int a = activityAreaDao.count();
		activityAreaDao.delete(id);
		int b = activityAreaDao.count();
		assertTrue(b < a);
	}
	
	@Test
	public void retriveAllActivityArea() throws PersistenceException {
		List<ActivityArea> l = activityAreaDao.retrieveAll();
		int a = activityAreaDao.count();
		assertEquals(l.size(), a);
	}

	@Test
	public void retriveByIdActivityArea() throws PersistenceException {
		if (!activityAreaExists())
			testCreateActivityArea();
		ActivityArea a1 = activityAreaDao.retrieveByID(id);
		assertEquals(id, a1);
	}

	public void nextId() throws PersistenceException{
		long n = activityAreaDao.nextId();
		assertTrue(n > id);
	}
	
	private boolean activityAreaExists() throws PersistenceException {
		return activityAreaDao.retrieveByID(id) != null;
	}
}
