package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public class Div implements Command {
    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws WrongAmountOfArgumentsException {
        if (args.size() != 0) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in DIV", 0, args.size());
        }
        double first = stackWithDefinitionTable.popVal();
        double second = stackWithDefinitionTable.popVal();
        stackWithDefinitionTable.pushVal(first + second);
    }
}
