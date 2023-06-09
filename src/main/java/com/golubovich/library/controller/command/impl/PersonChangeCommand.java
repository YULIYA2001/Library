package com.golubovich.library.controller.command.impl;

import com.golubovich.library.bean.Role;
import com.golubovich.library.controller.command.Command;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.PersonService;
import org.apache.log4j.Logger;

public class PersonChangeCommand implements Command {
    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String EQUAL_REGEX = "=";

    private static final Logger log = Logger.getLogger(PersonChangeCommand.class);

    @Override
    public String execute(String[] params) {
        ServiceProvider provider = ServiceProvider.getInstance();

        if (params.length == 2) {
            PersonService personService = provider.getPersonService();

            String id = params[0].split(EQUAL_REGEX)[1];
            Role role = Role.valueOf(params[1].split(EQUAL_REGEX)[1].toUpperCase());

            try {
                return personService.changeRole(Long.parseLong(id), role) ? SUCCESS : FAIL;
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
