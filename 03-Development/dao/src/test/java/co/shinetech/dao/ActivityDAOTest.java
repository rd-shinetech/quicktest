/**
 * 
 */
package co.shinetech.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Test;

import co.shinetech.dao.factory.SerializerDBProviderFactory;
import co.shinetech.dao.impl.ActivityDAOImp;
import co.shinetech.dto.Activity;
import co.shinetech.dto.ActivityType;
import co.shinetech.dto.Group;
import co.shinetech.dto.Profile;
import co.shinetech.dto.User;

/**
 * @author Ricardo
 *
 */
public class ActivityDAOTest {
	long id = 9283498394339L;
	String name = "Joaquim";
	LocalDateTime startTime = LocalDateTime.now();
	LocalDateTime endTime = LocalDateTime.now().plusMinutes(60);
	char[] password = {'a', 'b', 'B', '2'};
	User teacher = new User(54218547L, "Manuel", password, new Profile(839483948L, "name"));
	ActivityType at = ActivityType.EXAME;
	ArrayList<User> users = new ArrayList<>();	
	Group group = new Group(25256543565L, "Maria Jos�", users);
	private static final PersistenceProvider<Activity> activityDao = SerializerDBProviderFactory
	.getPersistenceProvider(SerializerDBProviderFactory
		.TABLE_AC);

	@Test
	public void testCreateActivity() {
		// add User to ArrayList, in this case, teacher
		users.add(teacher);

		Activity d = new Activity(id, name, startTime, endTime, teacher, at, group);
		
		ActivityDAOImp adi = new ActivityDAOImp();
	}
}
