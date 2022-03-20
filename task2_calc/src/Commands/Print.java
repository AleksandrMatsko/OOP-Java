package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.WrongAmountOfArgumentsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Print implements Command {
    private final static Logger logger = Logger.getLogger(Print.class.getName());

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException {
        if (args.size() != 0) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in PRINT.", 0, args.size());
        }
        if (stackWithDefinitionTable.sizeOfStack() < 1) {
            throw new NotEnoughDataInStackException("Not enough arguments for unary operation PRINT.", 1, stackWithDefinitionTable.sizeOfStack());
        }
        double val = stackWithDefinitionTable.peekVal();
        System.out.println(val);
        logger.log(Level.FINE, "PRINT successfully executed");
    }
}
