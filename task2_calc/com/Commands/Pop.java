package com.Commands;

import com.DataContainers.Data;

import java.util.Stack;

public class Pop implements Command {
    @Override
    public void execute(Data<Double> data, String[] args) {
        if (args.length != 0) {
            //throw WrongAmountOfArguments;
        }
        data.popVal();
    }
}
