package Calculator;

import DataContainers.ExecutionContext;
import Exceptions.NameExceptons.IllegalCommandNameException;
import Parser.InputParser;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputExecutor {
    private static final Logger logger = Logger.getLogger(InputExecutor.class.getName());
    private final InputParser inputParser;
    private final Calculator calculator;

    public InputExecutor(InputStream inputStream) {
        inputParser = new InputParser(inputStream);
        calculator = new Calculator();
    }

    public void execute() {
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
                logger.warning("Entered invalid command name.");
                continue;
            }
            logger.log(Level.FINE, "Execution context successfully created");
            calculator.executeWithContext(executionContext);
        }
    }


}
