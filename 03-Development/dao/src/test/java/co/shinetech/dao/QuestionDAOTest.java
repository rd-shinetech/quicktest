/**
 * QuestionDAOTest.java
 */
package co.shinetech.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dao.factory.PersistenceProviderFactory;
import co.shinetech.dto.Question;

/**
 * @author Rodrigo
 * @since 07/2016
 */
public class QuestionDAOTest {
	private static final DAOProvider<Question> questionDao = (DAOProvider<Question>) PersistenceProviderFactory.getPersistenceProvider(PersistenceProviderFactory.PERSISTENCE_SERIALIZED).getDAOProvider(PersistenceProvider.TABLE_QUESTION);
	private long questionId;
	
	@Before
	public void setUp() throws PersistenceException {
		testCreateQuestion();
	}
	
	public void testCreateQuestion() throws PersistenceException {
		Question q1 = new Question(questionId = questionDao.nextId());
		Question q2 = new Question(questionDao.nextId());

		q1.setQuestion("What is your name?");
		questionDao.create(q1);
		questionDao.create(q2);		
	}
	
	@Test
	public void testRetrieveQuestion() throws PersistenceException {
		questionDao.retrieveAll().forEach(o->System.out.println(o));
		
		Question q = questionDao.retrieveByID(questionId);
		
		assertTrue(q.getQuestion().equals("What is your name?"));
	}
}
