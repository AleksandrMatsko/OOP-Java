package src.Commands;

import src.DataContainers.Data;

import java.util.List;

public class Pop implements Command {
    @Override
    public void execute(Data<Double> data, List<String> args) {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        data.popVal();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.OUT;
    }
}
