package src;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private final Scanner scanner;

    public InputParser(InputStream in) {
        scanner = new Scanner(in);
    }

    public boolean isAvailable() {
        return scanner.hasNextLine();
    }

    public List<String> parse() {
        String line = "";
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
        }
        else {
            //throw bad input exception
        }
        return new ArrayList<String>(Arrays.asList(line.split(" ")));
    }
}
