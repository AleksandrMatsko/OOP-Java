package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.ValueExceptions.UseSqrtToNegativeValueException;
import Exceptions.WrongAmountOfArgumentsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sqrt implements Command {
    private final static Logger logger = Logger.getLogger(Sqrt.class.getName());

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException,
            UseSqrtToNegativeValueException {
        if (args.size() != 0) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in SQR.T", 0, args.size());
        }
        if (stackWithDefinitionTable.sizeOfStack() < 1) {
            throw new NotEnoughDataInStackException("Not enough arguments for unary operation SQRT.", 1, stackWithDefinitionTable.sizeOfStack());
        }
        double val = stackWithDefinitionTable.popVal();
        if (val < 0) {
            throw new UseSqrtToNegativeValueException("Try to use SQRT to negative number.", val);
        }
        stackWithDefinitionTable.pushVal(Math.sqrt(val));
        logger.log(Level.FINE, "SQRT successfully executed");
    }
}
