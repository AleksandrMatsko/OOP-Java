package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.ValueExceptions.DivisionByZeroException;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.WrongAmountOfArgumentsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Div implements Command {
    private final static Logger logger = Logger.getLogger(Div.class.getName());

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException,
            DivisionByZeroException {
        if (args.size() != 0) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in DIV.", 0, args.size());
        }
        if (stackWithDefinitionTable.sizeOfStack() < 2) {
            throw new NotEnoughDataInStackException("Not enough arguments for binary operation DIV.", 2, stackWithDefinitionTable.sizeOfStack());
        }
        double first = stackWithDefinitionTable.popVal();
        double second = stackWithDefinitionTable.popVal();
        if (second == 0) {
            throw new DivisionByZeroException("Try to divide by zero.", first);
        }
        stackWithDefinitionTable.pushVal(first / second);
        logger.log(Level.FINE, "DIV successfully executed");
    }
}
