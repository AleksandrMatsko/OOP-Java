package src.Commands;

import src.DataContainers.StackWithDefinitionTable;

import java.util.EmptyStackException;
import java.util.List;

public class Sum implements Command {
    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws EmptyStackException {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        double first = stackWithDefinitionTable.popVal();
        double second = stackWithDefinitionTable.popVal();
        stackWithDefinitionTable.pushVal(first + second);
    }
}
