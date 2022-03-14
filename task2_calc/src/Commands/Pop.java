package src.Commands;

import src.DataContainers.StackWithDefinitionTable;

import java.util.List;

public class Pop implements Command {
    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        stackWithDefinitionTable.popVal();
    }
}
