public class Main {
    public static void main(String[] args) {

        CommissionService commissionService = new CommissionService();

        System.out.println("Senior Employee: " + commissionService.calculateCommission("SENIOR", 1000));

        commissionService.addCustomCommision("Senior", sueldoBasico -> sueldoBasico * 1.3);

        System.out.println("Senior Employee: " + commissionService.calculateCommission("Senior", 1000));

    }
}