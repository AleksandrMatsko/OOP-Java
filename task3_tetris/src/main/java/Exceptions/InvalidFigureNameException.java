package Exceptions;

public class InvalidFigureNameException extends InvalidNameException {

    public InvalidFigureNameException() {
        super();
    }

    public InvalidFigureNameException(String message) {
        super(message);
    }
}
