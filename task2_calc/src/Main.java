package src;

import src.DataContainers.ExecutionContext;
import src.Exceptions.NameExceptons.IllegalCommandNameException;
import src.Parser.InputParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = null;
        if (args.length == 0) {
            inputStream = System.in;
        }
        else if (args.length == 1) {
            try {
                inputStream = new FileInputStream(args[0]);
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.err.println("Wrong amount of arguments in function main");
        }
        try {
            InputParser inputParser = new InputParser(inputStream);
            Calculator calculator = new Calculator();
            while (inputParser.isAvailable()) {
                List<String> parsedLine = inputParser.parse();
                if (parsedLine.size() == 0) {
                    System.err.println("Empty line");
                    continue;
                }
                if (parsedLine.get(0).toLowerCase(Locale.ROOT).equals("stop") || parsedLine.get(0).toLowerCase(Locale.ROOT).equals("q")) {
                    break;
                }
                else if (parsedLine.get(0).contains("#")) {
                    continue;
                }
                ExecutionContext executionContext;
                try {
                    executionContext = new ExecutionContext(parsedLine);
                }
                catch (IllegalCommandNameException ex) {
                    System.err.println("Entered invalid command name.");
                    continue;
                }
                calculator.executeWithContext(executionContext);
            }
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
