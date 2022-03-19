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

import java.util.logging.Level;
import java.util.logging.Logger;


public class Calculator {
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());
    private final StackWithDefinitionTable<Double> stackWithDefinitionTable;

    public Calculator() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
    }

    public void executeWithContext(ExecutionContext executionContext) {
        Command cmd = null;
        try {
            cmd = CommandFactory.getInstance().getCommand(executionContext.getCommandName());
        }
        catch (IllegalCommandNameException | CannotOpenConfigFileException | CommandNotFoundException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
            System.err.println(ex.getMessage());
        }
        if (cmd != null) {
            logger.log(Level.FINE, "Command successfully created");

            try {
                cmd.execute(stackWithDefinitionTable, executionContext.getArguments());
            }
            catch (WrongAmountOfArgumentsException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                System.err.println(ex.getMessage() + " Expected - " + ex.getExpected() + ", Has - " + ex.getHas());
            }
            catch (NotEnoughDataInStackException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                System.err.println(ex.getMessage() + " Required at least - " + ex.getRequired() + ", Provided - " + ex.getProvided());
            }
            catch (IllegalDefinitionNameException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                System.err.println(ex.getMessage());
            }
            catch (UseSqrtToNegativeValueException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                System.err.println(ex.getMessage() + " Value - " + ex.getVal());
            }
            catch (DivisionByZeroException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                System.err.println(ex.getMessage() + " Dividend - " + ex.getVal());
            }
            catch (IllegalValueException ex) {
                logger.log(Level.SEVERE, "Exception: ", ex);
                System.err.println(ex.getMessage());
            }
        }
    }
}
