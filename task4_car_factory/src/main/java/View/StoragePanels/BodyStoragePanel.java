package View.StoragePanels;

import javax.swing.*;
import java.awt.*;

public class BodyStoragePanel extends JPanel implements StorageViewer {
    private int bodiesStored;
    private final String name;
    private JTextField bodiesStoredArea;
    private JTextField signArea;


    public BodyStoragePanel() {
        name = "BodyStorage";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Bodies storage");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        bodiesStored = 0;
        bodiesStoredArea = new JTextField();
        bodiesStoredArea.setEditable(false);
        bodiesStoredArea.setText("Bodies stored: " + bodiesStored);
        add(bodiesStoredArea);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateStored(int numStored) {
        bodiesStored = numStored;
        bodiesStoredArea.setText("Bodies stored: " + bodiesStored);
    }
}
