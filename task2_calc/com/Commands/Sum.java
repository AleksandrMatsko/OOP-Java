package com.Commands;

import com.DataContainers.Data;
import java.util.EmptyStackException;

public class Sum implements Command {
    @Override
    public void execute(Data<Double> data, String[] args) throws EmptyStackException {
        if (args.length != 0) {
            //throw WrongAmountOfArguments;
        }
        double first = data.popVal();
        double second = data.popVal();
        data.pushVal(first + second);
    }
}
