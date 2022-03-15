package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.Exceptions.NameExceptons.IllegalNameException;
import src.Exceptions.ValueExceptions.IllegalValueException;
import src.Exceptions.NotEnoughDataInStackException;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public interface Command {
    void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException,
            IllegalNameException,
            IllegalValueException;
}
