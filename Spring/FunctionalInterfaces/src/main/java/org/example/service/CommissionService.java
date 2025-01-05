package org.example.service;

import org.example.interfaces.CommissionRule;

import java.util.HashMap;
import java.util.Map;

public class CommissionService {
    private final Map<String, CommissionRule> commissionRules = new HashMap<>();

    public CommissionService() {
        commissionRules.put("Sales Representative", salesAmount -> salesAmount * 0.1);  // 10% commission
        commissionRules.put("Sales Executive", salesAmount -> salesAmount * 0.2); // 20% commision
        commissionRules.put("Default", salesAmount -> salesAmount * 1); // Without commision
    }

    public void addCustomCommision(String employeeType, CommissionRule commissionRule) {
        commissionRules.put(employeeType, commissionRule);
    }

    public double calculateCommission(String employeeType, double salesAmount) {
        return commissionRules
                .getOrDefault(employeeType, commissionRules.get("Default"))
                .calculate(salesAmount);
    }
}