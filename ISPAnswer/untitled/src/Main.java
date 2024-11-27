import entities.BasicAccount;
import entities.CorporateAccount;
import entities.PremiumAccount;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BasicAccount basicAccount = new BasicAccount(1000);
        basicAccount.checkBalance();
        basicAccount.deposit(200);

        PremiumAccount premiumAccount = new PremiumAccount(5000);
        premiumAccount.deposit(1000);
        premiumAccount.checkBalance();
        premiumAccount.requestLoan();
        premiumAccount.receiveSpecialNotification();

        CorporateAccount corporateAccount = new CorporateAccount(20000);
        corporateAccount.deposit(10000);
        corporateAccount.checkBalance();
        corporateAccount.generateFinancialReport();
        corporateAccount.internationalTransfer(10000);
        }
}