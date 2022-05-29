package CarFactory;

import CarFactory.Details.Accessory;
import CarFactory.Details.Body;
import CarFactory.Details.Engine;
import CarFactory.Staff.Dealer;
import CarFactory.Staff.Supplier;
import CarFactory.StaffActions.CarAssembly;
import MyThreadPool.MyThreadPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarFactory {
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
            dealers.add(new Dealer(this));
        }
        accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < accessorySuppliersNumber; i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class, 100));
        }

        bodySupplier = new Supplier<>(bodyStorage, Body.class, 1000);
        engineSupplier = new Supplier<>(engineStorage, Engine.class, 2000);

        workers = new MyThreadPool(workersNumber, "Worker");

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
        workers.addTask(new CarAssembly(this));
        return carStorage.get();
    }



}
