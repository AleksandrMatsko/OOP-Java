package Model.ScoreTable;

import Exceptions.InvalidUserNameException;
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
            fileInputStream = new FileInputStream("src/main/resources/scores.txt");
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
            UserName userName = null;
            try {
                //TODO asking userName
                userName = new UserName(parsedLine[0]);
            }
            catch (InvalidUserNameException ex) {
                //TODO normal reaction
            }
            return new PairKeyVal<>(userName, Integer.valueOf(parsedLine[1]));
        }
        scanner.close();
        return null;
    }

    public void close() {
        scanner.close();
    }

}
