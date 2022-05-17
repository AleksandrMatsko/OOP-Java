package Model.Names;

import java.util.Objects;

public class UserName implements Name {
    private final String name;

    public UserName(String name) {
        if (!matchesSample(name)) {
            // TODO exception
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
        return !name.chars().allMatch(Character::isDigit);
    }
}
