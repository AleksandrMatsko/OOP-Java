package CarFactory.StaffActions;

import CarFactory.CarFactory;
import CarFactory.Car;
import CarFactory.Details.Accessory;
import CarFactory.Details.Body;
import CarFactory.Details.Engine;
import MyThreadPool.Task;

import java.util.Random;

public class CarAssembly implements Task {
    private CarFactory carFactory;

    public CarAssembly(CarFactory carFactory) {
        this.carFactory = carFactory;
    }


    @Override
    public void performWork() throws InterruptedException {
        Engine engine = carFactory.getEngineStorage().get();
        Body body = carFactory.getBodyStorage().get();
        Car newCar = new Car(engine, body);
        Random random = new Random();
        int index = random.nextInt(2);
        for (int i = 0; i < index + 1; i++) {
            Accessory accessory = carFactory.getAccessoryStorage().get();
            newCar.addAccessory(accessory);
        }
        carFactory.addCar(newCar);
    }
}
