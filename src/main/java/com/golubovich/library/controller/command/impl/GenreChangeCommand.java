package com.golubovich.library.controller.command.impl;

import com.golubovich.library.controller.command.Command;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.GenreService;
import org.apache.log4j.Logger;

public class GenreChangeCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String EQUAL_REGEX = "=";

    private static final Logger log = Logger.getLogger(GenreAddCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 2) {
            GenreService genreService = provider.getGenreService();

            String id = params[0].split(EQUAL_REGEX)[1];
            String description = params[1].split(EQUAL_REGEX)[1];

            try {
                return genreService.changeDescription(Long.parseLong(id), description) ? SUCCESS : FAIL;
            } catch (ServiceException e) {
                log.error(e.getMessage());
            } catch (NumberFormatException e) {
                log.error("Not numeric id", e);
            }
        } else {
            log.error("Wrong params count");
        }

        return FAIL;
    }
}
