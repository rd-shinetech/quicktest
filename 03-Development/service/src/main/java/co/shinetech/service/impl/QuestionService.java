/**
 * 
 */
package co.shinetech.service.impl;

import java.util.List;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Question;
import co.shinetech.service.ServiceProvider;

/**
 * Service class for Question entity.
 * @author Ricardo
 * @since 2016-08
 *
 */
public class QuestionService implements ServiceProvider<Question> {
	
	@SuppressWarnings("unchecked")
	private static final DAOProvider<Question> questionDao = (DAOProvider<Question>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_QUESTION);

	public void create(Question q) throws PersistenceException {
		questionDao.create(q);		
	}

	public void update(Question q) throws PersistenceException {
		questionDao.update(q);		
	}

	public Question retrieveByID(int ID) throws PersistenceException {
		return questionDao.retrieveByID(ID);
	}

	public List<Question> retrieveAll() throws PersistenceException {
		return questionDao.retrieveAll();
	}

	public void delete(int ID) throws PersistenceException {
		questionDao.delete(ID);		
	}

	public int count() throws PersistenceException {
		return questionDao.count();
	}

	public long nextId() throws PersistenceException {
		return questionDao.nextId();
	}

}
