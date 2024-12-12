package adapters;

import exceptions.PaymentFailException;
import interfaces.PaymentProcessor;
import paymentMethods.NewPaymentProcessor;

public class PaymentAdapter implements PaymentProcessor {
    NewPaymentProcessor newPaymentProcessor = new NewPaymentProcessor();

    public PaymentAdapter() {
    }

    public void processPayment(double amount, String paymentType) throws PaymentFailException {
        if (paymentType.equalsIgnoreCase("NewType")) {
            this.newPaymentProcessor.makepayment(amount, paymentType);
        } else {
            throw new PaymentFailException("Pago no soportado");
        }
    }
}