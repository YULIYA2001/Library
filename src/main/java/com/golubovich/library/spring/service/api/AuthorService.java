package com.golubovich.library.servisespring.api;

import com.golubovich.library.model.Author;
import com.golubovich.library.servisespring.ServiceException;

import java.util.List;

public interface AuthorService {
    long add(Author author) throws ServiceException;

    void changeInfo(long id, String info) throws ServiceException;

    List<Author> showAll() throws ServiceException;

    Author takeById(Long id) throws ServiceException;
}
