package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.WrongAmountOfArgumentsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sub implements Command {
    private final static Logger logger = Logger.getLogger(Sub.class.getName());

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException {
        if (args.size() != 0) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in SUB.", 0, args.size());
        }
        if (stackWithDefinitionTable.sizeOfStack() < 2) {
            throw new NotEnoughDataInStackException("Not enough arguments for binary operation SUB.", 2, stackWithDefinitionTable.sizeOfStack());
        }
        double first = stackWithDefinitionTable.popVal();
        double second = stackWithDefinitionTable.popVal();
        stackWithDefinitionTable.pushVal(first - second);
        logger.log(Level.FINE, "SUB successfully executed");
    }
}
