package com.golubovich.library.dao;


import com.golubovich.library.bean.Book;
import com.golubovich.library.bean.Magazine;
import com.golubovich.library.dao.api.AuthorDAO;
import com.golubovich.library.dao.api.GenreDAO;
import com.golubovich.library.dao.api.ItemDAO;
import com.golubovich.library.dao.api.PersonDAO;
import com.golubovich.library.dao.impl.AuthorDAOImpl;
import com.golubovich.library.dao.impl.BookDAOImpl;
import com.golubovich.library.dao.impl.GenreDAOImpl;
import com.golubovich.library.dao.impl.MagazineDAOImpl;

public final class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final PersonDAO personDAO = null;// = new PersonDAOImpl();
    private final AuthorDAO authorDAO = new AuthorDAOImpl();
    private final GenreDAO genreDAO = new GenreDAOImpl();
    private final ItemDAO<Magazine> magazineDAO = new MagazineDAOImpl();
    private final ItemDAO<Book> bookDAO = new BookDAOImpl();


    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }

    public GenreDAO getGenreDAO() {
        return genreDAO;
    }

    public ItemDAO<Magazine> getMagazineDAO() {
        return magazineDAO;
    }

    public ItemDAO<Book> getBookDAO() {
        return bookDAO;
    }
}