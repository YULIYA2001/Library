package com.golubovich.library.dao.impl;

import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.api.ItemDAO;

import java.util.List;

public class BookDAOImpl implements ItemDAO {
    @Override
    public boolean create(Object item) throws DAOException {
        return false;
    }

    @Override
    public List read() throws DAOException {
        return null;
    }

    @Override
    public void update(Object currentItem, Object updatedItem) throws DAOException {

    }

    @Override
    public boolean delete(Object deletedItem) throws DAOException {
        return false;
    }

    @Override
    public Object findById(long id) throws DAOException {
        return null;
    }
}
