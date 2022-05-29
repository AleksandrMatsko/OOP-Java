package CarFactory;

import CarFactory.Details.Detail;

import java.util.LinkedList;
import java.util.List;

public class Storage<T extends Detail> {
    private int id = 0;
    private final int capacity;
    private final List<T> data;


    public Storage(int capacity) {
        this.capacity = capacity;
        data = new LinkedList<>();
    }

    public synchronized T get() throws InterruptedException {
        while (data.size() < 1) {
            this.wait();
        }
        T thing = data.get(0);
        data.remove(0);
        notify();
        return thing;
    }

    public synchronized void add(T thing) throws InterruptedException {
        if (data.size() >= capacity) {
            this.wait();
        }
        id += 1;
        thing.setID(id);
        data.add(thing);
        notify();


    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized int getCurrentSize() {
        return data.size();
    }
}
