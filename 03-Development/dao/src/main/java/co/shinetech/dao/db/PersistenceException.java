/*
* PersistenceException.java
*/
package co.shinetech.dao.db;

/**
 * Class to implement backend exceptions.
 * @author Rodrigo
 * @since 2015-06-24
 */
public class PersistenceException extends Exception {

    public PersistenceException(String message) {
        super(message);
    }
    
}
