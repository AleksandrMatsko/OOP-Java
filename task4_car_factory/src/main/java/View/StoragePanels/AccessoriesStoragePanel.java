package View.StoragePanels;

import javax.swing.*;
import java.awt.*;

public class AccessoriesStoragePanel extends JPanel implements StorageViewer {

    private int accessoriesStored;
    private final String name;
    private JTextField accessoriesStoredArea;
    private JTextField signArea;


    public AccessoriesStoragePanel() {
        name = "AccessoryStorage";
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        signArea = new JTextField("Accessories storage");
        signArea.setEditable(false);
        signArea.setForeground(Color.black);
        signArea.setFont(new Font(null, Font.BOLD, 15));
        add(signArea);

        accessoriesStored = 0;
        accessoriesStoredArea = new JTextField();
        accessoriesStoredArea.setEditable(false);
        accessoriesStoredArea.setText("Accessories stored: " + accessoriesStored);
        add(accessoriesStoredArea);
    }




    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateStored(int numStored) {
        accessoriesStored = numStored;
        accessoriesStoredArea.setText("Accessories stored: " + accessoriesStored);
    }
}
