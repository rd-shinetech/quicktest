/**
 * ServiceProvider.java
 */
package co.shinetech.service;

import java.util.List;

import co.shinetech.dao.db.PersistenceException;

/**
 * Interface with common methods for Services.
 * @author Rodrigo
 * @since 2016-07-28
 */
public interface ServiceProvider<T> {
    void create(T d) throws PersistenceException;
    void update(T d) throws PersistenceException;
    T retrieveByID(int ID) throws PersistenceException;
    List<T> retrieveAll() throws PersistenceException;
    void delete(int ID) throws PersistenceException;
    int count() throws PersistenceException;
    long nextId() throws PersistenceException;
}
