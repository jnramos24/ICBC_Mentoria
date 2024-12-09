package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.PaymentProcessor;

public class OldPaymentSistem implements PaymentProcessor {

    @Override
    public void processPayment (double amount){
       System.out.println("Estamos procesando su pago de $ " + amount + " utilizando el sistema sin actualizar");
    }
}
