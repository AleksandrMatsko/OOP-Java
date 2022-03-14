package src.Commands;

import src.DataContainers.StackWithDefinitionTable;

import java.util.List;

public interface Command {
    void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args);
}
