package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.WrongAmountOfArgumentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintTest {
    private StackWithDefinitionTable<Double> stackWithDefinitionTable;
    private Command cmd;
    private List<String> arguments;

    @BeforeEach
    void setUp() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
        cmd = new Print();
        arguments = new ArrayList<>();
    }

    @Test
    @DisplayName("Testing PRINT with more arguments than expected")
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
    @DisplayName("Testing PRINT with empty stack")
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
    @DisplayName("Testing PRINT with correct data")
    public void testCorrectData() {
        stackWithDefinitionTable.pushVal(1.9);

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
        }
        catch (WrongAmountOfArgumentsException | NotEnoughDataInStackException ex) {
            fail("No exception was expected");
        }

        assertEquals(stackWithDefinitionTable.peekVal(), 1.9);
    }
}