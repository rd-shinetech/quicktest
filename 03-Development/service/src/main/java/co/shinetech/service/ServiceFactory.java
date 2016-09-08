/**
 * ServiceFactory.java
 */
package co.shinetech.service;

import java.util.HashMap;
import java.util.Map;

import co.shinetech.service.impl.ActivityAreaService;
import co.shinetech.service.impl.ActivityService;
import co.shinetech.service.impl.GroupService;
import co.shinetech.service.impl.ProfileService;
import co.shinetech.service.impl.QuestionService;
import co.shinetech.service.impl.UserService;

/**
 * Service Factory to have an unique entry point to the Service Component.
 * @author Rodrigo
 * @since 2016-07-28
 */
public final class ServiceFactory {
	@SuppressWarnings("rawtypes")
	private static Map<Class,ServiceProvider> serviceMap = new HashMap<Class, ServiceProvider>();
	
	static {
		serviceMap.put(GroupService.class,new GroupService());
		serviceMap.put(UserService.class, new UserService());
		serviceMap.put(ActivityService.class, new ActivityService());
		serviceMap.put(ProfileService.class, new ProfileService());
		serviceMap.put(ActivityAreaService.class, new ActivityAreaService());
		serviceMap.put(QuestionService.class, new QuestionService());
	}
	
	private ServiceFactory() {}
		
	/**
	 * Method to return a service implementation.
	 * 
	 * Usage: ServiceFactory.getService<GroupService>(GroupService.class);
	 * That will return a GroupService instance to be used in another layer/component.
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T extends ServiceProvider> T getService(Class<T> c) {
		return (T) serviceMap.get(c);		
	}
}