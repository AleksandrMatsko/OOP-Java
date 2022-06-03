package View.StoragePanels;

import javax.swing.*;
import java.awt.*;

public class CarStoragePanel extends JPanel implements StorageViewer {

    private int carsStored;
    private final String name;
    private JTextField carsStoredArea;
    private JTextField signArea;


    public CarStoragePanel() {
        name = "CarStorage";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Cars storage");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        carsStored = 0;
        carsStoredArea = new JTextField();
        carsStoredArea.setEditable(false);
        carsStoredArea.setText("Cars stored: " + carsStored);
        add(carsStoredArea);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateStored(int numStored) {
        carsStored = numStored;
        carsStoredArea.setText("Cars stored: " + carsStored);
    }
}
