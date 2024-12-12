import adapters.PaymentAdapter;
import exceptions.PaymentFailException;
import interfaces.PaymentProcessor;
import paymentMethods.OldPaymentSystem;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        PaymentProcessor oldSystem = new OldPaymentSystem();

        try {
            oldSystem.processPayment((double)100.0F, "OldType");
        } catch (PaymentFailException e) {
            throw new RuntimeException(e);
        }

        PaymentAdapter newSystem = new PaymentAdapter();

        try {
            newSystem.processPayment((double)500.0F, "NewType");
        } catch (PaymentFailException e) {
            throw new RuntimeException(e);
        }
    }
}
