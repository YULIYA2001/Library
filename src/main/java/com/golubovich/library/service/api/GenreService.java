package com.golubovich.library.service.api;

import com.golubovich.library.bean.Genre;
import com.golubovich.library.spring.service.ServiceException;

import java.util.List;

public interface GenreService {
    long add(Genre genre) throws ServiceException;

    boolean changeDescription(long id, String description) throws ServiceException;

    List<Genre> showAll() throws ServiceException;

    Genre takeById(Long id) throws ServiceException;
}
