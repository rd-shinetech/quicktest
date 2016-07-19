/**
 * 
 */
package co.shinetech.dao;

import java.util.ArrayList;

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
	public User retrieveByID(int ID) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> retrieveAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int ID) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}

}
