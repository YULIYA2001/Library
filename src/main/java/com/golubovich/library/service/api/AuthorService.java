package com.golubovich.library.service.api;

import com.golubovich.library.bean.Author;
import com.golubovich.library.service.ServiceException;

import java.util.List;

public interface AuthorService {
    long add(Author author) throws ServiceException;

    boolean changeInfo(long id, String info) throws ServiceException;

    List<Author> showAll() throws ServiceException;

    Author takeById(Long id) throws ServiceException;
}
