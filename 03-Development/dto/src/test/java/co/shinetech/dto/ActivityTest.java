/**
 * 
 */
package co.shinetech.dto;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Goncalo
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
		ActivityType type = ActivityType.EXERCISE;
		Group group = new Group(10L,"turminha",new ArrayList<User>());
		Activity a = new Activity(39,"Joel",LocalDateTime.now(),ldt,teacher,type,group);
		assertNotNull(a);
	}

	@Test
	public void TestHashEquals(){
		Profile prof = new Profile(400,"Teacher");
		User teacher = new User(300,prof);
		LocalDateTime ldt = LocalDateTime.now().plusHours(1);
		LocalDateTime ldtnow = LocalDateTime.now();
		ActivityType type = ActivityType.EXERCISE;
		Group group = new Group(10L,"turminha",new ArrayList<User>());
		Activity a1 = new Activity(39,"Joel",ldtnow,ldt,teacher,type,group);
		Activity a2 = new Activity(39,"Joel",ldtnow,ldt,teacher,type,group);
		System.out.println(a1.toString());
		System.out.println(a2.toString());
		assertEquals(a1.hashCode(),a2.hashCode());	
	}
}
