package View.StoragePanels;


import javax.swing.*;
import java.awt.*;

public class EngineStoragePanel extends JPanel implements StorageViewer {
    private final String name;
    private JTextField enginesStoredArea;
    private JTextField signArea;
    private int enginesStored;


    public EngineStoragePanel() {
        name = "EngineStorage";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Engines storage");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        enginesStored = 0;
        enginesStoredArea = new JTextField();
        enginesStoredArea.setEditable(false);
        enginesStoredArea.setText("Engines stored: " + enginesStored);
        add(enginesStoredArea);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateStored(int numStored) {
        enginesStored = numStored;
        enginesStoredArea.setText("Engines stored: " + enginesStored);
    }
}
