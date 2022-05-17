package Model.ScoreTable;

import Model.Names.UserName;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreFileParser {
    private final Scanner scanner;
    private final String regex;

    public ScoreFileParser(String regex) {
        this.regex = regex;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("scores.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner = new Scanner(fileInputStream);
    }

    public boolean hasNextRecord() {
        return scanner.hasNextLine();
    }

    public PairKeyVal<UserName, Integer> getPair() {
        if (hasNextRecord()) {
            String line = scanner.nextLine();
            String[] parsedLine = line.split(regex);
            // TODO exception
            return new PairKeyVal<>(new UserName(parsedLine[0]), Integer.getInteger(parsedLine[1]));
        }
        scanner.close();
        return null;
    }

    public void close() {
        scanner.close();
    }

}
