import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

class SetKeyVal<T_key> {
    private TreeSet<Pair<T_key, Integer>> container;

    public SetKeyVal(Comparator<Pair<T_key, Integer>> comparator) {
        container = new TreeSet<>(comparator);
    }

    public boolean add(T_key key) {
        int keyCount = 0;
        Iterator<Pair<T_key, Integer>> it = container.iterator();
        while (it.hasNext()) {
            Pair<T_key, Integer> cell = it.next();
            if (cell.getKey().equals(key)) {
                keyCount = cell.getValue();
                break;
            }
        }
        container.remove(new Pair<>(key, keyCount));
        return container.add(new Pair<>(key, keyCount + 1));
    }

    public Object[] toArray() {
        return container.toArray();
    }
}

public class WordCounter {
    private SetKeyVal<String> statistics;
    private int totalWords;

    Comparator<Pair<String, Integer>> cmp = new Comparator<Pair<String, Integer>>() {
        @Override
        public int compare (Pair < String, Integer > o1, Pair < String, Integer > o2) {
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

    public void collectStatistics(String inputFileName) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(inputFileName));
            int tmp;
            StringBuilder word = new StringBuilder();
            char sym = '/';
            while ((tmp = reader.read()) != -1) {
                sym = (char)tmp;
                if (Character.isLetterOrDigit(sym)) {
                    word.append(sym);
                }
                else if (!word.isEmpty()){
                    statistics.add(word.toString());
                    totalWords += 1;
                    word.setLength(0);
                }
            }
            if (Character.isLetterOrDigit(sym)) {
                statistics.add(word.toString());
                totalWords += 1;
            }

        }
        catch (IOException ex) {
            System.err.println("Error while reading file - " + ex.getLocalizedMessage());
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }
    }

    public void releaseStatistics(String outputFileName, char delimiter) {
        Object[] toRelease = statistics.toArray();
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outputFileName));
            for (int i = 0; i < toRelease.length; i++) {
                Pair<String, Integer> cell = (Pair<String, Integer>) toRelease[i];
                double frequent = (double)cell.getValue() / totalWords;
                writer.write(cell.getKey() + delimiter);
                writer.write(String.valueOf(frequent) + delimiter);
                writer.write((frequent * 100) + "%" + System.lineSeparator());
            }
            writer.write("Total words" + delimiter);
            writer.write(String.valueOf(totalWords));
            writer.write(System.lineSeparator());
        }
        catch (IOException ex) {
            System.err.println("Error while writing file - " + ex.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }
    }
}
