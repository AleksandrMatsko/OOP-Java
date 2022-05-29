package View;

import CarFactory.Closable;
import CarFactory.TimeSetter;
import View.StoragePanels.*;
import View.SuppliersPanels.AccessorySupplierPanel;
import View.SuppliersPanels.BodySupplierPanel;
import View.SuppliersPanels.EngineSupplierPanel;
import View.SuppliersPanels.SupplierViewer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class Viewer extends JFrame implements ChangeListener {
    private TimeSetter timeSetter;
    private Closable closable;
    private final HashMap<String, SupplierViewer> supplierViewers;
    private final HashMap<String, StorageViewer> storageViewers;
    private final WorkersPanel workersPanel;
    private final DealersPanel dealersPanel;

    public Viewer() {
        setTitle("Car factory");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel suppliersPanel = new JPanel();
        suppliersPanel.setLayout(new BoxLayout(suppliersPanel, BoxLayout.Y_AXIS));
        supplierViewers = new HashMap<>();

        BodySupplierPanel bodySupplierPanel = new BodySupplierPanel(this);
        supplierViewers.put(bodySupplierPanel.getName(), bodySupplierPanel);
        suppliersPanel.add(bodySupplierPanel);

        EngineSupplierPanel engineSupplierPanel = new EngineSupplierPanel(this);
        supplierViewers.put(engineSupplierPanel.getName(), engineSupplierPanel);
        suppliersPanel.add(engineSupplierPanel);

        AccessorySupplierPanel accessorySupplierPanel = new AccessorySupplierPanel(this);
        supplierViewers.put(accessorySupplierPanel.getName(), accessorySupplierPanel);
        suppliersPanel.add(accessorySupplierPanel);

        mainPanel.add(suppliersPanel);


        JPanel storagesPanel = new JPanel();
        storagesPanel.setLayout(new BoxLayout(storagesPanel, BoxLayout.Y_AXIS));
        storageViewers = new HashMap<>();

        BodyStoragePanel bodyStoragePanel = new BodyStoragePanel();
        storageViewers.put(bodyStoragePanel.getName(), bodyStoragePanel);
        storagesPanel.add(bodyStoragePanel);

        EngineStoragePanel engineStoragePanel = new EngineStoragePanel();
        storageViewers.put(engineStoragePanel.getName(), engineStoragePanel);
        storagesPanel.add(engineStoragePanel);

        AccessoriesStoragePanel accessoriesStoragePanel = new AccessoriesStoragePanel();
        storageViewers.put(accessoriesStoragePanel.getName(), accessoriesStoragePanel);
        storagesPanel.add(accessoriesStoragePanel);

        mainPanel.add(storagesPanel);

        workersPanel = new WorkersPanel(this);
        mainPanel.add(workersPanel);

        CarStoragePanel carStoragePanel = new CarStoragePanel();
        storageViewers.put(carStoragePanel.getName(), carStoragePanel);
        mainPanel.add(carStoragePanel);

        dealersPanel = new DealersPanel(this);
        mainPanel.add(dealersPanel);


        add(mainPanel);
        setBounds(50, 50, 1600, 600);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closable.close();
                super.windowClosing(e);
                System.exit(0);
            }
        });

    }

    public void setTimeSetter(TimeSetter setter) {
        timeSetter = setter;
    }

    public void setClosable(Closable closable) {
        this.closable = closable;
    }

    public synchronized void updateView() {

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        int newWorkingSpeed = slider.getValue();
        int period = 60 * 1000 / newWorkingSpeed;
        if (supplierViewers.containsKey(slider.getName())) {
            supplierViewers.get(slider.getName()).changePeriod(timeSetter, period);
            supplierViewers.get(slider.getName()).updateSpeed(newWorkingSpeed);
        }
        else if (slider.getName().equals(workersPanel.getName())) {
            workersPanel.updateSpeed(newWorkingSpeed);
            workersPanel.changePeriod(timeSetter, period);
        }

    }
}
