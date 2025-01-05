package org.example;

import org.example.service.CommissionService;

public class Main {
    public static void main(String[] args) {
        CommissionService commissionService = new CommissionService();

        System.out.println("Sales Representative Commision: $" + commissionService.calculateCommission("Sales Representative", 1000));
        System.out.println("Sales Executive Commision: $" + commissionService.calculateCommission("Sales Executive", 1000));

        commissionService.addCustomCommision("Sales Supervisor", salesAmount -> salesAmount * 0.3); // 30% commision
        System.out.println("Sales Supervisor Commision: $" + commissionService.calculateCommission("Sales Supervisor", 1000));
    }
}