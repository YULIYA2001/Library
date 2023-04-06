package com.golubovich.library.service.api;

import com.golubovich.library.bean.Genre;
import com.golubovich.library.service.ServiceException;

import java.util.List;

public interface GenreService {
    long add(Genre genre) throws ServiceException;
    void changeDescription(long id, String description) throws ServiceException;
    List<Genre> showAll() throws ServiceException;
}
