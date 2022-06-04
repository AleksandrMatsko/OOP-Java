package Names;

import Exceptions.InvalidUserNameException;

import java.io.Serializable;

public class UserName implements Name, Serializable {
    private final String name;


    public UserName(String name) throws InvalidUserNameException {
        if (!matchesSample(name)) {
            throw new InvalidUserNameException();
        }
        this.name = name;
    }
    @Override
    public boolean matchesSample(String name) {
        return !(name == null) && name.matches(".*\\w.*");
    }

    @Override
    public String getName() {
        return name;
    }
}
