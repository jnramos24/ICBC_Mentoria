import interfaces.BalanceOperations;
import interfaces.ReportOperations;
import interfaces.TransferOperations;

public class CorporateAccount implements BalanceOperations, ReportOperations, TransferOperations {

    @Override
    public void checkBalance() {
        System.out.println("Corporate account balance: $20000");
    }

    @Override
    public void deposit(double amount) {
        double originalBalance = 0 ;
        double finalBalance = originalBalance + amount;
        System.out.println("Deposited in Corporate Account: " + finalBalance);
    }

    @Override
    public void generateFinancialReport() {
        System.out.println("Generated financial report for Corporate account.");

    }

    @Override
    public void internationalTransfer(double amount) {
        double originalBalance = 0 ;
        double finalBalance = originalBalance + amount;
        System.out.println("International transfer of: " + amount + " completed for Corporate account");
    }


}
