import interfaces.BalanceOperations;

public class BasisAccount implements BalanceOperations {

    @Override
    public void checkBalance(){
        System.out.println("Basic account balance: $1000");
    }

    @Override
    public void deposit(double amount) {
        double originalBalance = 0 ;
        double finalBalance = originalBalance + amount;
        System.out.println("Deposited in Basic Account: " + finalBalance);
    }

}
