import CarFactory.CarFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CarFactory carFactory = new CarFactory();
        carFactory.startProduction();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        carFactory.stopProduction();
    }
}
