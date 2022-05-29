package CarFactory.Details;

public class Body implements Detail {
    private static int bodyNumbers = 0;
    private final int id;
    private final String stringID;

    public Body() {
        bodyNumbers += 1;
        id = bodyNumbers;
        stringID = "Body <" + id + "> ";
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
