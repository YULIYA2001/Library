package com.golubovich.library.service.api;


import com.golubovich.library.bean.Book;
import com.golubovich.library.spring.service.ServiceException;

import java.util.List;

public interface ItemService {
    long add(Book book) throws ServiceException;

    boolean changeReader(long id, long person_id) throws ServiceException;

    List<Book> showAll() throws ServiceException;

    boolean delete(Long id) throws ServiceException;

}
