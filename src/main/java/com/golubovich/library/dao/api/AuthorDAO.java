package com.golubovich.library.dao.api;

import com.golubovich.library.bean.Author;
import com.golubovich.library.dao.DAOException;

import java.util.List;

public interface AuthorDAO {
    long create(Author author) throws DAOException;

    List<Author> read() throws DAOException;

    boolean update(Author updatedAuthor) throws DAOException;

    boolean delete(Author deletedAuthor) throws DAOException;

    Author findById(long id) throws DAOException;

    List<Author> findByBookId(long id) throws DAOException;
}
