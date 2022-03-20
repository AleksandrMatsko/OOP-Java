package Commands;

import DataContainers.Definitions.DefinitionName;
import DataContainers.StackWithDefinitionTable;
import Exceptions.NameExceptons.IllegalDefinitionNameException;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.ValueExceptions.IllegalValueException;
import Exceptions.WrongAmountOfArgumentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {
    private StackWithDefinitionTable<Double> stackWithDefinitionTable;
    private Command cmd;
    private List<String> arguments;

    @BeforeEach
    void setUp() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
        cmd = new Push();
        arguments = new ArrayList<>();
    }

    @Test
    @DisplayName("Testing PUSH with less arguments than expected")
    public void testLessArguments() {
        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected WrongAmountOfArgumentsException");
        }
        catch (WrongAmountOfArgumentsException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | IllegalDefinitionNameException | IllegalValueException ex) {
            fail("Expected WrongAmountOfArgumentsException");
        }
    }

    @Test
    @DisplayName("Testing PUSH with more arguments than expected")
    public void testMoreArguments() {
        arguments.add("A");
        arguments.add("1");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected WrongAmountOfArgumentsException");
        }
        catch (WrongAmountOfArgumentsException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | IllegalDefinitionNameException | IllegalValueException ex) {
            fail("Expected WrongAmountOfArgumentsException");
        }
    }

    @Test
    @DisplayName("Testing PUSH with invalid value")
    public void testWithInvalidValue() {
        arguments.add("A");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected IllegalValueException");
        }
        catch (IllegalValueException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | IllegalDefinitionNameException | WrongAmountOfArgumentsException ex) {
            fail("Expected IllegalValueException");
        }
    }

    @Test
    @DisplayName("Testing PUSH with illegal definition name")
    public void testWithIllegalDefinition() {
        arguments.add("A b");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected IllegalDefinitionNameException");
        }
        catch (IllegalDefinitionNameException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | IllegalValueException | WrongAmountOfArgumentsException ex) {
            fail("Expected IllegalDefinitionNameException");
        }
    }

    @Test
    @DisplayName("Testing PUSH with correct definition")
    public void testWithCorrectDefinition() {
        stackWithDefinitionTable.addNewDefinition(new DefinitionName("A"), 2.0);
        arguments.add("A");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
        }
        catch (IllegalValueException | NotEnoughDataInStackException | IllegalDefinitionNameException | WrongAmountOfArgumentsException ex) {
            fail("No exception was expected ");
        }

        assertEquals(stackWithDefinitionTable.peekVal(), 2.0);
    }

    @Test
    @DisplayName("Testing PUSH with correct data")
    public void testWithCorrectData() {
        arguments.add("2");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
        }
        catch (IllegalValueException | NotEnoughDataInStackException | IllegalDefinitionNameException | WrongAmountOfArgumentsException ex) {
            fail("No exception was expected ");
        }

        assertEquals(stackWithDefinitionTable.peekVal(), 2.0);
    }
}