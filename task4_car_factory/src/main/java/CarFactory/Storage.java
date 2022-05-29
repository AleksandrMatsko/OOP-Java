package CarFactory;

import java.util.LinkedList;

public class Storage<T> {
    private final int capacity;
    private LinkedList<T> data;


    public Storage(int capacity) {
        this.capacity = capacity;
        data = new LinkedList<>();
    }

    public T get() throws InterruptedException {
        synchronized (this) {
            while (data.size() <= 0) {
                this.wait();
            }
            T thing = data.pop();
            notify();
            return thing;
        }
    }

    public void add(T thing) throws InterruptedException {
        synchronized (this) {
            if (data.size() >= capacity) {
                this.wait();
            }
            data.add(thing);
            notify();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized int getCurrentSize() {
        return data.size();
    }
}
