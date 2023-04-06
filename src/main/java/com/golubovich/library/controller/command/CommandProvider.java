package com.golubovich.library.controller.command;

import com.golubovich.library.controller.command.impl.GenreAddCommand;
import com.golubovich.library.controller.command.impl.GenreChangeCommand;
import com.golubovich.library.controller.command.impl.GenreShowAllCommand;
import com.golubovich.library.controller.command.impl.UnknownCommand;
import org.apache.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {

    private static final Logger log = Logger.getLogger(CommandProvider.class);
    private static final CommandProvider instance = new CommandProvider();
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private CommandProvider() {
        repository.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
        repository.put(CommandName.ADD_GENRE, new GenreAddCommand());
        repository.put(CommandName.SHOW_GENRES, new GenreShowAllCommand());
        repository.put(CommandName.CHANGE_GENRE, new GenreChangeCommand());
    }

    public Command getCommand(String name) {
        Command command;

        try {
            CommandName commandName = CommandName.valueOf(name);
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            log.info("No command with name", e);
            command = repository.get(CommandName.UNKNOWN_COMMAND);
        }
        return command;
    }

    public static CommandProvider getInstance() {
        return instance;
    }
}

