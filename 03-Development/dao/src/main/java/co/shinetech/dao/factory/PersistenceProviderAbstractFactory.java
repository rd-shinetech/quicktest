/*
 * DAOAbstractFactory.java
 */
package co.shinetech.dao.factory;

import co.shinetech.dao.PersistenceProvider;

/**
 * @author Rodrigo
 * @since 2015-07-07
 */
public class PersistenceProviderAbstractFactory {
    //private static PersistenceProviderAbstractFactory self;
	public static final String TABLE_USER = "User";
	public static final String TABLE_ACTIVITY = "Activity";

    //private PersistenceProviderAbstractFactory() {}

    //public static PersistenceProviderAbstractFactory getInstance() {
    //	if( self == null ) {
    //		self = new PersistenceProviderAbstractFactory();
    //	}    		
    //	return self; 
   // }
    
    @SuppressWarnings("rawtypes")
	public static PersistenceProvider getPersistenceProvider(String table) {
    	return null;
    }
}