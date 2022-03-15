package src.Exceptions.ValueExceptions;

public class DivisionByZeroException extends IllegalValueException {
    private final double val;

    public DivisionByZeroException(String message, double val) {
        super(message);
        this.val = val;
    }

    public double getVal() {
        return val;
    }
}
