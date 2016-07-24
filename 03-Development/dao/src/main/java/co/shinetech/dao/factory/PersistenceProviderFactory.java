/*
 * PersistenceProviderFactory.java
 */
package co.shinetech.dao.factory;

import co.shinetech.dao.PersistenceProvider;

/**
 * @author Rodrigo
 * @since 2015-07-07
 */
public class PersistenceProviderFactory {
	public static final String PERSISTENCE_SERIALIZED = "Serialized";
	// ... another persistence ... //
	
    private PersistenceProviderFactory() {}

	public static PersistenceProvider getPersistenceProvider(String db) {
		PersistenceProvider r = null;
		
		if( PERSISTENCE_SERIALIZED.equals(db) ) {
			r = new SerializerDBProviderFactory();
		}

		return r;
	}
}