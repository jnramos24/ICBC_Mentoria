package models;

public class SavingsAccount extends BankAccount {

    private int withdrawalCount = 0;
    private static final int MAX_WITHDRAWALS = 3;
    private static final double INTEREST_RATE = 0.02;

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (withdrawalCount >= MAX_WITHDRAWALS) {
            System.out.println("Superaste el limite de retiros del mes");
            return;
        }
        if (amount > balance) {
            System.out.println("Los fondos son insuficientes");
            return;
        }

        balance -= amount;
        withdrawalCount++;
        System.out.println("Retiraste el dinero de tu cuenta");
    }

    public void addInterest() {
        balance += balance * INTEREST_RATE;
    }

}
