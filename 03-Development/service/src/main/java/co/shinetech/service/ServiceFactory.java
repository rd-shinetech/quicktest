/**
 * ServiceFactory.java
 */
package co.shinetech.service;

import java.util.HashMap;
import java.util.Map;

import co.shinetech.service.impl.ActivityService;
import co.shinetech.service.impl.GroupService;
import co.shinetech.service.impl.UserService;

/**
 * Service Factory to have an unique entry point to the Service Component.
 * @author Rodrigo
 * @since 2016-07-28
 */
public final class ServiceFactory {
	private static Map<Class,ServiceProvider> serviceMap = new HashMap();
	
	static {
		serviceMap.put(GroupService.class,new GroupService());
		serviceMap.put(UserService.class, new UserService());
		serviceMap.put(ActivityService.class, new ActivityService());
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
	public static <T extends ServiceProvider> T getService(Class<T> c) {
		return (T) serviceMap.get(c);		
	}
}