import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TableKeyVal<TKey, TVal> {
    private TreeSet<PairKeyVal<TKey, TVal>> table;
    Comparator<PairKeyVal<TKey, TVal>> keyCmp;

    public TableKeyVal(Comparator<PairKeyVal<TKey, TVal>> keyComparator) {
        table = new TreeSet<>(keyComparator);
        keyCmp = keyComparator;
    }

    public boolean add(TKey key, TVal val) {
        return table.add(new PairKeyVal<>(key, val));
    }

    public PairKeyVal<TKey, TVal> getCell(TKey key, TVal val) {
        return table.floor(new PairKeyVal<>(key, val));
    }

    public PairKeyVal<TKey, TVal>[] getSortedData(Comparator<PairKeyVal<TKey, TVal>> reSortComparator) {
        TreeSet<PairKeyVal<TKey, TVal>> sortedData;
        if (reSortComparator != keyCmp) {
            sortedData = new TreeSet<>(reSortComparator);
            sortedData.addAll(table);
        }
        else {
            sortedData = table;
        }
        Iterator<PairKeyVal<TKey, TVal>> it = sortedData.iterator();
        PairKeyVal<TKey, TVal>[] array = (PairKeyVal<TKey, TVal>[]) new PairKeyVal[sortedData.size()];
        for (int i = 0; i < sortedData.size(); i++) {
            array[i] = it.next();
        }
        return array;
    }
 }
