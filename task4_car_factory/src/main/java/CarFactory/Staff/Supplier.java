package CarFactory.Staff;

import CarFactory.Details.Detail;
import CarFactory.Storage;
import View.Viewer;


public class Supplier<T extends Detail> extends Thread {
    private Viewer viewer;
    private Storage<T> storage;
    private Class<T> detailCreator;
    private int period;
    private final String name;

    public Supplier(Storage<T> storage, Class<T> detailCreator, int period, Viewer viewer, String name) {
        this.storage = storage;
        this.detailCreator = detailCreator;
        this.period = period;
        this.viewer = viewer;
        this.name = name;
    }

    public synchronized void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                T thing = detailCreator.getDeclaredConstructor().newInstance();
                Thread.sleep(period);
                storage.add(thing);

                int[] args = new int[1];
                args[0] = storage.getProduced();
                viewer.updateView(name, args);

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
