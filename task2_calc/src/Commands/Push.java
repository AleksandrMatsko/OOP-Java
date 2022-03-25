package Commands;

import DataContainers.StackWithDefinitionTable;
import DataContainers.Definitions.DefinitionName;
import Exceptions.NameExceptons.IllegalDefinitionNameException;
import Exceptions.ValueExceptions.IllegalValueException;
import Exceptions.WrongAmountOfArgumentsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Push implements Command {
    private final static Logger logger = Logger.getLogger(Push.class.getName());

    @Override
    public void execute(StackWithDefinitionTable<Double> stackWithDefinitionTable, List<String> args) throws
            WrongAmountOfArgumentsException,
            IllegalDefinitionNameException,
            IllegalValueException {
        if (args.size() != 1) {
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments in PUSH.", 1, args.size());
        }
        double val;
        if (args.get(0).chars().allMatch(Character::isDigit) || args.get(0).contains(".") || args.get(0).contains("-")) {
            val = Double.parseDouble(args.get(0));
        }
        else {
            DefinitionName name;
            try {
                name = new DefinitionName(args.get(0));
            }
            catch (IllegalDefinitionNameException ex) {
                throw new IllegalDefinitionNameException("Invalid definition name in PUSH.");
            }
            if (stackWithDefinitionTable.containsDefinitionName(name)) {
                val = stackWithDefinitionTable.getValByDefinition(name);
            }
            else {
                throw new IllegalValueException("Invalid value in PUSH.");
            }
        }
        stackWithDefinitionTable.pushVal(val);
        logger.log(Level.FINE, "PUSH successfully executed");
    }
}
