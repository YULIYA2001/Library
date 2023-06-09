package com.golubovich.library.controller.command.impl;

import com.golubovich.library.bean.Genre;
import com.golubovich.library.controller.command.Command;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.GenreService;
import org.apache.log4j.Logger;

public class GenreShowAllCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    private static final String DIVIDER_REGEX = "&";

    private static final Logger log = Logger.getLogger(GenreShowAllCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 0) {
            GenreService genreService = provider.getGenreService();

            try {
                StringBuilder response = new StringBuilder(SUCCESS + DIVIDER_REGEX);
                for (Genre g: genreService.showAll()) {
                    response.append(g.toString()).append('\n');
                }
                return response.toString();
            } catch (ServiceException e) {
                log.error(e.getMessage());
            }
        } else {
            log.error("Wrong params count");
        }

        return FAIL;
    }
}
