package com.golubovich.library.service.impl;

import com.golubovich.library.bean.Book;
import com.golubovich.library.bean.Person;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.DAOProvider;
import com.golubovich.library.dao.api.ItemDAO;
import com.golubovich.library.dao.api.PersonDAO;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.api.ItemService;

import java.util.List;

public class BookServiceImpl implements ItemService {

    private final DAOProvider daoProvider = DAOProvider.getInstance();
    private final ItemDAO<Book> bookDAO = daoProvider.getBookDAO();

    @Override
    public long add(Book book) throws ServiceException {
        try {
            validateBook(book);

            long createdId = bookDAO.create(book);
            return createdId;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeReader(long id, long person_id) throws ServiceException {
        try {
            Book book = bookDAO.findById(id);

            if (book != null) {
                if (person_id == 0) {
                    book.setPerson(null);
                    return bookDAO.update(book);
                }

                PersonDAO personDAO = daoProvider.getPersonDAO();
                Person person = personDAO.findById(person_id);

                if (person == null) {
                    throw new ServiceException("Person id is not found");
                }

                book.setPerson(person);
            } else {
                throw new ServiceException("Id is not found");
            }
            return bookDAO.update(book);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> showAll() throws ServiceException {
        try {
            return bookDAO.read();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            Book book = bookDAO.findById(id);
            if (book != null) {
                return bookDAO.delete(book);
            } else {
                throw new ServiceException("Can't delete book. Id is not found");
            }
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateBook(Book book) throws ServiceException {
        if (book.getTitle() == null) {
            throw new ServiceException("Not valid data. No book title");
        }
        if (book.getLanguage() == null) {
            book.setLanguage("");
        }
        if (book.getPerson() != null) {
            book.setPerson(null);
        }
        if (book.getGenre() == null) {
            throw new ServiceException("Not valid data. No book genre");
        }
    }
}
