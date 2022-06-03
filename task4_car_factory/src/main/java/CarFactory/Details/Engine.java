package CarFactory.Details;

import java.util.Objects;

public class Engine implements Detail {
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
        Engine engine = (Engine) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public void setID(int id) {
        this.id = id;
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
