package com.golubovich.library.spring.service.api;

import com.golubovich.library.spring.model.Author;
import com.golubovich.library.spring.service.ServiceException;

import java.util.List;

public interface AuthorService {
    Author add(Author author) throws ServiceException;

    Author changeInfo(long id, String info) throws ServiceException;

    List<Author> showAll() throws ServiceException;

    Author takeById(Long id) throws ServiceException;
}
