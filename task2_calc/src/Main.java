package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        InputStream stream = null;
        if (args.length == 0) {
            stream = System.in;
        }
        else if (args.length == 1) {
            try {
                stream = new FileInputStream(args[0]);
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.err.println("Wrong amount of arguments in function main");
        }
        try {
            calculator.executeInput(stream);
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
