import interfaces.BalanceOperations;
import interfaces.LoanOperations;
import interfaces.NotificationOperations;

public class PremiumAccount implements BalanceOperations, LoanOperations, NotificationOperations {
    @Override
    public void checkBalance() {
        System.out.println("Premium account balance: $5000");
    }

    @Override
    public void deposit(double amount) {
        double originalBalance = 0 ;
        double finalBalance = originalBalance + amount;
        System.out.println("Deposited in Premium Account: " + finalBalance);
    }

    @Override
    public void requestLoan() {
        System.out.println("Loan request submitted for Premium Account");
    }

    @Override
    public void receiveSpecialNotification() {
        System.out.println("Special notification received for Premium account.");
    }


}
