package CarFactory;

import CarFactory.Details.Detail;
import View.Viewer;

import java.util.LinkedList;

public class Storage<T extends Detail> {
    private Viewer viewer;
    private int id = 0;
    private final int capacity;
    private final LinkedList<T> data;
    private final String name;


    public Storage(int capacity, Viewer viewer, String name) {
        this.capacity = capacity;
        data = new LinkedList<>();
        this.viewer = viewer;
        this.name = name;
    }

    public T get() throws InterruptedException {
        synchronized (this) {
            while (data.size() < 1) {
                this.wait();
            }
            T thing = data.poll();

            int[] args = new int[1];
            args[0] = getCurrentSize();
            viewer.updateView(name, args);

            this.notify();
            return thing;
        }
    }

    public void add(T thing) throws InterruptedException {
        synchronized (this) {
            while (getCurrentSize() >= capacity) {
                this.wait();
            }
            id += 1;
            thing.setID(id);
            data.add(thing);

            int[] args = new int[1];
            args[0] = getCurrentSize();
            viewer.updateView(name, args);

            this.notify();
        }

    }

    public synchronized int getProduced() {
        return id;
    }

    public synchronized int getCurrentSize() {
        return data.size();
    }
}
