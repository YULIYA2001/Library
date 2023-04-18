package com.golubovich.library.spring.service.api;

import com.golubovich.library.spring.model.Genre;
import com.golubovich.library.spring.service.ServiceException;

import java.util.List;

public interface GenreService {
    Genre add(Genre genre) throws ServiceException;

    Genre changeDescription(long id, String description) throws ServiceException;

    List<Genre> showAll() throws ServiceException;

    Genre takeById(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
