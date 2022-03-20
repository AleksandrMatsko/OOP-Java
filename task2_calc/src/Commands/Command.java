package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.NameExceptons.IllegalNameException;
import Exceptions.ValueExceptions.IllegalValueException;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public interface Command {
    void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            NotEnoughDataInStackException,
            IllegalNameException,
            IllegalValueException;
}
