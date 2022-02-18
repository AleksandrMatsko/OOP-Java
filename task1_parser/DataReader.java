import java.io.InputStream;
import java.util.Scanner;

public class DataReader {
    private Scanner inputScanner;
    private String inputLine;
    private int stopIndex;

    public DataReader(InputStream in) {
        inputScanner = new Scanner(in);
        inputLine = "";
        stopIndex = 0;
    }

    private void updateLine() {
        inputLine = inputScanner.nextLine();
        stopIndex = 0;
    }

    public boolean isAvailable() {
        return inputScanner.hasNext() || stopIndex < inputLine.length();
    }

    public String getWord() {
        if (inputLine.isEmpty() || stopIndex == inputLine.length()) {
            updateLine();
        }
        String word = "";
        while (stopIndex < inputLine.length()) {
            if (word.isEmpty() && !Character.isLetterOrDigit(inputLine.charAt(stopIndex))) {
                stopIndex += 1;
                continue;
            }
            else if (Character.isLetterOrDigit(inputLine.charAt(stopIndex))) {
                word += inputLine.charAt(stopIndex);
                stopIndex += 1;
            }
            else if (!word.isEmpty() && !Character.isLetterOrDigit(inputLine.charAt(stopIndex))) {
                stopIndex += 1;
                break;
            }
            if (word.isEmpty() && stopIndex == inputLine.length()) {
                updateLine();
            }
        }
        return word;
    }

    public void close() {
        inputScanner.close();
    }
}
