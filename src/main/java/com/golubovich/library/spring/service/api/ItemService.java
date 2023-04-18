package com.golubovich.library.servisespring.api;


import com.golubovich.library.model.Book;
import com.golubovich.library.model.Item;
import com.golubovich.library.servisespring.ServiceException;

import java.util.List;

public interface ItemService {
    long add(Book book) throws ServiceException;

    void changeReader(long id, long person_id) throws ServiceException;

    List<Item> showAll() throws ServiceException;

    void delete(Long id) throws ServiceException;

}
