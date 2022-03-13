package src.Commands;

import src.DataContainers.Data;

import java.util.List;

enum CommandType {
    IN,
    INOUT,
    OUT,
    NONE
}

public interface Command {
    void execute(Data<Double> data, List<String> args);

    CommandType getCommandType();
}
