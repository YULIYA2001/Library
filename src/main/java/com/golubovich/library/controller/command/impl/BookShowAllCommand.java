package com.golubovich.library.controller.command.impl;

import com.golubovich.library.bean.Book;
import com.golubovich.library.controller.command.Command;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.ItemService;
import org.apache.log4j.Logger;

public class BookShowAllCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    private static final String DIVIDER_REGEX = "&";

    private static final Logger log = Logger.getLogger(BookShowAllCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 0) {
            ItemService bookService = provider.getBookService();

            try {
                StringBuilder response = new StringBuilder(SUCCESS + DIVIDER_REGEX);
                for (Book b: bookService.showAll()) {
                    response.append(b.toString()).append('\n');
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
