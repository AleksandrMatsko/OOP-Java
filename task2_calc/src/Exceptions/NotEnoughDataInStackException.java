package src.Exceptions;

public class NotEnoughDataInStackException extends Exception {
    private final int required;
    private final int provided;

    public NotEnoughDataInStackException(String message, int required, int provided) {
        super(message);
        this.required = required;
        this.provided = provided;
    }

    public int getRequired() {
        return required;
    }

    public int getProvided() {
        return provided;
    }
}
