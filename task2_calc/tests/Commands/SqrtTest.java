package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.ValueExceptions.UseSqrtToNegativeValueException;
import Exceptions.WrongAmountOfArgumentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest {
    private StackWithDefinitionTable<Double> stackWithDefinitionTable;
    private Command cmd;
    private List<String> arguments;

    @BeforeEach
    void setUp() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
        cmd = new Sqrt();
        arguments = new ArrayList<>();
    }

    @Test
    @DisplayName("Testing SQRT with more arguments than expected")
    public void testMoreArguments() {
        arguments.add("A");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected WrongAmountOfArgumentsException");
        }
        catch (WrongAmountOfArgumentsException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | UseSqrtToNegativeValueException ex) {
            fail("Expected WrongAmountOfArgumentsException");
        }
    }

    @Test
    @DisplayName("Testing SQRT with empty stack")
    public void testEmptyStack() {
        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected NotEnoughDataInStackException");
        }
        catch (NotEnoughDataInStackException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (WrongAmountOfArgumentsException | UseSqrtToNegativeValueException ex) {
            fail("Expected NotEnoughDataInStackException");
        }
    }

    @Test
    @DisplayName("Testing SQRT with negative number")
    public void testSqrtWithNegativeNumber() {
        stackWithDefinitionTable.pushVal(-1.0);

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected UseSqrtToNegativeValueException");
        }
        catch (UseSqrtToNegativeValueException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (WrongAmountOfArgumentsException | NotEnoughDataInStackException ex) {
            fail("Expected UseSqrtToNegativeValueException");
        }
    }

    @Test
    @DisplayName("Testing SQRT with correct data")
    public void testCorrectData() {
        stackWithDefinitionTable.pushVal(2.25);

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
        }
        catch (WrongAmountOfArgumentsException | NotEnoughDataInStackException | UseSqrtToNegativeValueException ex) {
            fail("No exception was expected");
        }

        assertEquals(stackWithDefinitionTable.peekVal(), 1.5);
    }
}