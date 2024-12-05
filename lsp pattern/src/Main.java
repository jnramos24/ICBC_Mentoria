import models.BankAccount;
import models.CurrentAccount;
import models.SavingAccount;

public class Main {
    public static void main(String[] args) {

        SavingAccount savings = new SavingAccount(1000);
        BankAccount current = new CurrentAccount(2000);

        System.out.println("Balance inicial: " + savings.getBalance());

        savings.withdraw(200);
        savings.withdraw(200);
        savings.withdraw(200);
        savings.withdraw(200);

        System.out.println("Balance inicial cuenta corriente: "+ current.getBalance());
        current.withdraw(500);
        current.withdraw(2000);

        if(savings instanceof SavingAccount) {
            savings.addInterest();
            System.out.println("Balance actual luego del interes: "+ savings.getBalance());
        }
    }
}