package CarFactory;

import CarFactory.Details.Accessory;
import CarFactory.Details.Body;
import CarFactory.Details.Detail;
import CarFactory.Details.Engine;

import java.util.HashSet;
import java.util.Set;

public class Car implements Detail {
    private final Engine engine;
    private final Body body;
    private final Set<Accessory> accessories;
    private int id;
    private String stringID;

    public Car(Engine engine, Body body) {
        this.engine = engine;
        this.body = body;
        accessories = new HashSet<>();
    }


    @Override
    public void setID(int id) {
        this.id = id;
        stringID = "Car <" + id + "> ";
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getStringID() {
        return stringID;
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

    public void addAccessory(Set<Accessory> accessorySet) {
        accessories.addAll(accessorySet);
    }

    public void removeAccessory(Accessory accessory) {
        accessories.remove(accessory);
    }
}
