import CarFactory.CarFactory;
import View.Viewer;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        Viewer viewer = new Viewer();
        CarFactory carFactory = new CarFactory(viewer);
        boolean isLogging = carFactory.isLogging();
        if (isLogging) {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
        }
        viewer.setTimeSetter(carFactory);
        viewer.setClosable(carFactory);
        carFactory.startProduction();
    }
}
