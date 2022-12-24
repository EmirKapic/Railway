package ba.unsa.etf.rpr.Dao;

import java.util.List;

/**
 * Interface which is to be implemented by all specific-dao classes (departures, passengers etc.)
 */
public interface Dao<T> {
    /**
     * get object with the specified ID(PK) from database
     * @param id primary key in the DB
     * @return Object with the primaryKey
     */
    T getById(int id);

    /**
     * Adds an object to the DB
     * @param item the object to be added
     * @return added item
     */
    T add(T item);

    /**
     * Updates an object in the Database
     * @param item to be updated
     * @return updated object
     */
    T update(T item);

    /**
     * Deletes the specified object from the DB
     * @param id id of the object to be deleted
     */
    void delete(int id);

    /**
     * Returns all objects from the DB
     * @return List of all objects
     */
    List<T> getAll();
}
