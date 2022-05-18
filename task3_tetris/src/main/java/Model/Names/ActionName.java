package Model.Names;

import Exceptions.InvalidActionNameException;

import java.util.Objects;

public class ActionName implements Name {

    private final String name;

    public ActionName(String name) throws InvalidActionNameException {
        if (!matchesSample(name)) {
            throw new InvalidActionNameException();
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matchesSample(String name) {
        return name.chars().allMatch(Character::isLetter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActionName that = (ActionName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
