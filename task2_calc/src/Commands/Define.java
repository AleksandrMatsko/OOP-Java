package src.Commands;

import src.DataContainers.StackWithDefinitionTable;
import src.DataContainers.Definitions.DefinitionName;
import src.Exceptions.NameExceptons.IllegalDefinitionNameException;
import src.Exceptions.ValueExceptions.IllegalValueException;
import src.Exceptions.WrongAmountOfArgumentsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Define implements Command {
    private final static Logger logger = Logger.getLogger(Define.class.getName());

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            IllegalDefinitionNameException,
            IllegalValueException {
        if (args.size() != 2) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in DEFINE.", 2, args.size());
        }
        DefinitionName definitionName = null;
        try {
            definitionName = new DefinitionName(args.get(0));
        }
        catch (IllegalDefinitionNameException ex) {
            throw new IllegalDefinitionNameException("Invalid definition name in DEFINE.");
        }
        double val;
        if (args.get(1).chars().allMatch(Character::isDigit) || args.get(1).contains(".") || args.get(1).contains("-")) {
            val = Double.parseDouble(args.get(1));
        }
        else {
            throw new IllegalValueException("Invalid value in DEFINE.");
        }
        stackWithDefinitionTable.addNewDefinition(definitionName, val);
        logger.log(Level.FINE, "DEFINE successfully executed");
    }
}
