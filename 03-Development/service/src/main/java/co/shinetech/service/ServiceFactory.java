/**
 * ServiceFactory.java
 */
package co.shinetech.service;

import java.util.HashMap;
import java.util.Map;

import co.shinetech.service.impl.GroupService;

/**
 * @author Rodrigo
 *
 */
public final class ServiceFactory {
	private static Map<Class,ServiceProvider> serviceMap = new HashMap();
	
	static {
		serviceMap.put(GroupService.class,new GroupService());
	}
	
	private ServiceFactory() {}
		
	public static <T extends ServiceProvider> T getService(Class<T> c) {
		return (T) serviceMap.get(c);		
	}
	
	// ServiceFactory.getService<GroupService>(GroupService.class);
}
