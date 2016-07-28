/**
 * PersistenceProvider.java
 */
package co.shinetech.dao;

/**
 * @author Rodrigo
 */
public interface PersistenceProvider{
	public static final String TABLE_USER = "User";
	public static final String TABLE_ACTIVITY = "Activity";
	public static final String TABLE_GROUP = "Group";
	
	DAOProvider<?> getDAOProvider(String table);
}
