package src;

import src.Commands.*;
import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.CommandNotFoundException;
import src.Exceptions.FileExceptions.CannotOpenConfigFileException;
import src.Exceptions.NameExceptons.IllegalCommandNameException;
import src.Exceptions.NameExceptons.IllegalDefinitionNameException;
import src.Exceptions.NotEnoughDataInStackException;
import src.Exceptions.ValueExceptions.DivisionByZeroException;
import src.Exceptions.ValueExceptions.IllegalValueException;
import src.Exceptions.ValueExceptions.UseSqrtToNegativeValueException;
import src.Exceptions.WrongAmountOfArgumentsException;
import src.Factory.CommandFactory;
import src.Parser.InputParser;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class Calculator {

    public void executeInput(InputStream in) {
        InputParser parser = new InputParser(in);
        StackWithDefinitionTable<Double> stackWithDefinitionTable = new StackWithDefinitionTable<>();
        try {
            CommandFactory.getInstance();
        }
        catch (CannotOpenConfigFileException ex) {
            System.err.println(ex.getMessage());
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        while (parser.isAvailable()) {
            List<String> parsedLine = parser.parse();
            if (parsedLine.get(0).toLowerCase(Locale.ROOT).equals("stop") || parsedLine.get(0).toLowerCase(Locale.ROOT).equals("q")) {
                break;
            }
            else if (parsedLine.get(0).contains("#")) {
                continue;
            }
            CommandName commandName = null;
            try {
                commandName = new CommandName(parsedLine.get(0));
            }
            catch (IllegalCommandNameException ex) {
                System.err.println("Entered invalid command name.");
                continue;
            }
            Command cmd = null;
            try {
                cmd = CommandFactory.getInstance().getCommand(commandName);
            }
            catch (IllegalCommandNameException | CannotOpenConfigFileException | CommandNotFoundException ex) {
                System.err.println(ex.getMessage());
                continue;
            }
            catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            parsedLine.remove(0);
            if (cmd != null) {
                try {
                    cmd.execute(stackWithDefinitionTable, parsedLine);
                }
                catch (WrongAmountOfArgumentsException ex) {
                    System.err.println(ex.getMessage() + " Expected - " + ex.getExpected() + ", Has - " + ex.getHas());
                }
                catch (NotEnoughDataInStackException ex) {
                    System.err.println(ex.getMessage() + " Required at least - " + ex.getRequired() + ", Provided - " + ex.getProvided());
                }
                catch (IllegalDefinitionNameException ex) {
                    System.err.println(ex.getMessage());
                }
                catch (UseSqrtToNegativeValueException ex) {
                    System.err.println(ex.getMessage() + " Value - " + ex.getVal());
                }
                catch (DivisionByZeroException ex) {
                    System.err.println(ex.getMessage() + " Dividend - " + ex.getVal());
                }
                catch (IllegalValueException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
