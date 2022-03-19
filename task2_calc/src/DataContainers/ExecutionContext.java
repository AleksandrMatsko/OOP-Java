package src.DataContainers;

import src.Commands.CommandName;
import src.Exceptions.NameExceptons.IllegalCommandNameException;

import java.util.List;

public class ExecutionContext {
    private final CommandName commandName;
    private final List<String> arguments;

    public ExecutionContext(List<String> parsedLine) throws IllegalCommandNameException {
        commandName = new CommandName(parsedLine.get(0));
        parsedLine.remove(0);
        arguments = parsedLine;
    }

    public CommandName getCommandName() {
        return commandName;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
