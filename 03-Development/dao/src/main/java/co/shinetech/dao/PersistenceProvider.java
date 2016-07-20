/*
* PersistenceProvider.java
*/
package co.shinetech.dao;

import java.util.ArrayList;

import co.shinetech.dao.db.PersistenceException;
import co.shinetech.dto.Domain;

/**
 * Interface to provide persistence for applications.
 * @author Rodrigo
 * @param <T>
 * @since 2015-06-24
 */
public interface PersistenceProvider<T extends Domain> {
    void create( T d ) throws PersistenceException;
    void update(T d) throws PersistenceException;
    T retrieveByID(long ID) throws PersistenceException;
    ArrayList<T> retrieveAll() throws PersistenceException;
    void delete(long ID) throws PersistenceException;
    int count() throws PersistenceException;
}
