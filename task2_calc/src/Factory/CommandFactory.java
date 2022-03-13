package src.Factory;

import src.Commands.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
            System.err.println(commandName.toString() + " " + className);
            try {
                commandsTable.put(commandName, (Command) Class.forName(className).getDeclaredConstructor().newInstance());
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry entry: commandsTable.entrySet()) {
            System.err.println(entry.getKey().toString() + " " + entry.getValue().getClass().getName());
        }
    }

    public static CommandFactory getInstance() throws IOException, ClassNotFoundException {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    public Command getCommand(CommandName name) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!commandsTable.containsKey(name)) {
            //throw CommandNotFound
        }
        /*Command cmd;
        cmd = (Command) commandsTable.get(name);*/
        return commandsTable.get(name);
    }
}
