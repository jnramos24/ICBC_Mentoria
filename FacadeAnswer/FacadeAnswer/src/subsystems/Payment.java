package subsystems;

public class Payment {

    public void processPayment(String customerId, double amount){

        System.out.println("Procensado el pago para el cliente ID: " + customerId + "por el monto de $: " + amount);
    }
}
