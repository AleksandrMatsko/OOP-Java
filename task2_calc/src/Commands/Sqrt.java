package src.Commands;

import src.DataContainers.Data;

import java.util.List;

public class Sqrt implements Command {
    @Override
    public void execute(Data<Double> data, List<String> args) {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        double val = data.popVal();
        if (val < 0) {
            //throw CannotUseSqrtToNegativeNum
        }
        data.pushVal(Math.sqrt(val));
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.INOUT;
    }
}
