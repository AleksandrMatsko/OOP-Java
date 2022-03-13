package src.Commands;

import src.DataContainers.Data;

import java.util.List;

public class Print implements Command {
    @Override
    public void execute(Data<Double> data, List<String> args) {
        if (args.size() != 0) {
            //throw WrongAmountOfArguments;
        }
        double val = data.peekVal();
        System.out.println(val);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.OUT;
    }
}
