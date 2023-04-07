package com.golubovich.library.service.api;

import com.golubovich.library.bean.Author;
import com.golubovich.library.service.ServiceException;

import java.util.List;

public interface AuthorService {
    long add(Author author) throws ServiceException;
    void changeInfo(long id, String info) throws ServiceException;
    List<Author> showAll() throws ServiceException;
}
