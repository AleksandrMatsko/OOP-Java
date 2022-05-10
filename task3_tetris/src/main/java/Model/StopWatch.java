package Model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CancellationException;

public class StopWatch {
    private Timer timer;
    private boolean stop;

    public StopWatch() {
        timer = new Timer();
        stop = false;
    }

    public void start(int milliseconds) {
        stop = false;
        timer = new Timer();
        System.err.println("Timer started");
        timer.schedule(new StopTask(), milliseconds);
    }

    public synchronized boolean isStop() {
        return stop;
    }

    class StopTask extends TimerTask {

        @Override
        public void run() {
            timer.cancel();
            timer.purge();
            System.err.println("Timer stopped");
            stop = true;
        }
    }

}
