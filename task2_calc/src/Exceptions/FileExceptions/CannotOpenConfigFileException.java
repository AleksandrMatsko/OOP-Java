package Exceptions.FileExceptions;

public class CannotOpenConfigFileException extends CannotOpenFileException {
    public CannotOpenConfigFileException() {
        super();
    }

    public CannotOpenConfigFileException(String message) {
        super(message);
    }
}
