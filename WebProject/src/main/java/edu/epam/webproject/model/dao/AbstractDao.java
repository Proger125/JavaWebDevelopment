package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Entity;
import edu.epam.webproject.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Interface that contains all common methods for all dao
 * @param <T> - object of {@link Entity}
 */
public interface AbstractDao<T extends Entity> {
    /**
     * Finds {@link Entity} by id
     * @param id - {@link Entity} id
     * @return {@link Optional<Entity>}
     * @throws DaoException when problems with database connection occurs
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Finds all {@link Entity}
     * @return {@link List<Entity>}
     * @throws DaoException when problems with database connection occurs
     */
    List<T> findAll() throws DaoException;
}
