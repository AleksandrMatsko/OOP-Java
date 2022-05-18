package Model.ScoreTable;

import Model.Names.UserName;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ScoreFileWriter {
    private final String regex;
    private OutputStreamWriter outputStreamWriter = null;

    public ScoreFileWriter(String regex) {
        this.regex = regex;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream("src/main/java/Model/ScoreTable/scores.txt"));
        }
        catch (IOException ex) {
            // TODO normal exception
        }
    }

    public void close() {
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            // TODO exception
            throw new RuntimeException(e);
        }
    }

    public void writeScore(PairKeyVal<UserName, Integer> pairKeyVal) {
        try {
            outputStreamWriter.write(pairKeyVal.getKey().getName() + regex + pairKeyVal.getValue() + System.lineSeparator());
        }
        catch (IOException ex) {
            // TODO exception
            throw new RuntimeException(ex);
        }
    }


}
