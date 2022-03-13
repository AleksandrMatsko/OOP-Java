package src;

import src.Commands.*;
import src.DataContainers.Data;
import src.Factory.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Executor {

    public void executeInput(InputStream in) {
        InputParser parser = new InputParser(in);
        Data<Double> data = new Data<>();
        while (true) {
            List<String> parsedLine = parser.parse();
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
            //try {
                cmd.execute(data, parsedLine);
            //}
            //catch () {

            //}

        }
    }
}
