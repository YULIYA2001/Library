package com.golubovich.library.service;

import com.golubovich.library.service.api.AuthorService;
import com.golubovich.library.service.api.GenreService;
import com.golubovich.library.service.api.ItemService;
import com.golubovich.library.service.impl.AuthorServiceImpl;
import com.golubovich.library.service.impl.BookServiceImpl;
import com.golubovich.library.service.impl.GenreServiceImpl;
import com.golubovich.library.service.impl.MagazineServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();
    private final ItemService magazineService = new MagazineServiceImpl();
    private final ItemService bookService = new BookServiceImpl();
    private final GenreService genreService = new GenreServiceImpl();

    private final AuthorService authorService = new AuthorServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public ItemService getMagazineService() {
        return this.magazineService;
    }

    public ItemService getBookService() {
        return this.bookService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public GenreService getGenreService() {
        return this.genreService;
    }
}
