package co.shinetech.dto;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

/**
 * 
 * @author Robin
 *
 */
public class ResultTest {
	private final Random rnd = new Random();
	
	@Test
	public void testeCreateInstance() {
		long id = rnd.nextLong();
		Result r = new Result(id);
		assertNotNull(id);
		System.out.println(r.toString());
	}
	
	@Test
	public void testEqualAndHashCode() {
		long id = rnd.nextLong();
		Result r1 = new Result(id);
		Result r2 = new Result(id);
		assertEquals(r1, r2);
		assertEquals(r1.hashCode(), r2.hashCode());
	}
}
