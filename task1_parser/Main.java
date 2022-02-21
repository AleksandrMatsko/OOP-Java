import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Error: not enough arguments");
            System.exit(1);
        }
        WordCounter parser = new WordCounter();
        InputStream inputStream = null;
        for (int i = 0; i < args.length - 1; i++) {
            try {
                inputStream = new FileInputStream(args[i]);
                parser.collectStatistics(inputStream);
            }
            catch (IOException ex) {
                System.err.println("Error while reading file - " + ex.getLocalizedMessage());
            }
            finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace(System.err);
                    }
                }
            }
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(args[args.length - 1]);
            parser.releaseStatistics(outputStream, ';');
        }
        catch (IOException ex) {
            System.err.println("Error while writing file - " + ex.getLocalizedMessage());
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }
    }
}
