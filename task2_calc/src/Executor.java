package src;

import src.Commands.*;
import src.DataContainers.StackWithDefinitionTable;
import src.Factory.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

public class Executor {

    public void executeInput(InputStream in) {
        InputParser parser = new InputParser(in);
        StackWithDefinitionTable<Double> stackWithDefinitionTable = new StackWithDefinitionTable<>();
        while (parser.isAvailable()) {
            List<String> parsedLine = parser.parse();
            if (parsedLine.get(0).toLowerCase(Locale.ROOT).equals("stop") || parsedLine.get(0).toLowerCase(Locale.ROOT).equals("q")) {
                break;
            }
            CommandName commandName = null;
            try {
                commandName = new CommandName(parsedLine.get(0));
            }
            catch (IllegalArgumentException ex) {

            }
            Command cmd = null;
            try {
                cmd = CommandFactory.getInstance().getCommand(commandName);
            }
            catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            parsedLine.remove(0);
            try {
                cmd.execute(stackWithDefinitionTable, parsedLine);
            }
            catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }

        }
    }
}
