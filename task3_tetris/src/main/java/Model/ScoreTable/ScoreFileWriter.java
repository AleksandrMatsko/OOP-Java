package Model.ScoreTable;

import Model.Names.UserName;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ScoreFileWriter {
    private final String regex;
    private OutputStreamWriter outputStreamWriter = null;

    public ScoreFileWriter(String regex) throws IOException {
        this.regex = regex;
        outputStreamWriter = new OutputStreamWriter(new FileOutputStream("src/main/resources/scores.txt"));
    }

    public void close() {
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeScore(PairKeyVal<UserName, Integer> pairKeyVal) {
        try {
            outputStreamWriter.write(pairKeyVal.getKey().getName() + regex + pairKeyVal.getValue() + System.lineSeparator());
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}
