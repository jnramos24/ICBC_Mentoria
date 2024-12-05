package models;

public class SavingAccount extends BankAccount {

    private int withdrawalCount = 0;
    private static final int MAX_WITHDRAWLS = 3;
    private static final double INTEREST_RATE = 0.02;

    public SavingAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (withdrawalCount >= MAX_WITHDRAWLS) {
            System.out.println("Superaste el limite de retiro por mes");
            return;
        }

        if(amount > balance) {
            System.out.println("Los fondos son insuficientes");
            return;
        }

        balance -= amount;
        withdrawalCount++;
        System.out.println("Retiraste el dinero de tu cuenta: " + amount);

    }

    public void addInterest() {
        balance+= balance * INTEREST_RATE;
    }
}
