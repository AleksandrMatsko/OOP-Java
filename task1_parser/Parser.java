import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Comparator;
import java.util.TreeSet;

class TableKeyVal<T_key, T_val> {
    private TreeSet<Pair<T_key, T_val>> container;

    public TableKeyVal(Comparator<Pair<T_key, T_val>> comparator) {
        container = new TreeSet<Pair<T_key, T_val>>(comparator);
    }

    public boolean contains(T_key key) {
        return container.contains(new Pair<T_key, T_val>());
    }
}

public class Parser {
    private TableKeyVal<String, Integer> statistics;
    private int totalWords;

    Comparator<Pair<String, Integer>> cmp = new Comparator<Pair<String, Integer>>(){
        @Override
        public int compare (Pair < String, Integer > o1, Pair < String, Integer > o2) {
            return o1.getValue() - o2.getValue();
        }
    };

    public Parser() {
        this.statistics = new TableKeyVal<>(cmp);
        this.totalWords = 0;
    }

    public void collectStatistics(String inputFileName) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(inputFileName));
            int tmp;
            String word = "";
            while ((tmp = reader.read()) != -1) {
                char sym = (char)tmp;
                if (Character.isLetterOrDigit(sym)) {
                    word += sym;
                }
                else {
                    if (statistics.contains(word)) {

                    }
                    else {
                        statistics.add(new Pair<>(word, 1));
                    }
                    word = "";
                }
            }
        }
        catch (IOException ex) {
            System.err.println("Error while reading file - " + ex.getLocalizedMessage());
        }

    }

    public void releaseStatistics(String outFileName) {

    }
}