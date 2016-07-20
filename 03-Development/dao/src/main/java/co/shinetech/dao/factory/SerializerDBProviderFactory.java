/*
 * SerializerDBProviderFactory.java
 */
package co.shinetech.dao.factory;

import co.shinetech.dao.PersistenceProvider;
import co.shinetech.dao.impl.UserDAOImp;

/**
 * @author Rodrigo
 * @since 2015-07-07
 */
public class SerializerDBProviderFactory {
	public static final String TABLE_USER = "User";
            
    @SuppressWarnings("rawtypes")
    public static PersistenceProvider getPersistenceProvider(String table) {
        PersistenceProvider pv = null;

        if( table != null ) {        
            if( PersistenceProviderAbstractFactory.TABLE_USER.equals(table) )
                pv = new UserDAOImp();
        }
        return pv;
    }
}