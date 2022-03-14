package src.Commands;

import src.DataContainers.StackWithDefinitionTable;

import java.util.List;

public class Sub implements Command {
    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        double first = stackWithDefinitionTable.popVal();
        double second = stackWithDefinitionTable.popVal();
        stackWithDefinitionTable.pushVal(first + second);
    }
}
