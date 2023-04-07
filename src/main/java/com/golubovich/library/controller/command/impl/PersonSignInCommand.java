package com.golubovich.library.controller.command.impl;

import com.golubovich.library.controller.command.Command;
import com.golubovich.library.controller.command.EncryptMD;
import com.golubovich.library.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.PersonService;
import org.apache.log4j.Logger;

public class PersonSignInCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    private static final String DIVIDER_REGEX = "&";
    public static final String EQUAL_REGEX = "=";

    private static final Logger log = Logger.getLogger(GenreShowAllCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 2) {
            PersonService personService = provider.getPersonService();

            String email = params[0].split(EQUAL_REGEX)[1];
            String password = EncryptMD.encryptPassword(params[1].split(EQUAL_REGEX)[1]);

            try {
                return SUCCESS + DIVIDER_REGEX + personService.signIn(email, password);
            } catch (ServiceException e) {
                log.error(e.getMessage());
            }
        } else {
            log.error("Wrong params count");
        }

        return FAIL;
    }
}