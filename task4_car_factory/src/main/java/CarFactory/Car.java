package CarFactory;

import CarFactory.Details.Accessory;
import CarFactory.Details.Body;
import CarFactory.Details.Detail;
import CarFactory.Details.Engine;

import java.util.HashSet;

public class Car implements Detail {
    private static int carNumbers = 0;
    private final Engine engine;
    private final Body body;
    private final HashSet<Accessory> accessories;
    private final int id;
    private final String stringID;

    public Car(Engine engine, Body body) {
        carNumbers += 1;
        this.engine = engine;
        this.body = body;
        accessories = new HashSet<>();
        id = carNumbers;
        stringID = "Car <" + id + "> ";
    }


    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getStringID() {
        return null;
    }

    public String getFullInfo() {
        StringBuilder stringBuilder = new StringBuilder(stringID);
        stringBuilder.append(engine.getStringID());
        stringBuilder.append(body.getStringID());
        for (Accessory accessory : accessories) {
            stringBuilder.append(accessory.getStringID());
        }
        return stringBuilder.toString();
    }
    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
    }

    public void removeAccessory(Accessory accessory) {
        accessories.remove(accessory);
    }
}
