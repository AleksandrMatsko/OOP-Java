package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public interface Command {
    void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws WrongAmountOfArgumentsException;
}
