package CarFactory.Details;

import java.util.Objects;

public class Accessory implements Detail {
    private int id;
    private String stringID;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accessory accessory = (Accessory) o;
        return id == accessory.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public void setID(int id) {
        this.id = id;
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
