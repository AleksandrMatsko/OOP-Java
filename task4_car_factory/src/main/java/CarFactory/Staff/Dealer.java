package CarFactory.Staff;

import CarFactory.CarFactory;
import CarFactory.Details.Car;
import View.Viewer;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Dealer extends Thread {
    private static final Logger logger = Logger.getLogger(Dealer.class.getName());
    private int period;
    private CarFactory carFactory;
    private Viewer viewer;


    public Dealer(CarFactory carFactory, int period, Viewer viewer) {
        this.carFactory = carFactory;
        this.period = period;
        this.viewer = viewer;
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
                    logger.log(Level.INFO, "Sold: " + car.getFullInfo());
                }
                carFactory.registerSoldCar();

                int[] args = new int[1];
                args[0] = carFactory.getSoldCars();
                viewer.updateView("Dealer", args);
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
}
