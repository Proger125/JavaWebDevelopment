package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface AbstractDao<T extends Entity> {
    boolean add(T entity);
    Optional<T> findById(long id);
    Optional<List<T> > findAll();
    Optional<T> updateById(long id, T entity);
}
