import java.util.HashMap;
import java.util.Map;

public class CommissionService {
    private final Map<String, CommissionRule> commissionRules = new HashMap<>();

    public CommissionService() {

        // Implementación de las reglas de comisión
        commissionRules.put("Junior", sueldoBasico -> sueldoBasico * 1.1);
        commissionRules.put("Trainee", sueldoBasico -> sueldoBasico * 1.2);
        commissionRules.put("Default", sueldoBasico -> sueldoBasico * 1);

    }

    public void addCustomCommision(String employeeType, CommissionRule commissionRule) {
        commissionRules.put(employeeType, commissionRule);
    }

    public double calculateCommission(String employeeType, double salesAmount) {
        return commissionRules
                .getOrDefault(employeeType, commissionRules.get("Default"))
                .calculate(salesAmount);
    }
}
