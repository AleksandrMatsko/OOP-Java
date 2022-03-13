package src.Commands;

import src.DataContainers.Data;

import java.util.List;

public class Sub implements Command {
    @Override
    public void execute(Data<Double> data, List<String> args) {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        double first = data.popVal();
        double second = data.popVal();
        data.pushVal(first + second);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.INOUT;
    }
}
