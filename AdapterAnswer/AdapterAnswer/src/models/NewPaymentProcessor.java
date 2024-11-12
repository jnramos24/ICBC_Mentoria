package models;

import exceptions.PaymentFailedException;
import interfaces.PaymentProcessor;

public class NewPaymentProcessor {

    public void makePayment(double amount, String paymentId ) throws PaymentFailedException {
         if(amount>1000){
             throw new PaymentFailedException("Pago fallido con id:"+ paymentId+"\n el monto supera el limite");
         } else {
             System.out.println("Pago con id: " +paymentId+ " procesado por el monto de: $" + amount);
         }
    }

}
