package com.Factory;

import com.Commands.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class CommandFactory {
    private final HashMap<CommandName, Command> commandsTable;
    private static CommandFactory factory = null;

    private CommandFactory() throws IOException, ClassNotFoundException {
        commandsTable = new HashMap<>();
        final String configFileName = "config/commands.ini";
        Properties properties = new Properties();
        properties.load(CommandFactory.class.getResourceAsStream(configFileName));
        /*try {
            //properties.load(CommandFactory.class.getResourceAsStream(configFileName));
        }
        catch (IIOException ex) {
            //throw CannotOpenConfigFile
        }*/
        for (Map.Entry entry: properties.entrySet()) {
            String toCommandName = (String) entry.getKey();
            String className = (String) entry.getValue();
            CommandName commandName = null;
            try {
                commandName = new CommandName(toCommandName);
            }
            catch (IllegalArgumentException ex) {
                //
            }
            if (commandName != null) {
                commandsTable.put(commandName, Class.forName(className));
                /*try {
                    //commandsTable.put(commandName, Class.forName(className));
                }
                catch (IIOException ex) {
                    //throw ex;
                }*/
            }
            else {
                //throw IllegalCommandName
            }
        }
    }

    public static CommandFactory getInstance() throws IOException, ClassNotFoundException {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    public Command getCommand(CommandName name) {
        if (!commandsTable.containsKey(name)) {
            //throw CommandNotFound
        }
        return commandsTable.get(name);
    }
}
