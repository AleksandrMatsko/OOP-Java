package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.NotEnoughDataInStackException;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mul implements Command {
    private final static Logger logger = Logger.getLogger(Mul.class.getName());

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException {
        if (args.size() != 0) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in MUL.", 0, args.size());
        }
        if (stackWithDefinitionTable.sizeOfStack() < 2) {
            throw new NotEnoughDataInStackException("Not enough arguments for binary operation MUL.", 2, stackWithDefinitionTable.sizeOfStack());
        }
        double first = stackWithDefinitionTable.popVal();
        double second = stackWithDefinitionTable.popVal();
        stackWithDefinitionTable.pushVal(first * second);
        logger.log(Level.FINE, "MUL successfully executed");
    }
}
