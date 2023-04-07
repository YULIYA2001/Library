package com.golubovich.library.dao.api;

import com.golubovich.library.dao.DAOException;

import java.util.List;

public interface ItemDAO<T> {
    long create(T item) throws DAOException;

    List<T> read() throws DAOException;

    boolean update(T updatedItem) throws DAOException;

    boolean delete(T deletedItem) throws DAOException;

    T findById(long id) throws DAOException;
}
