package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.NotEnoughDataInStackException;
import src.Exceptions.ValueExceptions.UseSqrtToNegativeValueException;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public class Sqrt implements Command {
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
    }
}
