package co.shinetech.dao;

import org.junit.Test;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.SerializerDBProviderFactory;
import co.shinetech.dto.User;

public class UserDAOTest {
	@SuppressWarnings({ "unused", "unchecked" })
	@Test
	public void createUser() throws PersistenceException {
		PersistenceProvider<User> userDao = SerializerDBProviderFactory.getPersistenceProvider(SerializerDBProviderFactory.TABLE_USER);
		User u = new User(1);
		//u.set
		
		//userDao.create(u);
	}
}
