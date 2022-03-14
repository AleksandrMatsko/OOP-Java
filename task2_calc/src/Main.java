package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Executor executor = new Executor();
        if (args.length == 0) {
            executor.executeInput(System.in);
        }
        else if (args.length == 1) {
            InputStream fileStream = null;
            try {
                fileStream = new FileInputStream(args[0]);
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            executor.executeInput(fileStream);
        }
        else {
            //throw new WrongAmountOfArguments
        }

    }
}
