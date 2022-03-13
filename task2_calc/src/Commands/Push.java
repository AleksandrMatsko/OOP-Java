package src.Commands;

import src.DataContainers.Data;
import src.DataContainers.Definitions.DefinitionName;

import java.util.List;

public class Push implements Command {
    @Override
    public void execute(Data<Double> data, List<String> args) throws IllegalArgumentException {
        if (args.size() != 1) {
            //throw WrongAmountOfArguments;
        }
        double val;
        if (args.get(0).chars().allMatch(Character::isDigit)) {
            val = Double.parseDouble(args.get(0));
        }
        else {
            DefinitionName name = new DefinitionName(args.get(0));
            if (data.containsDefinitionName(name)) {
                val = data.getValByDefinition(name);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        data.pushVal(val);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.IN;
    }
}
