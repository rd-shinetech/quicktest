/*
 * SerializerDBProviderFactory.java
 */
package co.shinetech.dao.factory;

import java.util.HashMap;
import java.util.Map;

import co.shinetech.dao.DAOProvider;
import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.impl.ActivityAreaDAOImp;
import co.shinetech.dao.impl.ActivityDAOImp;
import co.shinetech.dao.impl.GroupDAOImp;
import co.shinetech.dao.impl.ProfileDAOImp;
import co.shinetech.dao.impl.QuestionDAOImp;
import co.shinetech.dao.impl.UserDAOImp;

/**
 * @author Rodrigo
 * @param <T>
 * @since 2015-07-07
 */
public class SerializerDBProviderFactory implements PersistenceProvider {
	private static Map<String,DAOProvider<?>> tableMap = new HashMap<>();
	
	static {
		tableMap.put(PersistenceProvider.TABLE_USER, new UserDAOImp());
		tableMap.put(PersistenceProvider.TABLE_ACTIVITY, new ActivityDAOImp());
		tableMap.put(PersistenceProvider.TABLE_GROUP, new GroupDAOImp());
		tableMap.put(PersistenceProvider.TABLE_PROFILE, new ProfileDAOImp());
		tableMap.put(PersistenceProvider.TABLE_ACTIVITY_AREA, new ActivityAreaDAOImp());
		tableMap.put(PersistenceProvider.TABLE_QUESTION, new QuestionDAOImp());
	}

	@Override
	public DAOProvider<?> getDAOProvider(String table) {
		return tableMap.get(table);
	}
}