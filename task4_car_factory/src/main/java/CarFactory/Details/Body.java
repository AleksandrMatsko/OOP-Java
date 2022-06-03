package CarFactory.Details;

import java.util.Objects;

public class Body implements Detail {
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
        Body body = (Body) o;
        return id == body.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public void setID(int id) {
        this.id = id;
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
