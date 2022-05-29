package CarFactory.Staff;

import CarFactory.CarFactory;
import CarFactory.Car;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Dealer extends Thread {
    private static final Logger logger = Logger.getLogger(Dealer.class.getName());
    private int period;
    private CarFactory carFactory;


    public Dealer(CarFactory carFactory, int period) {
        this.carFactory = carFactory;
        this.period = period;
    }

    public synchronized int getPeriod() {
        return period;
    }

    public synchronized void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(period);
                Car car = carFactory.getCar();
                if (carFactory.isLogging()) {
                    logger.log(Level.FINE, "Sold: " + car.getFullInfo());
                }
                System.err.println("Sold: " + car.getFullInfo());
                carFactory.registerSoldCar();
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
}
