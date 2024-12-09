package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.exceptions.PaymentFailedException;
import com.example.resoluciones_mentoria.interfaces.PaymentProcessor;

public class NewPaymentProcessor {


    public void makePayment(double amount) throws PaymentFailedException {
        if(amount <=0){
            throw new PaymentFailedException("El monto que se debe abonar debe ser mayor a cero");
        }
        System.out.println("Estamos procesando el pago de $ " + amount + " utilizando la nueva actualización disponible");
    }
}
