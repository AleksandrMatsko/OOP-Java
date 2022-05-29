package View.SuppliersPanels;

import CarFactory.TimeSetter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class EngineSupplierPanel extends JPanel implements SupplierViewer {
    private final String name;
    private JTextField signArea;
    private int enginesProduced;
    private JTextField enginesProducedArea;
    private int workingSpeed;
    private JSlider slider;
    private JTextField workingSpeedArea;

    public EngineSupplierPanel(ChangeListener changeListener) {
        name = "EngineSupplier";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Engine supplier");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        enginesProduced = 0;
        enginesProducedArea = new JTextField();
        enginesProducedArea.setEditable(false);
        enginesProducedArea.setText("Engines produced: " + enginesProduced);
        add(enginesProducedArea);

        workingSpeed = 30;
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
        workingSpeedArea.setText("Working speed " + workingSpeed + " engines / min");
        add(workingSpeedArea);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateProduced(int numberProduced) {
        enginesProduced = numberProduced;
        enginesProducedArea.setText("Engines produced: " + enginesProduced);
    }

    @Override
    public void updateSpeed(int workingSpeed) {
        this.workingSpeed = workingSpeed;
        workingSpeedArea.setText("Working speed " + workingSpeed + " engines / min");
    }

    @Override
    public void changePeriod(TimeSetter timeSetter, int period) {
        timeSetter.setEngineSupplierPeriod(period);
    }
}
