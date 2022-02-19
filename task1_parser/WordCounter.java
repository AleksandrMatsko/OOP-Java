import java.io.*;
import java.util.Comparator;

public class WordCounter {
    private TableKeyVal<String, Integer> statistics;
    private int totalWords;

    private Comparator<PairKeyVal<String, Integer>> addCmp = new Comparator<PairKeyVal<String, Integer>>() {
        @Override
        public int compare (PairKeyVal<String, Integer> o1, PairKeyVal<String, Integer> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    };

    private Comparator<PairKeyVal<String, Integer>> sortCmp = new Comparator<PairKeyVal<String, Integer>>() {
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
        statistics = new TableKeyVal<>(addCmp);
        totalWords = 0;
    }

    public void collectStatistics(InputStream inputStream) {
        DataReader dataReader = null;
        try {
            dataReader = new DataReader(inputStream);
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
        }
        finally {
            if (dataReader != null) {
                dataReader.close();
            }
        }
    }

    public void releaseStatistics(OutputStream outputStream, char delimiter) {
        DataWriter dataWriter = null;
        try {
            PairKeyVal<String, Integer>[] toRelease = statistics.getSortedData(sortCmp);
            dataWriter = new DataWriter(outputStream, totalWords, delimiter);
            for (int i = 0; i < toRelease.length; i++) {
                dataWriter.writeDataLine(toRelease[i].getKey(), toRelease[i].getValue());
            }
            dataWriter.writeTotalWords();
        }
        finally {
            if (dataWriter != null) {
                dataWriter.close();
            }
        }
    }
}
