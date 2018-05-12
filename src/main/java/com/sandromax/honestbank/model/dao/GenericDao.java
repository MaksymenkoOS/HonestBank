package com.sandromax.honestbank.model.dao;

import java.util.LinkedList;

/**
 * Created by sandro on 26.04.18.
 */
public interface GenericDao<T> {
    int create(T entity);

    LinkedList<T> findAll();

    T findById(int id);

//    T findByName(String name);
//
//    T findBy(String columnName, String value);

    boolean update(T entity);

    boolean delete(T entity);
}
