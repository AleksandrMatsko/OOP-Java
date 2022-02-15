import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetOfPairs<TKey, TVal> {
    private TreeSet<Pair<TKey, TVal>> table;

    public TreeSetOfPairs(Comparator<Pair<TKey, TVal>> comparator) {
        table = new TreeSet<>(comparator);
    }

    public boolean add(TKey key, TVal val) {
        return table.add(new Pair<>(key, val));
    }

    public Pair<TKey, TVal> getCell(TKey key, TVal val) {
        return table.floor(new Pair<>(key, val));
    }

    public Object[] toArray() {
        return table.toArray();
    }

    public TreeSet<Pair<TKey, TVal>> getRowData() {
        return table;
    }
 }
