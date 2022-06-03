package View.SuppliersPanels;

import CarFactory.TimeSetter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class AccessorySupplierPanel extends JPanel implements SupplierViewer {

    private JTextField signArea;
    private JTextField accessoriesProducedArea;
    private int accessoriesProduced;
    private JSlider slider;
    private int workingSpeed;
    private JTextField workingSpeedArea;
    private final String name;

    public AccessorySupplierPanel(ChangeListener changeListener) {
        name = "AccessorySuppliers";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Accessory suppliers");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        accessoriesProduced = 0;
        accessoriesProducedArea = new JTextField();
        accessoriesProducedArea.setEditable(false);
        accessoriesProducedArea.setText("Accessories produced: " + accessoriesProduced);
        add(accessoriesProducedArea);

        workingSpeed = 300;
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
        workingSpeedArea.setText("Working speed " + workingSpeed + " accessories / min");
        add(workingSpeedArea);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateProduced(int numberProduced) {
        accessoriesProduced = numberProduced;
        accessoriesProducedArea.setText("Accessories produced: " + accessoriesProduced);
    }

    @Override
    public void updateSpeed(int workingSpeed) {
        this.workingSpeed = workingSpeed;
        workingSpeedArea.setText("Working speed " + workingSpeed + " Accessories / min");
    }

    @Override
    public void changePeriod(TimeSetter timeSetter, int period) {
        timeSetter.setAccessorySupplierPeriod(period);
    }
}
