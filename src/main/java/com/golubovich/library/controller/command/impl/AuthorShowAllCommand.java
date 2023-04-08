package com.golubovich.library.controller.command.impl;

import com.golubovich.library.bean.Author;
import com.golubovich.library.controller.command.Command;
import com.golubovich.library.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.AuthorService;
import org.apache.log4j.Logger;

public class AuthorShowAllCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    private static final String DIVIDER_REGEX = "&";

    private static final Logger log = Logger.getLogger(AuthorShowAllCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 0) {
            AuthorService authorService = provider.getAuthorService();

            try {
                StringBuilder response = new StringBuilder(SUCCESS + DIVIDER_REGEX);
                for (Author a: authorService.showAll()) {
                    response.append(a.toString()).append('\n');
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
