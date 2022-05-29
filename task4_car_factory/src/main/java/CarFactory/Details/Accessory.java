package CarFactory.Details;

public class Accessory implements Detail {
    private static int accessoryNumbers = 0;
    private final int id;
    private final String stringID;

    public Accessory() {
        accessoryNumbers += 1;
        id = accessoryNumbers;
        stringID = "Accessory <" + id + "> ";
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getStringID() {
        return stringID;
    }
}
