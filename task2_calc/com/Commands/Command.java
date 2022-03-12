package com.Commands;

import com.DataContainers.Data;

import java.util.Stack;

public interface Command {
    public void execute(Data<Double> data, String[] args);
}
