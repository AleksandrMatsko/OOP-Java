package com.Commands;

import com.DataContainers.Data;

public class Pop implements Command {
    @Override
    public void execute(Data<Double> data, String[] args) {
        if (args.length != 0) {
            //throw WrongAmountOfArguments;
        }
        data.popVal();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.OUT;
    }


}
