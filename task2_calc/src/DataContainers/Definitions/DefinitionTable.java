package DataContainers.Definitions;

import java.util.HashMap;

public class DefinitionTable<TVal> {
    private final HashMap<DefinitionName, TVal> table;

    public DefinitionTable() {
        table = new HashMap<>();
    }

    public void add(DefinitionName name, TVal val) {
        if (table.containsKey(name)) {
            table.replace(name, val);
        }
        else {
            table.put(name,val);
        }
    }

    public TVal get(DefinitionName name) {
        return table.get(name);
    }

    public boolean contains(DefinitionName name) {
        return table.containsKey(name);
    }
}
