import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DataWriter {
    private OutputStreamWriter writer;
    private int totalWords;
    private char delimiter;

    public DataWriter(OutputStream outputStream, int numOfWords, char delim) {
        writer = new OutputStreamWriter(outputStream);
        totalWords = numOfWords;
        delimiter = delim;
    }

    public void writeDataLine(String word, int numOfWord) throws IOException {
        writer.write(word + delimiter);
        double frequency = numOfWord / (double)totalWords;
        writer.write(String.valueOf(frequency) + delimiter);
        writer.write(String.valueOf(frequency * 100) + '%' + delimiter + System.lineSeparator());
    }

    public void writeTotalWords() throws IOException {
        writer.write("Total words:" + delimiter + totalWords + System.lineSeparator());
    }

    public void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

}
