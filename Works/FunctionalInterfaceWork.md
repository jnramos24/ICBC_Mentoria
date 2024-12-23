### **Refactorizar el Cálculo de Comisiones Usando Interfaces Funcionales**

#### **Contexto del Problema**
Una empresa necesita optimizar su sistema de cálculo de **comisiones de ventas** para sus empleados. Actualmente, el cálculo de la comisión se basa en el tipo de empleado y sigue una lógica rígida con múltiples condicionales.

---

### **Requerimiento**
Refactorizar el cálculo de comisiones utilizando **interfaces funcionales** y principios de programación funcional. El objetivo es mejorar la legibilidad, escalabilidad y mantenibilidad del código.

---

### **Tareas**

1. **Crear una Interfaz Funcional**  
   Define una interfaz funcional llamada `CommissionRule` con un método que reciba el monto de la venta y devuelva la comisión calculada.

   ```java
   @FunctionalInterface
   public interface CommissionRule {
       double calculate(double salesAmount);
   }
   ```

2. **Refactorizar la Clase Servicio**  
   Refactoriza `CommissionService` para:
   - Utilizar un `Map<String, CommissionRule>` para almacenar las reglas de cálculo por tipo de empleado.
   - Implementar un método `calculateCommission` que busque en el `Map` la regla correspondiente y la aplique.

   ```java
   import java.util.HashMap;
   import java.util.Map;

   public class CommissionService {
       private final Map<String, CommissionRule> commissionRules = new HashMap<>();

       public CommissionService() {
           // Implementación de las reglas de comisión
       }

       public double calculateCommission(String employeeType, double salesAmount) {
           // Lógica para calcular la comisión utilizando el Map
           return 0; // Placeholder
       }
   }
   ```

3. **Probar en el Método `main`**  
   - Escribe una clase `Main` donde se instancie el servicio y se realicen pruebas llamando al método `calculateCommission`.

   ```java
   public class Main {
       public static void main(String[] args) {
           CommissionService commissionService = new CommissionService();

           // Realiza varias pruebas con diferentes tipos de empleados y montos de venta
           // Ejemplo: System.out.println(commissionService.calculateCommission("SENIOR", 1000));
       }
   }
   ```

---

### **Criterios de Aceptación**
1. **Funcionalidad Completa**: El servicio debe calcular correctamente la comisión para diferentes tipos de empleados.
2. **Código Limpio y Escalable**: No deben existir condicionales en el método de cálculo.
3. **Pruebas en el `main` Exitosas**: Los resultados de las pruebas deben coincidir con las expectativas según las reglas de negocio.