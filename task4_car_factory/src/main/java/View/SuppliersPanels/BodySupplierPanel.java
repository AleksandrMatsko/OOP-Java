package View.SuppliersPanels;

import CarFactory.TimeSetter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BodySupplierPanel extends JPanel implements SupplierViewer {
    private JTextField signArea;
    private JTextField bodiesProducedArea;
    private int bodiesProduced;
    private JSlider slider;
    private int workingSpeed;
    private JTextField workingSpeedArea;
    private final String name;

    public BodySupplierPanel(ChangeListener changeListener) {
        name = "BodySupplier";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        signArea = new JTextField("Body supplier");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        bodiesProduced = 0;
        bodiesProducedArea = new JTextField();
        bodiesProducedArea.setEditable(false);
        bodiesProducedArea.setText("Bodies produced: " + bodiesProduced);
        add(bodiesProducedArea);

        workingSpeed = 60;
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
        workingSpeedArea.setText("Working speed " + workingSpeed + " bodies / min");
        add(workingSpeedArea);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized void updateProduced(int numberProduced) {
        bodiesProduced = numberProduced;
        bodiesProducedArea.setText("Bodies produced " + bodiesProduced);
    }

    @Override
    public synchronized void updateSpeed(int workingSpeed) {
        this.workingSpeed = workingSpeed;
        workingSpeedArea.setText("Working speed " + this.workingSpeed + " bodies / min");
    }

    @Override
    public void changePeriod(TimeSetter timeSetter, int period) {
        timeSetter.setBodySupplierPeriod(period);
    }
}
