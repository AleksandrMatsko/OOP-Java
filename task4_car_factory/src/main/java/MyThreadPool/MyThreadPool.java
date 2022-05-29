package MyThreadPool;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {
    private final int threadsCount;
    private final List<Task> taskQueue;
    private final HashSet<Thread> availableThreads;

    public MyThreadPool(int threadsCount, String unitName) {
        this.threadsCount = threadsCount;
        this.taskQueue = new LinkedList<Task>();
        this.availableThreads = new HashSet<>();
        for (int i = 0; i < threadsCount; i++) {
            availableThreads.add(new PooledThread(unitName + " " + i, taskQueue));
        }
    }

    public void startAll() {
        for (Thread thread : availableThreads) {
            thread.start();
        }
    }

    public void addTask(Task task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void stopAll() {
        for (Thread thread : availableThreads) {
            thread.interrupt();
        }
    }


}
