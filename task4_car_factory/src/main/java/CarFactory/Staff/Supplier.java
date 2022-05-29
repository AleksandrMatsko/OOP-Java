package CarFactory.Staff;

import CarFactory.Details.Detail;
import CarFactory.Storage;


public class Supplier<T extends Detail> extends Thread {
    private Storage<T> storage;
    private Class<T> detailCreator;
    private int period;

    public Supplier(Storage<T> storage, Class<T> detailCreator, int period) {
        this.storage = storage;
        this.detailCreator = detailCreator;
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
                Detail detail = detailCreator.getDeclaredConstructor().newInstance();
                storage.add((T) detail);
                System.err.println("Supplied: " + detail.getStringID());
                Thread.sleep(period);
            }
            catch (InterruptedException e) {
                break;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
