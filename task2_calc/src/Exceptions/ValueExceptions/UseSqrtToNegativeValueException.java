package src.Exceptions.ValueExceptions;

public class UseSqrtToNegativeValueException extends IllegalValueException {
    private final double val;

    public UseSqrtToNegativeValueException(String message, double val) {
        super(message);
        this.val = val;
    }

    public double getVal() {
        return val;
    }
}
