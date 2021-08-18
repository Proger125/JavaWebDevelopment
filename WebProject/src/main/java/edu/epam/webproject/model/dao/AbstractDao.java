package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Entity;
import edu.epam.webproject.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AbstractDao<T extends Entity> {
    Optional<T> findById(long id) throws DaoException;
    List<T> findAll() throws DaoException;
}
