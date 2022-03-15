package src.Exceptions;

public class WrongAmountOfArgumentsException extends Exception {
    private final int expected;
    private final int has;

    public WrongAmountOfArgumentsException(String message, int expected, int has) {
        super(message);
        this.expected = expected;
        this.has = has;
    }

    public int getExpected() {
        return expected;
    }

    public int getHas() {
        return has;
    }
}
