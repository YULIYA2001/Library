package com.golubovich.library.controller.command.impl;

import com.golubovich.library.bean.Genre;
import com.golubovich.library.controller.command.Command;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.GenreService;

import org.apache.log4j.Logger;

public class GenreAddCommand implements Command {

    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String EQUAL_REGEX = "=";

    private static final Logger log = Logger.getLogger(GenreAddCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 2) {
            GenreService genreService = provider.getGenreService();

            String name = params[0].split(EQUAL_REGEX)[1];
            String description = params[1].split(EQUAL_REGEX)[1];

            try {
                long createdId = genreService.add(new Genre(name, description));
                return createdId != 0 ? SUCCESS : FAIL;
            } catch (ServiceException e) {
                log.error(e.getMessage());
            }
        } else {
            log.error("Wrong params count");
        }

        return FAIL;
    }
}