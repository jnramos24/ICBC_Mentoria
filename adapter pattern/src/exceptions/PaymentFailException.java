package exceptions;

public class PaymentFailException extends Exception {
    public PaymentFailException(String message) {
        super(message);
    }
}

