package com.Commands;

import com.DataContainers.Data;

enum CommandType {
    IN,
    INOUT,
    OUT
}

public interface Command {
    void execute(Data<Double> data, String[] args);

    CommandType getCommandType();
}
