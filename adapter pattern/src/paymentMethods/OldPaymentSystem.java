package paymentMethods;

import exceptions.PaymentFailException;
import interfaces.PaymentProcessor;

public class OldPaymentSystem implements PaymentProcessor {
    public OldPaymentSystem() {
    }

    public void processPayment(double amount, String paymentType) throws PaymentFailException {
        if (paymentType.equalsIgnoreCase("OldType")) {
            System.out.println("Procesando pago con OldPayment: " + amount);
        } else {
            throw new PaymentFailException("Tipo no soportado");
        }
    }
}
