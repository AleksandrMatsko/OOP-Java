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

    public void writeDataLine(String word, int numOfWord) {
        try {
            writer.write(word + delimiter);
            double frequency = numOfWord / (double)totalWords;
            writer.write(String.valueOf(frequency) + delimiter);
            writer.write(String.valueOf(frequency * 100) + '%' + delimiter + System.lineSeparator());
        }
        catch (IOException ex) {
            System.err.println("Error while writing data - " + ex.getLocalizedMessage());
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

    public void writeTotalWords() {
        try {
            writer.write("Total words:" + delimiter + totalWords + System.lineSeparator());
        }
        catch (IOException ex) {
            System.err.println("Error while writing data - " + ex.getLocalizedMessage());
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }

        }
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
