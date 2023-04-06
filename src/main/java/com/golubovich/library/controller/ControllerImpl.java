package com.golubovich.library.controller;

import com.golubovich.library.controller.command.Command;
import com.golubovich.library.controller.command.CommandProvider;

import java.util.Arrays;

public class ControllerImpl implements Controller {

    private static final ControllerImpl instance = new ControllerImpl();

    private final CommandProvider provider = CommandProvider.getInstance();

    private static final String DIVIDER_REGEX = "&";

    public String doAction(String request) {
        String[] params = request.split(DIVIDER_REGEX);
        String commandName = params[0];
        params = Arrays.copyOfRange(params, 1, params.length);
        Command currentCommand = this.provider.getCommand(commandName.toUpperCase());
        return currentCommand.execute(params);
    }

    public static ControllerImpl getInstance() {
        return instance;
    }
}
