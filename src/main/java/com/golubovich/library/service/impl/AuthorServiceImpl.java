package com.golubovich.library.service.impl;

import com.golubovich.library.bean.Author;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.DAOProvider;
import com.golubovich.library.dao.api.AuthorDAO;
import com.golubovich.library.service.ServiceException;
import com.golubovich.library.service.api.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final DAOProvider daoProvider = DAOProvider.getInstance();
    private final AuthorDAO authorDAO = daoProvider.getAuthorDAO();

    @Override
    public long add(Author author) throws ServiceException {
        try {
            validateAuthor(author);

            long createdId = authorDAO.create(author);
            return createdId;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeInfo(long id, String info) throws ServiceException {
        try {
            Author author = authorDAO.findById(id);
            if (author != null) {
                author.setInfo(info);
            } else {
                throw new ServiceException("Id is not found");
            }
            return authorDAO.update(author);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Author> showAll() throws ServiceException {
        try {
            return authorDAO.read();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Author takeById(Long id) throws ServiceException {
        try {
            Author author = authorDAO.findById(id);
            if (author != null) {
                return author;
            } else {
                throw new ServiceException("No author with id");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    private void validateAuthor(Author author) throws ServiceException {
        if (author.getName() == null) {
            throw new ServiceException("Not valid data");
        }
        if (author.getInfo() == null) {
            author.setInfo("");
        }
    }
}
