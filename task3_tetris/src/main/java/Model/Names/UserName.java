package Model.Names;

import Exceptions.NameExceptions.InvalidUserNameException;

import java.util.Objects;

public class UserName implements Name {
    private final String name;

    public UserName(String name) throws InvalidUserNameException {
        if (!matchesSample(name)) {
            throw new InvalidUserNameException();
        }
        this.name = name;
    }

    public int compareTo(UserName another) {
        return name.compareTo(another.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserName userName = (UserName) o;
        return Objects.equals(name, userName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matchesSample(String name) {
        return name != null && !name.chars().allMatch(Character::isDigit);
    }
}
