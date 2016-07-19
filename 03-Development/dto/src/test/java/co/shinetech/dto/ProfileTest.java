package co.shinetech.dto;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import co.shinetech.dto.Profile;

public class ProfileTest {
	@Test
	public void ProfileInstanceSuccess(){
		Profile p = new Profile(987654321L,"Inácio");
		
		assertNotNull(p);
	}
	
	@Test
	public void HashCodeEqual(){
		Profile p1 = new Profile(987654321L,"Inácio");
		Profile p2 = new Profile(987654321L,"Inácio");
		
		assertEquals(p1, p2);
		assertEquals(p1.hashCode(), p2.hashCode());
	}
}