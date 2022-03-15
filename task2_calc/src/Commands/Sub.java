package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.NotEnoughDataInStackException;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public class Sub implements Command {
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
    }
}
