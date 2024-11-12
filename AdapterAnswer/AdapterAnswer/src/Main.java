import exceptions.PaymentFailedException;
import interfaces.PaymentProcessor;
import models.OldPaymentSystem;
import utils.PaymentAdapter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PaymentProcessor oldSystem = new OldPaymentSystem();
        PaymentProcessor adapter = new PaymentAdapter();

        try {
            //pago con el sistema antiguo
            oldSystem.processPayment(500,"D456");

            //pago con el sistema adaptado
            adapter.processPayment(800,"H345");

            //Pago con error
            adapter.processPayment(1200,"G178");
        } catch (PaymentFailedException e) {
            e.printStackTrace();
        }

    }
}