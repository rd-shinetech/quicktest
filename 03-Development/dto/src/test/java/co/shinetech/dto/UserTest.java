/**
 * 
 */
package co.shinetech.dto;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Ricardo
 *
 */
public class UserTest {
	long id = 1583958739573984L;
	String login = "User Login";
	char[] password = {'a','1','4','B'};
	Profile profile = new Profile(1583958739573984L, "Aluno");	
	
	
	@Test
	public void testCreateInstance() {
		User tester = new User(id, login, password, profile);
		assertNotNull(tester);
		System.out.println(tester.toString());		
		
	}
	
	@Test
	public void testHashCodeAndEquals() {
		User tester1 = new User(id, profile);
		User tester2 = new User(id, profile);
		assertEquals(tester1, tester2);
		assertEquals(tester1.hashCode(), tester2.hashCode());
				
	}
	
	
}
