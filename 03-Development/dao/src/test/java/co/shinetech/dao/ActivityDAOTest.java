/**
 * 
 */
package co.shinetech.dao;

import java.time.LocalDateTime;

import co.shinetech.dto.Activity;
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
	User teacher = new User();
	
	
	Activity d = new Activity(id, name, startTime, endTime, teacher,    ){

}
