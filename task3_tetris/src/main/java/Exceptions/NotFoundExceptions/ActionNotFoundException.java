package Exceptions.NotFoundExceptions;

public class ActionNotFoundException extends NotFoundException {
    public ActionNotFoundException() {
        super();
    }

    public ActionNotFoundException(String message) {
        super(message);
    }
}
