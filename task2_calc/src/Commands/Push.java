package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.DataContainers.Definitions.DefinitionName;

import java.util.List;

public class Push implements Command {
    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws IllegalArgumentException {
        if (args.size() != 1) {
            //throw WrongAmountOfArguments;
        }
        double val;
        if (args.get(0).chars().allMatch(Character::isDigit) || args.get(0).contains(".") || args.get(0).contains("-")) {
            val = Double.parseDouble(args.get(0));
        }
        else {
            DefinitionName name = new DefinitionName(args.get(0));
            if (stackWithDefinitionTable.containsDefinitionName(name)) {
                val = stackWithDefinitionTable.getValByDefinition(name);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        stackWithDefinitionTable.pushVal(val);
    }
}
