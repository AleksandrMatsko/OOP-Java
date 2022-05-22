package Model.ScoreTable;

import Exceptions.NameExceptions.InvalidUserNameException;
import Model.Names.UserName;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreFileParser {
    private final Scanner scanner;
    private final String regex;

    public ScoreFileParser(String regex) throws FileNotFoundException {
        this.regex = regex;
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/scores.txt");
        scanner = new Scanner(fileInputStream);
    }

    public boolean hasNextRecord() {
        return scanner.hasNextLine();
    }

    public PairKeyVal<UserName, Integer> getPair() throws InvalidUserNameException {
        if (hasNextRecord()) {
            String line = scanner.nextLine();
            String[] parsedLine = line.split(regex);
            UserName userName = new UserName(parsedLine[0]);
            return new PairKeyVal<>(userName, Integer.valueOf(parsedLine[1]));
        }
        scanner.close();
        return null;
    }

    public void close() {
        scanner.close();
    }

}
