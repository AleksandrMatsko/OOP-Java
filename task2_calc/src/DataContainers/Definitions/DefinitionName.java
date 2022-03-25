package DataContainers.Definitions;

import Exceptions.NameExceptons.IllegalDefinitionNameException;

import java.util.Objects;

public class DefinitionName {
    private final String name;

    public DefinitionName(String name) throws IllegalDefinitionNameException {
        if (name.contains(" ") || name.chars().allMatch(Character::isDigit)) {
            throw new IllegalDefinitionNameException();
        }
        this.name = name;
    }

    public String toString() {
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
        DefinitionName that = (DefinitionName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
