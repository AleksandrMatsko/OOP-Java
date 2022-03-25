package Exceptions.FileExceptions;

import java.io.IOException;

public class CannotOpenFileException extends IOException {
    public CannotOpenFileException() {
        super();
    }

    public CannotOpenFileException(String message) {
        super(message);
    }
}
