package Commands;

import DataContainers.StackWithDefinitionTable;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.ValueExceptions.DivisionByZeroException;
import Exceptions.WrongAmountOfArgumentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DivTest {
    private StackWithDefinitionTable<Double> stackWithDefinitionTable;
    private Command cmd;
    private List<String> arguments;

    @BeforeEach
    void setUp() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
        cmd = new Div();
        arguments = new ArrayList<>();
    }

    @Test
    @DisplayName("Testing DIV with more arguments than expected")
    public void testMoreArguments() {
        arguments.add("A");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected WrongAmountOfArgumentsException");
        }
        catch (WrongAmountOfArgumentsException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | DivisionByZeroException ex) {
            fail("Expected WrongAmountOfArgumentsException");
        }
    }

    @Test
    @DisplayName("Testing DIV with not enough data in stack (0 arguments in stack)")
    public void testEmptyStack() {
        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected NotEnoughDataInStackException");
        }
        catch (NotEnoughDataInStackException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (WrongAmountOfArgumentsException | DivisionByZeroException ex) {
            fail("Expected NotEnoughDataInStackException");
        }
    }

    @Test
    @DisplayName("Testing DIV with not enough data in stack (1 arguments in stack)")
    public void testStackWithOneValue() {
        stackWithDefinitionTable.pushVal(0.0);

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected NotEnoughDataInStackException");
        }
        catch (NotEnoughDataInStackException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (WrongAmountOfArgumentsException | DivisionByZeroException ex) {
            fail("Expected NotEnoughDataInStackException");
        }
    }

    @Test
    @DisplayName("Testing DIV by zero")
    public void testDivisionByZero() {;
        stackWithDefinitionTable.pushVal(0.0);
        stackWithDefinitionTable.pushVal(1.5);

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected DivisionByZeroException");
        }
        catch (DivisionByZeroException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (WrongAmountOfArgumentsException | NotEnoughDataInStackException ex) {
            fail("Expected DivisionByZeroException");
        }
    }

    @Test
    @DisplayName("Testing DIV with correct data")
    public void testCorrectData() {
        stackWithDefinitionTable.pushVal(1.9);
        stackWithDefinitionTable.pushVal(4.56);

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
        }
        catch (DivisionByZeroException | WrongAmountOfArgumentsException | NotEnoughDataInStackException ex) {
            fail("No exception was expected");
        }

        assertEquals(stackWithDefinitionTable.peekVal(), 4.56 / 1.9);
    }
}