/**
 * 
 */
package co.shinetech.dto;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

/**
 * @author Gonçalo
 *
 */
public class ActivityTest {

	/**
	 * 
	 */
	
	@Test
	public void TestInstance() {
		Profile prof = new Profile(400,"Teacher");
		User teacher = new User(300,prof);
		LocalDateTime ldt = LocalDateTime.now().plusHours(1);
		Activity a = new Activity((long)39,"Joel",LocalDateTime.now(),ldt,"class1",teacher);
		assertNotNull(a);
	}

	@Test
	public void TestHashEquals(){
		Profile prof = new Profile(400,"Teacher");
		User teacher = new User(300,prof);
		LocalDateTime ldt = LocalDateTime.now().plusHours(1);
		Activity a1 = new Activity((long)39,"Joel",LocalDateTime.now(),ldt,"class1",teacher);
		Activity a2 = new Activity((long)39,"Joel",LocalDateTime.now(),ldt,"class1",teacher);
		System.out.println(a1.toString());
		System.out.println(a2.toString());
		assertEquals(a1.hashCode(),a2.hashCode());
		
	}
}
