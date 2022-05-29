package View;

import CarFactory.TimeSetter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DealersPanel extends JPanel {
    private final String name;
    private JTextField signArea;
    private int carsSold;
    private JTextField carsSoldArea;
    private int requestingSpeed;
    private JSlider slider;
    private JTextField requestingSpeedArea;

    public DealersPanel(ChangeListener changeListener) {
        name = "Dealers";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Dealers");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        carsSold = 0;
        carsSoldArea = new JTextField();
        carsSoldArea.setEditable(false);
        carsSoldArea.setText("Cars sold: " + carsSold);
        add(carsSoldArea);

        requestingSpeed = 60;
        slider = new JSlider(JSlider.HORIZONTAL, 10, 610, requestingSpeed);
        slider.addChangeListener(changeListener);
        slider.setName(name);
        slider.setMajorTickSpacing(60);
        slider.setMinorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        add(slider);

        requestingSpeedArea = new JTextField();
        requestingSpeedArea.setEditable(false);
        requestingSpeedArea.setText("Requesting speed " + requestingSpeed + " cars / min");
        add(requestingSpeedArea);
    }

    public String getName() {
        return name;
    }

    public void updateSold(int numberSold) {
        carsSold = numberSold;
        carsSoldArea.setText("Cars sold: " + carsSold);
    }

    public void updateSpeed(int requestingSpeed) {
        this.requestingSpeed = requestingSpeed;
        requestingSpeedArea.setText("Requesting speed " + requestingSpeed + " cars / min");
    }

    public void changePeriod(TimeSetter timeSetter, int period) {
        timeSetter.setDealerPeriod(period);
    }

}
