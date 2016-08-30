/**
 * 
 */
package co.shinetech.dao.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.db.SerializerDB;
import co.shinetech.dto.Question;

/**
 * @author Ricardo
 * @since 2016-08
 *
 */
public class QuestionDAOImp implements DAOProvider<Question> {
	
	@Override
	public void create(Question q) throws PersistenceException {
		SerializerDB.insert(SerializerDB.TABLE_QUESTION, q);
	}

	@Override
	public void update(Question q) throws PersistenceException {
		SerializerDB.update(SerializerDB.TABLE_QUESTION, q);		
	}

	@Override
	public Question retrieveByID(long ID) throws PersistenceException {
		return (Question)SerializerDB.selectById(SerializerDB.TABLE_QUESTION, ID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> retrieveAll() throws PersistenceException {
		return (List<Question>)SerializerDB.selectAll(SerializerDB.TABLE_QUESTION);			
	}

	@Override
	public void delete(long ID) throws PersistenceException {
		SerializerDB.delete(SerializerDB.TABLE_QUESTION, ID);
	}

	@Override
	public int count() throws PersistenceException {
		return SerializerDB.count(SerializerDB.TABLE_QUESTION);
	}

	@Override
	public long nextId() throws PersistenceException {
		return SerializerDB.nextId(SerializerDB.TABLE_QUESTION);
	}
}
