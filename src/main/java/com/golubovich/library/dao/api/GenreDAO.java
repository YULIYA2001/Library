package com.golubovich.library.dao.api;

import com.golubovich.library.bean.Genre;
import com.golubovich.library.dao.DAOException;

import java.util.List;

public interface GenreDAO {
    long create(Genre genre) throws DAOException;

    List<Genre> read() throws DAOException;

    boolean update(Genre updatedGenre) throws DAOException;

    boolean delete(Genre deletedGenre) throws DAOException;

    Genre findById(long id) throws DAOException;
}
