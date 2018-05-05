package com.sandromax.honestbank.model.dao;

import java.util.List;

/**
 * Created by sandro on 26.04.18.
 */
public interface GenericDao<T> {
    boolean create(T entity);

    List<T> findAll();

    T findById(int id);

    T findByName(String name);

    boolean update(T entity);

    boolean delete(T entity);
}
