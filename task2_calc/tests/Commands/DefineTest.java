package Commands;

import DataContainers.Definitions.DefinitionName;
import DataContainers.StackWithDefinitionTable;
import Exceptions.NameExceptons.IllegalDefinitionNameException;
import Exceptions.NotEnoughDataInStackException;
import Exceptions.ValueExceptions.IllegalValueException;
import org.junit.jupiter.api.BeforeEach;
import Exceptions.WrongAmountOfArgumentsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefineTest {
    private StackWithDefinitionTable<Double> stackWithDefinitionTable;
    private Command cmd;
    private List<String> arguments;

    @BeforeEach
    void setUp() {
        stackWithDefinitionTable = new StackWithDefinitionTable<>();
        cmd = new Define();
        arguments = new ArrayList<>();
    }

    @Test
    @DisplayName("Testing DEFINE with less arguments than expected")
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
    @DisplayName("Testing DEFINE with more arguments than expected")
    public void testMoreArguments() {
        arguments.add("A");
        arguments.add("1");
        arguments.add("2");

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
    @DisplayName("Testing DEFINE with incorrect definition name (contains space)")
    public void testIncorrectDefinitionNameWithSpace() {
        arguments.add("A b");
        arguments.add("1");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected IllegalDefinitionNameException");
        }
        catch (IllegalDefinitionNameException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | WrongAmountOfArgumentsException | IllegalValueException ex) {
            fail("Expected IllegalDefinitionNameException");
        }
    }

    @Test
    @DisplayName("Testing DEFINE with incorrect definition name (contains digits only)")
    public void testIncorrectDefinitionNameOnlyWithDigits() {
        arguments.add("2");
        arguments.add("1");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected IllegalDefinitionNameException");
        }
        catch (IllegalDefinitionNameException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | WrongAmountOfArgumentsException | IllegalValueException ex) {
            fail("Expected IllegalDefinitionNameException");
        }
    }

    @Test
    @DisplayName("Testing DEFINE with incorrect value")
    public void testIncorrectValue() {
        arguments.add("A");
        arguments.add("b");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
            fail("Expected IllegalValueException");
        }
        catch (IllegalValueException ex) {
            assertNotNull(ex.getMessage());
        }
        catch (NotEnoughDataInStackException | WrongAmountOfArgumentsException | IllegalDefinitionNameException ex) {
            fail("Expected IllegalValueException");
        }
    }

    @Test
    @DisplayName("Testing DEFINE with correct arguments")
    public void testCorrectInput() {
        arguments.add("A");
        arguments.add("1");

        try {
            cmd.execute(stackWithDefinitionTable, arguments);
        }
        catch (IllegalValueException | NotEnoughDataInStackException | WrongAmountOfArgumentsException | IllegalDefinitionNameException ex) {
            fail("No exception was expected");
        }

        assertEquals(stackWithDefinitionTable.getValByDefinition(new DefinitionName(arguments.get(0))),
                Double.parseDouble(arguments.get(1)));
    }
}