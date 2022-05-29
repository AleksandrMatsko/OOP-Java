import CarFactory.CarFactory;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        CarFactory carFactory = new CarFactory();
        boolean isLogging = carFactory.isLogging();
        if (isLogging) {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
        }

        carFactory.startProduction();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        carFactory.stopProduction();
    }
}
