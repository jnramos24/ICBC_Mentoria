package org.example.interfaces;

@FunctionalInterface
public interface CommissionRule {
    double calculate(double salesAmount);
}