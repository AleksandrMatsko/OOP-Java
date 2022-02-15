import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Error: not enough arguments");
            System.exit(1);
        }
        WordCounter parser = new WordCounter();
        for (int i = 0; i < args.length - 1; i++) {
            try {
                InputStream inputStream = new FileInputStream(args[i]);
                parser.collectStatistics(inputStream);
            }
            catch (IOException ex) {
                System.err.println("Error while reading file - " + ex.getLocalizedMessage());
            }
        }

        try {
            OutputStream outputStream = new FileOutputStream(args[args.length - 1]);
            parser.releaseStatistics(outputStream, ';');
        }
        catch (IOException ex) {
            System.err.println("Error while writing file - " + ex.getLocalizedMessage());
        }
    }
}
