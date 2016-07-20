/**
 * 
 */
package co.shinetech.dao.impl;

import java.util.ArrayList;

import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.db.SerializerDB;
import co.shinetech.dto.User;

/**
 * @author Robin
 *
 */
public class UserDAOImp implements PersistenceProvider<User>{

	@Override
	public void create(User user) throws PersistenceException {
		SerializerDB.insert(SerializerDB.TABLE_USER, user);
	}

	@Override
	public void update(User user) throws PersistenceException {
		SerializerDB.update(SerializerDB.TABLE_USER, user);
	}

	@Override
	public User retrieveByID(long ID) throws PersistenceException {
		return (User)SerializerDB.selectById(SerializerDB.TABLE_USER, ID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<User> retrieveAll() throws PersistenceException {
		return (ArrayList<User>)SerializerDB.selectAll(SerializerDB.TABLE_USER);
	}

	@Override
	public void delete(long ID) throws PersistenceException {
		SerializerDB.delete(SerializerDB.TABLE_USER, ID);	
	}

	@Override
	public int count() throws PersistenceException {
		return SerializerDB.count(SerializerDB.TABLE_USER);
	}
}
