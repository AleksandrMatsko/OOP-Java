package Model.Names;

import java.util.Objects;

public class FigureName implements Name {
    private final String name;

    public FigureName(String name) {
        if (!matchesSample(name)) {
            //TODO exception
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matchesSample(String name) {
        return name.length() == 1 && name.chars().allMatch(Character::isLetter);
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
