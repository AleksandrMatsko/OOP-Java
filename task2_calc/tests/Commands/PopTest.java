package Commands;

import static org.junit.jupiter.api.Assertions.*;
import DataContainers.StackWithDefinitionTable;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.WrongAmountOfArgumentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PopTest {
    private StackWithDefinitionTable<Double> stackWithDefinitionTable;
    private Command cmd;
    private List<String> arguments;

    @BeforeEach
    void setUp() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
        cmd = new Pop();
        arguments = new ArrayList<>();
    }

    @Test
    @DisplayName("Testing POP with more arguments than expected")
    public void testMoreArguments() {
        arguments.add("A");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected WrongAmountOfArgumentsException");
        }
        catch (WrongAmountOfArgumentsException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException ex) {
            fail("Expected WrongAmountOfArgumentsException");
        }
    }

    @Test
    @DisplayName("Testing POP with empty stack")
    public void testEmptyStack() {
        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected NotEnoughDataInStackException");
        }
        catch (NotEnoughDataInStackException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (WrongAmountOfArgumentsException ex) {
            fail("Expected NotEnoughDataInStackException");
        }
    }

    @Test
    @DisplayName("Testing POP with correct data")
    public void testCorrectData() {
        stackWithDefinitionTable.pushVal(1.9);
        int prevSize = stackWithDefinitionTable.sizeOfStack();

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
        }
        catch (WrongAmountOfArgumentsException | NotEnoughDataInStackException ex) {
            fail("No exception was expected");
        }

        assertEquals(stackWithDefinitionTable.sizeOfStack(), prevSize - 1);
    }
}