/**
 * 
 */
package co.shinetech.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Ricardo
 * @since 2016-08
 */
public class QuestionTest {
	@Test
	public void TestQuestion() {
		Question qst = new Question(1L);
		assertNotNull(qst);
	}
	
	@Test
	public void testEqualAndHashCode() {
		Question qst1 = new Question(1L);
		Question qst2 = new Question(1L);
		assertEquals(qst1, qst2);
		assertEquals(qst1, qst1);
		assertEquals(qst1.hashCode(), qst2.hashCode());
		assertEquals(qst1.toString(), qst1.toString());
		qst1.setAnswer("answer");
		assertEquals(qst1.getAnswer(), "answer");
		System.out.println(qst1.toString());
		System.out.println(qst1.toString());
	}
}
