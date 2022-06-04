package Names;

public class CommandName implements Name {
    private final String name;

    public CommandName(String name) {
        if (!matchesSample(name)) {
            //TODO exception
        }
        this.name = name;
    }

    @Override
    public boolean matchesSample(String name) {
        return !(name == null) && name.chars().allMatch(Character::isLetter);
    }

    @Override
    public String getName() {
        return name;
    }
}
