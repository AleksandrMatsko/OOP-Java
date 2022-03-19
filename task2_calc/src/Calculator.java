package src;

import src.Commands.*;
import src.DataContainers.ExecutionContext;
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


public class Calculator {
    private final StackWithDefinitionTable<Double> stackWithDefinitionTable;

    public Calculator() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
    }

    public void executeWithContext(ExecutionContext executionContext) {
        try {
            CommandFactory.getInstance();
        }
        catch (CannotOpenConfigFileException ex) {
            System.err.println(ex.getMessage());
        }
        Command cmd = null;
        try {
            cmd = CommandFactory.getInstance().getCommand(executionContext.getCommandName());
        }
        catch (IllegalCommandNameException | CannotOpenConfigFileException | CommandNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        if (cmd != null) {
            try {
                cmd.execute(stackWithDefinitionTable, executionContext.getArguments());
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
