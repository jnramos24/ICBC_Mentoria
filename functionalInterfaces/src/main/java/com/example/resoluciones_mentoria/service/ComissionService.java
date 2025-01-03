package com.example.resoluciones_mentoria.service;

import com.example.resoluciones_mentoria.interfaces.ComissionRule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComissionService {

    private static final Map<String, ComissionRule> comissionRules = new HashMap<>();

    public ComissionService(){
        comissionRules.put("Senior", salesAmount -> salesAmount * 0.80); //80% de comisión
        comissionRules.put("Semisenior", salesAmount -> salesAmount * 0.60); //60% de comisión
        comissionRules.put("Junior", salesAmount -> salesAmount * 0.40); //40% de comisión
        comissionRules.put("Trainee",salesAmount -> salesAmount * 0.20); //20% de comisión

        comissionRules.put("Default", salesAmount -> 0.0); // Comisión cero por defecto
    }

    public static double comissionCalculator(String userType, double salesAmmount){

        if (salesAmmount < 0) {
            throw new IllegalArgumentException("El monto de ventas no puede ser negativo.");
        }

        return comissionRules
                .getOrDefault(userType, comissionRules.get("Default"))
                .calculate(salesAmmount);
    }
}
