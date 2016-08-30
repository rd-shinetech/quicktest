/**
 * PersistenceProvider.java
 */
package co.shinetech.dao;

/**
 * @author Rodrigo
 * @since 2016-08
 * @version 1.0
 */
public interface PersistenceProvider{
	String TABLE_USER = "User";
	String TABLE_ACTIVITY = "Activity";
	String TABLE_GROUP = "Group";
	String TABLE_PROFILE = "Profile";
	String TABLE_ACTIVITY_AREA = "ActivityArea";
	String TABLE_QUESTION = "Question";
	
	DAOProvider<?> getDAOProvider(String table);
}
