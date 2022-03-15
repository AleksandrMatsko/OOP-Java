package src.Exceptions.NameExceptons;

public class IllegalNameException extends IllegalArgumentException {
    public IllegalNameException() {
        super();
    }

    public IllegalNameException(String message) {
        super(message);
    }
}
