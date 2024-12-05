public class Main {
    public static void main(String[] args) {

        System.out.println();
        BasisAccount basisAccount = new BasisAccount();
        basisAccount.checkBalance();
        basisAccount.deposit(3500);

        System.out.println();
        PremiumAccount premiumAccount = new PremiumAccount();
        premiumAccount.checkBalance();
        premiumAccount.deposit(2000);
        premiumAccount.requestLoan();
        premiumAccount.receiveSpecialNotification();

        System.out.println();
        CorporateAccount corporateAccount = new CorporateAccount();
        corporateAccount.checkBalance();
        corporateAccount.deposit(45000);
        corporateAccount.generateFinancialReport();
        corporateAccount.internationalTransfer(63000);


    }
}
