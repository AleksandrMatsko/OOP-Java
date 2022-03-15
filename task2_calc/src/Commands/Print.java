package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.NotEnoughDataInStackException;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public class Print implements Command {
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
    }
}
