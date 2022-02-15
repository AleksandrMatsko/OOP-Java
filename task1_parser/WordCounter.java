import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;

public class WordCounter {
    private TreeSetOfPairs<String, Integer> statistics;
    private int totalWords;

    Comparator<Pair<String, Integer>> addCmp = new Comparator<Pair<String, Integer>>() {
        @Override
        public int compare (Pair< String, Integer > o1, Pair< String, Integer > o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    };

    public WordCounter() {
        statistics = new TreeSetOfPairs<>(addCmp);
        totalWords = 0;
    }

    public void collectStatistics(InputStream inputStream) {
        DataReader dataReader = new DataReader(inputStream);
        while (dataReader.isAvailable()) {
            String word = dataReader.getWord();
            if (!word.isEmpty()) {
                if (!statistics.add(word, 1)) {
                    int val = statistics.getCell(word, 1).getValue();
                    statistics.getCell(word, 1).setValue(val + 1);
                }
                totalWords += 1;
            }
        }
        dataReader.close();
    }

    Comparator<Pair<String, Integer>> sortCmp = new Comparator<Pair<String, Integer>>() {
        @Override
        public int compare (Pair< String, Integer > o1, Pair< String, Integer > o2) {
            int res = o2.getValue() - o1.getValue();
            if (res != 0) {
                return res;
            }
            else {
                return o1.getKey().compareTo(o2.getKey());
            }
        }
    };

    public void releaseStatistics(OutputStream outputStream, char delimiter) {
        Object[] toRelease = statistics.toArray();
        //sort result
        DataWriter dataWriter = new DataWriter(outputStream, totalWords, delimiter);
        for (int i = 0; i < toRelease.length; i++) {
            Pair<String, Integer> cell = (Pair<String, Integer>) toRelease[i];
            dataWriter.writeDataLine(cell.getKey(), cell.getValue());
        }
        dataWriter.writeTotalWords();
        dataWriter.close();
    }
}
