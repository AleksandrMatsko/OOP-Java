package Factory;

import Commands.*;
import Exceptions.CommandNotFoundException;
import Exceptions.FileExceptions.CannotOpenConfigFileException;
import Exceptions.NameExceptons.IllegalCommandNameException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandFactory {
    private static final Logger logger = Logger.getLogger(CommandFactory.class.getName());
    private final HashMap<CommandName, Command> commandsTable;
    private static CommandFactory factory = null;

    private CommandFactory() throws CannotOpenConfigFileException, IllegalCommandNameException {
        commandsTable = new HashMap<>();
        final String configFileName = "config/commands.ini";
        Properties properties = new Properties();
        try {
            properties.load(CommandFactory.class.getResourceAsStream(configFileName));
        }
        catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
            throw new CannotOpenConfigFileException();
        }
        logger.log(Level.FINE, "Config file with commands was read successfully");
        for (Map.Entry entry: properties.entrySet()) {
            String toCommandName = (String) entry.getKey();
            String className = (String) entry.getValue();
            CommandName commandName;
            try {
                commandName = new CommandName(toCommandName);
            }
            catch (IllegalCommandNameException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                throw new IllegalCommandNameException("Invalid command name in config file.");
            }
            try {
                commandsTable.put(commandName, (Command) Class.forName(className).getDeclaredConstructor().newInstance());
            }
            catch (ClassNotFoundException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                logger.warning("Cannot create class " + className + " for command " + commandName.toString() + ". Class not found");
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                //ex.printStackTrace();
            }
        }
    }

    public static CommandFactory getInstance() throws CannotOpenConfigFileException, IllegalCommandNameException {
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
