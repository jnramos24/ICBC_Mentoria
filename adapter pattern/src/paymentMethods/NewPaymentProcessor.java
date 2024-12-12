package paymentMethods;

import exceptions.PaymentFailException;

public class NewPaymentProcessor {
    public NewPaymentProcessor() {
    }

    public void makepayment(double amount, String paymentType) throws PaymentFailException {
        if (paymentType.equalsIgnoreCase("NewType")) {
            System.out.println("Procesando pago con NewPayment: " + amount);
        } else {
            throw new PaymentFailException("Tipo no soportado");
        }
    }
}
