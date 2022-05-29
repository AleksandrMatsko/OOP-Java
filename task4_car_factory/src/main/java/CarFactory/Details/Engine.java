package CarFactory.Details;

public class Engine implements Detail {
    private static int engineNumbers = 0;
    private final int id;
    private final String stringID;

    public Engine() {
        engineNumbers += 1;
        id = engineNumbers;
        stringID = "Engine <" + id + "> ";
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
