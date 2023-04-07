package com.golubovich.library.controller.command.impl;

import com.golubovich.library.controller.command.Command;
import com.golubovich.library.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.ItemService;
import org.apache.log4j.Logger;

public class BookDeleteCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String EQUAL_REGEX = "=";

    private static final Logger log = Logger.getLogger(BookDeleteCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 1) {
            ItemService bookService = provider.getBookService();

            String id = params[0].split(EQUAL_REGEX)[1];

            try {
                return bookService.delete(Long.parseLong(id)) ? SUCCESS : FAIL;
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
