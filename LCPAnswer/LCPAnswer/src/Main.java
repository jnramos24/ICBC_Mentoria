import models.BankAccount;
import models.CurrentAccount;
import models.SavingsAccount;

public class Main {
    public static void main(String[] args) {

        SavingsAccount savings = new SavingsAccount(1000);
        BankAccount current = new CurrentAccount(2000);

        System.out.println("Balance inicial cuenta ahorros: " + savings.getBalance());

        savings.withdraw(200);
        savings.withdraw(200);
        savings.withdraw(200);
        savings.withdraw(200);

        System.out.println("Balance inicial cuenta corriente: "+ current.getBalance());
        current.withdraw(500);
        current.withdraw(2000);

        if( savings instanceof SavingsAccount){
            savings.addInterest();
            System.out.println("Balance actual luego del interes: " + savings.getBalance());
        }
    }
}