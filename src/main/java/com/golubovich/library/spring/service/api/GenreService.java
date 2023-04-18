package com.golubovich.library.servisespring.api;

import com.golubovich.library.model.Genre;
import com.golubovich.library.servisespring.ServiceException;

import java.util.List;

public interface GenreService {
    long add(Genre genre) throws ServiceException;

    void changeDescription(long id, String description) throws ServiceException;

    List<Genre> showAll() throws ServiceException;

    Genre takeById(Long id) throws ServiceException;
}
