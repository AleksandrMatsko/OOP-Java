package src.DataContainers;

import src.DataContainers.Definitions.*;

import java.util.EmptyStackException;
import java.util.Stack;

public class Data<TVal> {
    private final Stack<TVal> stack;
    private final DefinitionTable<TVal> definitionTable;

    public Data() {
        stack = new Stack<>();
        definitionTable = new DefinitionTable<>();
    }

    public void pushVal(TVal val) {
        stack.push(val);
    }

    public TVal popVal() throws EmptyStackException {
        return stack.pop();
    }

    public TVal peekVal() throws EmptyStackException {
        return stack.peek();
    }

    public int sizeOfStack() {
        return stack.size();
    }

    public boolean isEmptyStack() {
        return stack.isEmpty();
    }

    public void addNewDefinition(DefinitionName name, TVal val) {
        definitionTable.add(name, val);
    }

    public boolean containsDefinitionName(DefinitionName name) {
        return definitionTable.contains(name);
    }

    public TVal getValByDefinition(DefinitionName name) {
        return definitionTable.get(name);
    }

}
