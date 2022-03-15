package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.NotEnoughDataInStackException;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public class Pop implements Command {
    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException {
        if (args.size() != 0) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in POP.", 0, args.size());
        }
        if (stackWithDefinitionTable.sizeOfStack() < 1) {
            throw new NotEnoughDataInStackException("Not enough arguments for unary operation POP.", 1, stackWithDefinitionTable.sizeOfStack());
        }
        stackWithDefinitionTable.popVal();
    }
}
