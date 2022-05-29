package CarFactory.Staff;

import CarFactory.CarFactory;
import CarFactory.Car;


public class Dealer extends Thread {
    private int period;
    private CarFactory carFactory;


    public Dealer(CarFactory carFactory) {
        this.carFactory = carFactory;
        period = 1000;
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
                Car car = carFactory.getCar();
                Thread.sleep(period);
                System.err.println("Sold: " + car.getFullInfo());
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
}
