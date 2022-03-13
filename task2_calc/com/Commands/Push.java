package com.Commands;

import com.DataContainers.Data;
import com.DataContainers.DefinitionName;

public class Push implements Command {

    @Override
    public void execute(Data<Double> data, String[] args) throws IllegalArgumentException {
        if (args.length != 1) {
            //throw WrongAmountOfArguments;
        }
        double val;
        if (args[0].chars().allMatch(Character::isDigit)) {
            val = Double.parseDouble(args[0]);
        }
        else {
            DefinitionName name = new DefinitionName(args[0]);
            if (data.isContainDefinition(name)) {
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
