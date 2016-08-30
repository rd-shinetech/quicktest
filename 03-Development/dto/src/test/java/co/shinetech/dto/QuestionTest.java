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
		Profile prf = new Profile(1L, "Teste");
		assertNotNull(prf);
	}
	@Test
	public void testEqualAndHashCode() {
		Profile prf1 = new Profile(1L, "Teste");
		Profile prf2 = new Profile(1L, "Teste");
		assertEquals(prf1, prf2);
		assertEquals(prf1.hashCode(), prf2.hashCode());
		assertEquals(prf1.toString(), prf2.toString());
		System.out.println(prf1.toString());
		System.out.println(prf2.toString());
	}
}
