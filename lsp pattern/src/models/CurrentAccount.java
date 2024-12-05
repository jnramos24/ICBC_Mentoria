package models;

public class CurrentAccount extends BankAccount{
    public CurrentAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if(amount > balance) {
            System.out.println("Los fondos son insuficientes");
            return;
        }

        balance -= amount;
        System.out.println("Retiraste el dinero de tu cuenta: " + amount);


    }
}
