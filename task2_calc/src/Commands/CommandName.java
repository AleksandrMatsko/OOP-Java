package Commands;

import Exceptions.NameExceptons.IllegalCommandNameException;

import java.util.Objects;

public class CommandName {
    private final String name;

    public CommandName(String name) throws IllegalCommandNameException {
        if (!name.chars().allMatch(Character::isLetter)) {
            throw new IllegalCommandNameException();
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
        CommandName that = (CommandName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
