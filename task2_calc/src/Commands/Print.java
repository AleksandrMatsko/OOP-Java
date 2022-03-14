package src.Commands;

import src.DataContainers.StackWithDefinitionTable;

import java.util.List;

public class Print implements Command {
    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        double val = stackWithDefinitionTable.peekVal();
        System.out.println(val);
    }
}
