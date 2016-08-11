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
 *
 */
public class ActivityAreaTest {
	@Test
	public void TestActivityArea() {
		Profile pf = new Profile(8963254L, "Isto_e_um_teste");
		assertNotNull(pf);
	}
	@Test
	public void testEqualAndHashCode() {
		Profile pf1 = new Profile(8963254L, "Isto_e_um_teste");
		Profile pf2 = new Profile(8963254L, "Isto_e_um_teste");
		assertEquals(pf1, pf2);
		assertEquals(pf1.hashCode(), pf2.hashCode());
		assertEquals(pf1.toString(), pf2.toString());
		System.out.println(pf1.toString());
		System.out.println(pf2.toString());
	}
	
}
