package com.golubovich.library.controller.command.impl;

import com.golubovich.library.bean.Author;
import com.golubovich.library.bean.Book;
import com.golubovich.library.bean.Genre;
import com.golubovich.library.controller.command.Command;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.AuthorService;
import com.golubovich.library.service.api.GenreService;
import com.golubovich.library.service.api.ItemService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookAddCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String EQUAL_REGEX = "=";
    public static final String SPACE_REGEX = "\\s+";

    private static final Logger log = Logger.getLogger(BookAddCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 4) {
            ItemService bookService = provider.getBookService();
            GenreService genreService = provider.getGenreService();
            AuthorService authorService = provider.getAuthorService();

            String title = params[0].split(EQUAL_REGEX)[1];
            String language = params[1].split(EQUAL_REGEX)[1];
            String genreId = params[2].split(EQUAL_REGEX)[1];
            String[] authorsIds = params[3].split(EQUAL_REGEX)[1].split(SPACE_REGEX);

            try {
                Genre genre = genreService.takeById(Long.parseLong(genreId));

                List<Author> authors = new ArrayList<>();
                for (String id : authorsIds) {
                    authors.add(authorService.takeById(Long.parseLong(id)));
                }

                long createdId = bookService.add(new Book(title, language, null, genre, authors));
                return createdId != 0 ? SUCCESS : FAIL;
            } catch (ServiceException e) {
                log.error(e.getMessage());
            } catch (NumberFormatException e) {
                log.error("Not numeric genre/authors id", e);
            }
        } else {
            log.error("Wrong params count");
        }

        return FAIL;
    }
}
