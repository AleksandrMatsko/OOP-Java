import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;

class TableKeyVal<T_key> {
    private TreeSet<Pair<T_key, Integer>> container;

    public TableKeyVal(Comparator<Pair<T_key, Integer>> comparator) {
        container = new TreeSet<>(comparator);
    }

    /*public boolean contains(T_key key) {
        return container.contains();
    }*/

    public boolean add(T_key key, int amountOfWords) {
        int keyCount = 0;
        for (int i = 0; i < amountOfWords; i++) {
            if (container.contains(new Pair<>(key, i + 1))) {
                keyCount = i + 1;
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

public class Parser {
    private TableKeyVal<String> statistics;
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

    public Parser() {
        statistics = new TableKeyVal<>(cmp);
        totalWords = 0;
    }

    public void collectStatistics(String inputFileName) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(inputFileName));
            int tmp;
            String word = "";
            char sym = '/';
            while ((tmp = reader.read()) != -1) {
                sym = (char)tmp;
                if (Character.isLetterOrDigit(sym)) {
                    word += sym;
                }
                else if (!word.equals("")){
                    statistics.add(word, totalWords);
                    totalWords += 1;
                    word = "";
                }
            }
            if (Character.isLetterOrDigit(sym)) {
                statistics.add(word, totalWords);
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
