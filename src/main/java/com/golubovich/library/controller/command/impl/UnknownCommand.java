package com.golubovich.library.controller.command.impl;

import com.golubovich.library.controller.command.Command;
import org.apache.log4j.Logger;

public class UnknownCommand implements Command {

    public static final String FAIL = "1";

    @Override
    public String execute(String[] params) {
        return FAIL;
    }
}
