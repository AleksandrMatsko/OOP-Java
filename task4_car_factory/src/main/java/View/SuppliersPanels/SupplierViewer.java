package View.SuppliersPanels;

import CarFactory.TimeSetter;

public interface SupplierViewer {
    String getName();
    void updateProduced(int numberProduced);
    void updateSpeed(int workingSpeed);
    void changePeriod(TimeSetter timeSetter, int period);
}
