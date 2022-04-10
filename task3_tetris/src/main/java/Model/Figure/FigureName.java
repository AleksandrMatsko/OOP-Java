package Model.Figure;

import java.util.Objects;

public class FigureName {
    private final String name;

    public FigureName(String name) {
        if (name.length() != 1 || name.chars().allMatch(Character::isDigit)) {
            //exception
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FigureName that = (FigureName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
