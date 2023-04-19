package com.golubovich.library.spring.service.api;


import com.golubovich.library.spring.model.Book;
import com.golubovich.library.spring.service.ServiceException;

import java.util.List;

public interface ItemService<T> {
    Book add(T book) throws ServiceException;

    T changeReader(long id, long personId) throws ServiceException;

    List<T> showAll() throws ServiceException;

    void delete(Long id) throws ServiceException;

    T takeById(Long id) throws ServiceException;
}
