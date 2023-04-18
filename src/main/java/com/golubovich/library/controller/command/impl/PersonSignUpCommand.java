package com.golubovich.library.controller.command.impl;

import com.golubovich.library.bean.Person;
import com.golubovich.library.controller.command.Command;
import com.golubovich.library.controller.command.EncryptMD;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.PersonService;
import org.apache.log4j.Logger;

public class PersonSignUpCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String EQUAL_REGEX = "=";

    private static final Logger log = Logger.getLogger(PersonSignUpCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 2) {
            PersonService personService = provider.getPersonService();

            String email = params[0].split(EQUAL_REGEX)[1];
            String password = EncryptMD.encryptPassword(params[1].split(EQUAL_REGEX)[1]);

            try {
                long createdId = personService.add(new Person(email, password));
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
