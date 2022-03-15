package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.DataContainers.Definitions.DefinitionName;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;

public class Define implements Command {

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws IllegalArgumentException, WrongAmountOfArgumentsException {
        if (args.size() != 2) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in DEFINE", 2, args.size());
        }
        DefinitionName definitionName = null;
        try {
            definitionName = new DefinitionName(args.get(0));
        }
        catch (IllegalArgumentException ex) {

        }
        double val;
        if (args.get(1).chars().allMatch(Character::isDigit) || args.get(1).contains(".") || args.get(1).contains("-")) {
            val = Double.parseDouble(args.get(1));
        }
        else {
            throw new IllegalArgumentException();
        }
        stackWithDefinitionTable.addNewDefinition(definitionName, val);
    }
}
