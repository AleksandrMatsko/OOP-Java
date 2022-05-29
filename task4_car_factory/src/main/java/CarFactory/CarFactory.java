package CarFactory;

import CarFactory.Details.Accessory;
import CarFactory.Details.Body;
import CarFactory.Details.Engine;
import CarFactory.Staff.Dealer;
import CarFactory.Staff.Supplier;
import CarFactory.StaffActions.CarAssembly;
import MyThreadPool.MyThreadPool;
import View.Viewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarFactory implements TimeSetter, Closable {
    private Viewer viewer;
    private Storage<Car> carStorage;
    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoryStorage;

    private final int dealersNumber;
    private final int workersNumber;
    private final int accessorySuppliersNumber;
    private final boolean logging;

    private final List<Dealer> dealers;
    private final List<Supplier<Accessory>> accessorySuppliers;
    private final Supplier<Engine> engineSupplier;
    private final Supplier<Body> bodySupplier;
    private final MyThreadPool workers;

    private int workerPeriod = 400;
    private int accessorySupplierPeriod = 100;
    private int engineSupplierPeriod = 2000;
    private int dealerPeriod = 1000;
    private int bodySupplierPeriod = 1000;

    private int soldCars = 0;


    public CarFactory() throws IOException {
        Properties properties = new Properties();
        properties.load(CarFactory.class.getResourceAsStream("/configure.properties"));

        initStorages(properties);

        dealersNumber = getIntegerFromProperty(properties, "Dealers");
        workersNumber = getIntegerFromProperty(properties, "Workers");
        accessorySuppliersNumber = getIntegerFromProperty(properties, "AccessorySuppliers");
        logging = Boolean.parseBoolean(properties.getProperty("LogSale"));

        dealers = new ArrayList<>();
        for (int i = 0; i < dealersNumber; i++) {
            dealers.add(new Dealer(this, dealerPeriod));
        }
        accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < accessorySuppliersNumber; i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class, accessorySupplierPeriod));
        }

        bodySupplier = new Supplier<>(bodyStorage, Body.class, bodySupplierPeriod);
        engineSupplier = new Supplier<>(engineStorage, Engine.class, engineSupplierPeriod);

        workers = new MyThreadPool(workersNumber, "Worker");

    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public void stopProduction() {
        for (Dealer dealer : dealers) {
            dealer.interrupt();
        }
        for (Supplier<Accessory> supplier : accessorySuppliers) {
            supplier.interrupt();
        }
        bodySupplier.interrupt();
        engineSupplier.interrupt();
        workers.stopAll();
    }

    public boolean isLogging() {
        return logging;
    }

    public void startProduction() {
        for (Dealer dealer : dealers) {
            dealer.start();
        }
        for (Supplier<Accessory> supplier : accessorySuppliers) {
            supplier.start();
        }
        bodySupplier.start();
        engineSupplier.start();
        workers.startAll();
    }

    private int getIntegerFromProperty(Properties properties, String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    private void initStorages(Properties properties) {
        engineStorage = new Storage<Engine>(getIntegerFromProperty(properties, "StorageEngineCapacity"));
        bodyStorage = new Storage<Body>(getIntegerFromProperty(properties, "StorageBodyCapacity"));
        carStorage = new Storage<Car>(getIntegerFromProperty(properties, "StorageCarCapacity"));
        accessoryStorage = new Storage<Accessory>(getIntegerFromProperty(properties, "StorageAccessoryCapacity"));
    }

    public Storage<Body> getBodyStorage() {
        return bodyStorage;
    }

    public Storage<Engine> getEngineStorage() {
        return engineStorage;
    }

    public Storage<Accessory> getAccessoryStorage() {
        return accessoryStorage;
    }

    public void addCar(Car car) throws InterruptedException {
        carStorage.add(car);
    }

    public Car getCar() throws InterruptedException {
        workers.addTask(new CarAssembly(this, workerPeriod));
        return carStorage.get();
    }

    @Override
    public void setWorkerPeriod(int workerPeriod) {
        this.workerPeriod = workerPeriod;

    }

    @Override
    public void setAccessorySupplierPeriod(int accessorySupplierPeriod) {
        this.accessorySupplierPeriod = accessorySupplierPeriod;
        for (Supplier<Accessory> supplier : accessorySuppliers) {
            supplier.setPeriod(accessorySupplierPeriod);
        }
    }

    @Override
    public void setEngineSupplierPeriod(int engineSupplierPeriod) {
        this.engineSupplierPeriod = engineSupplierPeriod;
        engineSupplier.setPeriod(engineSupplierPeriod);
    }

    @Override
    public void setDealerPeriod(int dealerPeriod) {
        this.dealerPeriod = dealerPeriod;
        for (Dealer dealer : dealers) {
            dealer.setPeriod(dealerPeriod);
        }
    }

    @Override
    public void setBodySupplierPeriod(int bodySupplierPeriod) {
        this.bodySupplierPeriod = bodySupplierPeriod;
        bodySupplier.setPeriod(bodySupplierPeriod);
    }

    public synchronized void registerSoldCar() {
        soldCars += 1;
    }

    public synchronized int getSoldCars() {
        return soldCars;
    }

    @Override
    public void close() {
        stopProduction();
    }
}
