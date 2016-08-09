/**
 * 
 */
package co.shinetech.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * @author Goncalo
 *
 */
public class ActivityTest {

	/**
	 * 
	 */
	
	@Test
	public void testInstance() {
		Profile prof = new Profile(400,"Teacher");
		User teacher = new User(300,prof);
		LocalDateTime ldt = LocalDateTime.now().plusHours(1);
		ActivityType type = ActivityType.EXERCISE;
		Group group = new Group(10L,"turminha");
		Activity a = new Activity(39,"Joel",LocalDateTime.now(),ldt,teacher,type,group);
		assertNotNull(a);
	}

	@Test
	public void testHashEquals(){
		Profile prof = new Profile(400,"Teacher");
		User teacher = new User(300,prof);
		LocalDateTime ldt = LocalDateTime.now().plusHours(1);
		LocalDateTime ldtnow = LocalDateTime.now();
		ActivityType type = ActivityType.EXERCISE;
		Group group = new Group(10L,"turminha");
		Activity a1 = new Activity(39,"Joel",ldtnow,ldt,teacher,type,group);
		Activity a2 = new Activity(39,"Joel",ldtnow,ldt,teacher,type,group);
		System.out.println(a1.toString());
		System.out.println(a2.toString());
		assertEquals(a1.hashCode(),a2.hashCode());	
	}
}
