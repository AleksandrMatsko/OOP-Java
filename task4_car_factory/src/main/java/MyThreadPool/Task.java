package MyThreadPool;

public interface Task {
    void performWork() throws InterruptedException;
}
