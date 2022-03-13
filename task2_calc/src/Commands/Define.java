package src.Commands;

import src.DataContainers.Data;
import src.DataContainers.Definitions.DefinitionName;

import java.util.List;

public class Define implements Command {

    @Override
    public void execute(Data<Double> data, List<String> args) {
        if (args.size() != 2) {
            //throw WrongAmountOfArguments
        }
        DefinitionName definitionName = null;
        try {
            definitionName = new DefinitionName(args.get(0));
        }
        catch (IllegalArgumentException ex) {

        }
        double val = 0;
        if (args.get(0).chars().allMatch(Character::isDigit)) {
            val = Double.parseDouble(args.get(0));
        }
        else {
            //throw IllegalArgumentException
        }
        data.addNewDefinition(definitionName, val);

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.NONE;
    }
}
