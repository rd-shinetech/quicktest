/**
 * 
 */
package co.shinetech.dto;

import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Robin
 *
 */
public class ResultItemTest {
	private final Random rnd = new Random();
	private final String result = "ResultItem 1";
	@Test
	public void testeCreateInstance() {
		long id = rnd.nextLong();
		ResultItem item = new ResultItem(id, result);
		assertNotNull(item);
		System.out.println(item.toString());
	}
	
	@Test
	public void testEqualAndHashCode() {
		long id = rnd.nextLong();
		ResultItem it1 = new ResultItem(id, result);
		ResultItem it2 = new ResultItem(id, result);
		assertEquals(it1, it2);
		assertEquals(it1.hashCode(), it2.hashCode());
	}
}
