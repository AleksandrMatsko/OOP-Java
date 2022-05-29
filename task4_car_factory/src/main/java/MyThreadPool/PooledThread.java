package MyThreadPool;

import java.util.List;

public class PooledThread extends Thread {
    private final List<Task> taskQueue;

    public PooledThread(String name, List<Task> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        Task taskToExec = null;
        while (!isInterrupted()) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    }
                    catch (InterruptedException e) {
                        break;
                    }
                }
                else {
                    taskToExec = taskQueue.remove(0);
                }
            }
            try {
                taskToExec.performWork();
            }
            catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
}
