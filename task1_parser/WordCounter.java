import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

class SetKeyVal<TKey> {
    private TreeSet<PairKeyVal<TKey, Integer>> container;

    public SetKeyVal(Comparator<PairKeyVal<TKey, Integer>> comparator) {
        container = new TreeSet<>(comparator);
    }

    public boolean add(TKey key) {
        int keyCount = 0;
        Iterator<PairKeyVal<TKey, Integer>> it = container.iterator();
        while (it.hasNext()) {
            PairKeyVal<TKey, Integer> cell = it.next();
            if (cell.getKey().equals(key)) {
                keyCount = cell.getValue();
                break;
            }
        }
        container.remove(new PairKeyVal<>(key, keyCount));
        return container.add(new PairKeyVal<>(key, keyCount + 1));
    }

    public Object[] toArray() {
        return container.toArray();
    }
}

public class WordCounter {
    private SetKeyVal<String> statistics;
    private int totalWords;

    Comparator<PairKeyVal<String, Integer>> cmp = new Comparator<PairKeyVal<String, Integer>>() {
        @Override
        public int compare (PairKeyVal< String, Integer > o1, PairKeyVal< String, Integer > o2) {
            int res = o2.getValue() - o1.getValue();
            if (res != 0) {
                return res;
            }
            else {
                return o1.getKey().compareTo(o2.getKey());
            }
        }
    };

    public WordCounter() {
        statistics = new SetKeyVal<>(cmp);
        totalWords = 0;
    }

    public void collectStatistics(InputStream inputStream) {
        DataReader dataReader = new DataReader(inputStream);
        while (dataReader.isAvailable()) {
            String word = dataReader.getWord();
            if (!word.isEmpty()) {
                statistics.add(word);
                totalWords += 1;
            }
        }
        dataReader.close();
    }

    public void releaseStatistics(OutputStream outputStream, char delimiter) {
        Object[] toRelease = statistics.toArray();
        DataWriter dataWriter = new DataWriter(outputStream, totalWords, delimiter);
        for (int i = 0; i < toRelease.length; i++) {
            PairKeyVal<String, Integer> cell = (PairKeyVal<String, Integer>) toRelease[i];
            dataWriter.writeDataLine(cell.getKey(), cell.getValue());
        }
        dataWriter.writeTotalWords();
        dataWriter.close();
    }
}
