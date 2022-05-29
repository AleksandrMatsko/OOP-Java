package CarFactory.StaffActions;

import CarFactory.CarFactory;
import CarFactory.Car;
import CarFactory.Details.Accessory;
import CarFactory.Details.Body;
import CarFactory.Details.Engine;
import MyThreadPool.Task;

import java.util.*;

public class CarAssembly implements Task {
    private CarFactory carFactory;
    private int period;

    public CarAssembly(CarFactory carFactory, int period) {
        this.carFactory = carFactory;
        this.period = period;
    }

    public synchronized int getPeriod() {
        return period;
    }

    public synchronized void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public void performWork() throws InterruptedException {
        Engine engine = carFactory.getEngineStorage().get();
        Body body = carFactory.getBodyStorage().get();

        Thread.sleep(period);

        Set<Accessory> accessories = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(3) + 1; i++) {
            accessories.add(carFactory.getAccessoryStorage().get());
        }

        Car newCar = new Car(engine, body);
        newCar.addAccessory(accessories);

        carFactory.addCar(newCar);
    }
}
