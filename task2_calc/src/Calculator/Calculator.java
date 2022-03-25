package Calculator;

import Commands.*;
import DataContainers.ExecutionContext;
import DataContainers.StackWithDefinitionTable;
import Exceptions.CommandNotFoundException;
import Exceptions.FileExceptions.CannotOpenConfigFileException;
import Exceptions.NameExceptons.IllegalCommandNameException;
import Exceptions.NameExceptons.IllegalDefinitionNameException;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.ValueExceptions.DivisionByZeroException;
import Exceptions.ValueExceptions.IllegalValueException;
import Exceptions.ValueExceptions.UseSqrtToNegativeValueException;
import Exceptions.WrongAmountOfArgumentsException;
import Factory.CommandFactory;

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
            logger.warning(ex.getMessage());
        }
        if (cmd != null) {
            logger.log(Level.FINE, "Command successfully created");

            try {
                cmd.execute(stackWithDefinitionTable, executionContext.getArguments());
            }
            catch (WrongAmountOfArgumentsException ex) {
                logger.warning(ex.getMessage() + " Expected - " + ex.getExpected() + ", Has - " + ex.getHas());
            }
            catch (NotEnoughDataInStackException ex) {
                logger.warning(ex.getMessage() + " Required at least - " + ex.getRequired() + ", Provided - " + ex.getProvided());
            }
            catch (IllegalDefinitionNameException ex) {
                logger.warning(ex.getMessage());
            }
            catch (UseSqrtToNegativeValueException ex) {
                logger.warning(ex.getMessage() + " Value - " + ex.getVal());
            }
            catch (DivisionByZeroException ex) {
                logger.warning(ex.getMessage() + " Dividend - " + ex.getVal());
            }
            catch (IllegalValueException ex) {
                logger.warning(ex.getMessage());
            }
        }
    }
}
