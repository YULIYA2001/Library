package com.golubovich.library.service.impl;

import com.golubovich.library.bean.Genre;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.DAOProvider;
import com.golubovich.library.dao.api.GenreDAO;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.api.GenreService;

import java.util.List;

public class GenreServiceImpl implements GenreService {

    private final DAOProvider daoProvider = DAOProvider.getInstance();
    private final GenreDAO genreDAO = daoProvider.getGenreDAO();

    @Override
    public long add(Genre genre) throws ServiceException {
        try {
            validateGenre(genre);

            long createdId = genreDAO.create(genre);
            return createdId;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeDescription(long id, String description) throws ServiceException {
        try {
            Genre genre = genreDAO.findById(id);
            if (genre != null) {
                genre.setDescription(description);
            }
            else {
                throw new ServiceException("Id is not found");
            }
            return genreDAO.update(genre);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> showAll() throws ServiceException {
        try {
            return genreDAO.read();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Genre takeById(Long id) throws ServiceException {
        try {
            Genre genre = genreDAO.findById(id);
            if (genre != null) {
                return genre;
            }
            else {
                throw new ServiceException("No genre with id");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void validateGenre(Genre genre) throws ServiceException {
        if (genre.getName() == null) {
            throw new ServiceException("Not valid data");
        }
        if (genre.getDescription() == null) {
            genre.setDescription("");
        }
    }
}
