package View;

import CarFactory.TimeSetter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class WorkersPanel extends JPanel {
    private final String name;
    private JTextField signArea;
    private int carsProduced;
    private JTextField carsProducedArea;
    private int requestedCars;
    private JTextField requestedCarsArea;
    private int workingSpeed;
    private JSlider slider;
    private JTextField workingSpeedArea;
    private int activeWorkers;
    private JTextField activeWorkersArea;
    private int waitWorkers;
    private JTextField waitWorkersArea;


    public WorkersPanel(ChangeListener changeListener) {
        name = "Workers";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Workers");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        carsProduced = 0;
        carsProducedArea = new JTextField();
        carsProducedArea.setEditable(false);
        carsProducedArea.setText("Cars produced: " + carsProduced);
        add(carsProducedArea);

        requestedCars = 0;
        requestedCarsArea = new JTextField();
        requestedCarsArea.setEditable(false);
        requestedCarsArea.setText("Requested cars: " + requestedCars);
        add(requestedCarsArea);

        activeWorkers = 0;
        activeWorkersArea = new JTextField();
        activeWorkersArea.setEditable(false);
        activeWorkersArea.setText("Workers active: " + activeWorkers);
        add(activeWorkersArea);

        waitWorkers = 0;
        waitWorkersArea = new JTextField();
        waitWorkersArea.setEditable(false);
        waitWorkersArea.setText("Workers wait: " + waitWorkers);
        add(waitWorkersArea);

        workingSpeed = 150;
        slider = new JSlider(JSlider.HORIZONTAL, 10, 610, workingSpeed);
        slider.addChangeListener(changeListener);
        slider.setName(name);
        slider.setMajorTickSpacing(60);
        slider.setMinorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);

        workingSpeedArea = new JTextField();
        workingSpeedArea.setEditable(false);
        workingSpeedArea.setText("Working speed " + workingSpeed + " cars / min");
        add(workingSpeedArea);
    }

    public String getName() {
        return name;
    }

    public synchronized void updateProduced(int numberProduced) {
        carsProduced = numberProduced;
        carsProducedArea.setText("Cars produced: " + carsProduced);
    }

    public synchronized void updateSpeed(int workingSpeed) {
        this.workingSpeed = workingSpeed;
        workingSpeedArea.setText("Working speed " + workingSpeed + " cars / min");
    }

    public synchronized void updateRequested(int numberRequested) {
        requestedCars = numberRequested;
        requestedCarsArea.setText("Requested cars: " + requestedCars);
    }

    public void updateActive(int numActive) {
        activeWorkers = numActive;
        activeWorkersArea.setText("Workers active: " + activeWorkers);
    }

    public void updateWait(int numWait) {
        waitWorkers = numWait;
        waitWorkersArea.setText("Workers wait: " + waitWorkers);
    }

    public void changePeriod(TimeSetter timeSetter, int period) {
        timeSetter.setWorkerPeriod(period);
    }
}
