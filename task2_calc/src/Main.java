import Calculator.Calculator;
import DataContainers.ExecutionContext;
import Exceptions.NameExceptons.IllegalCommandNameException;
import src.Parser.InputParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
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
            InputParser inputParser = new InputParser(inputStream);
            Calculator calculator = new Calculator();
            while (inputParser.isAvailable()) {
                List<String> parsedLine = inputParser.parse();
                logger.log(Level.FINE, "Line was parsed");
                if (parsedLine.get(0).toLowerCase(Locale.ROOT).equals("stop") || parsedLine.get(0).toLowerCase(Locale.ROOT).equals("q")) {
                    logger.log(Level.FINE, "Stop symbol sequence entered");
                    break;
                }
                else if (parsedLine.get(0).contains("#")) {
                    logger.log(Level.FINE, "Commentary");
                    continue;
                }
                ExecutionContext executionContext;
                try {
                    executionContext = new ExecutionContext(parsedLine);
                }
                catch (IllegalCommandNameException ex) {
                    logger.log(Level.SEVERE, "Exception: ", ex);
                    logger.warning("Entered invalid command name.");
                    continue;
                }
                logger.log(Level.FINE, "Execution context successfully created");
                calculator.executeWithContext(executionContext);
            }
            logger.log(Level.INFO, "Exit from loop");
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ex) {
                    logger.log(Level.SEVERE, "Exception: ", ex);
                    ex.printStackTrace();
                }
                logger.log(Level.FINE, "Input stream successfully closed");
            }
        }
    }
}
