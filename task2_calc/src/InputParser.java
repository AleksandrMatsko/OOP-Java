package src;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private final InputStream in;

    public InputParser(InputStream in) {
        this.in = in;
    }

    public List<String> parse() {
        Scanner scanner = new Scanner(in);
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
