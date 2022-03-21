import Calculator.InputExecutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        final String loggerConfigFileName = "Log/logging.properties";
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream(loggerConfigFileName));
        }
        catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
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
            logger.log(Level.SEVERE, "Wrong amount of arguments in function main");
            System.exit(1);
        }
        try {
            InputExecutor inputExecutor = new InputExecutor(inputStream);
            inputExecutor.execute();
            logger.log(Level.FINE, "Stream successfully executed");
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    logger.log(Level.FINE, "Input stream successfully closed");
                }
                catch (IOException ex) {
                    logger.log(Level.SEVERE, "Exception: ", ex);
                }
            }
        }
    }
}
