package src.Factory;

import src.Commands.*;
import src.Exceptions.CommandNotFoundException;
import src.Exceptions.FileExceptions.CannotOpenConfigFileException;
import src.Exceptions.NameExceptons.IllegalCommandNameException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class CommandFactory {
    private final HashMap<CommandName, Command> commandsTable;
    private static CommandFactory factory = null;

    private CommandFactory() throws CannotOpenConfigFileException, ClassNotFoundException, IllegalCommandNameException {
        commandsTable = new HashMap<>();
        final String configFileName = "config/commands.ini";
        Properties properties = new Properties();
        try {
            properties.load(CommandFactory.class.getResourceAsStream(configFileName));
        }
        catch (IOException ex) {
            throw new CannotOpenConfigFileException();
        }
        for (Map.Entry entry: properties.entrySet()) {
            String toCommandName = (String) entry.getKey();
            String className = (String) entry.getValue();
            CommandName commandName;
            try {
                commandName = new CommandName(toCommandName);
            }
            catch (IllegalCommandNameException ex) {
                throw new IllegalCommandNameException("Invalid command name in config file.");
            }
            try {
                commandsTable.put(commandName, (Command) Class.forName(className).getDeclaredConstructor().newInstance());
            }
            catch (ClassNotFoundException ex) {
                System.err.println("Cannot create class " + className + " for command " + commandName.toString() + ". Class not found");
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static CommandFactory getInstance() throws CannotOpenConfigFileException, ClassNotFoundException, IllegalCommandNameException {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    public Command getCommand(CommandName name) throws CommandNotFoundException {
        if (!commandsTable.containsKey(name)) {
            throw new CommandNotFoundException("Cannot find command with name " + name.toString());
        }
        return commandsTable.get(name);
    }
}
