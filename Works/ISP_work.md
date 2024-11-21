### **Ejercicio: Implementar el Principio de Segregación de Interfaces (ISP)**

Eres el encargado de diseñar un sistema para gestionar los diferentes tipos de cuentas en una aplicación bancaria. Existen varios tipos de cuentas con diferentes funcionalidades:

1. **Cuenta básica (BasicAccount)**:
   - Puede consultar su saldo.
   - Puede realizar depósitos.

2. **Cuenta premium (PremiumAccount)**:
   - Puede consultar su saldo.
   - Puede realizar depósitos.
   - Puede solicitar préstamos.
   - Puede recibir notificaciones especiales.

3. **Cuenta corporativa (CorporateAccount)**:
   - Puede consultar su saldo.
   - Puede realizar depósitos.
   - Puede generar reportes financieros.
   - Puede realizar transferencias internacionales.

El sistema actual tiene una única interfaz grande que incluye todos los métodos requeridos por los distintos tipos de cuentas, lo cual está causando problemas de mantenimiento porque no todas las cuentas necesitan todas las funcionalidades.

Tu tarea es refactorizar el diseño del sistema para que cumpla con el principio de **Segregación de Interfaces (ISP)**.

---

#### **Requisitos del ejercicio**
1. Divide la interfaz actual en interfaces más pequeñas y específicas.
2. Implementa las interfaces necesarias para cada tipo de cuenta.
3. Escribe una clase principal (`Main`) para probar que cada tipo de cuenta utiliza solo las funcionalidades necesarias.

---

#### **Interfaz inicial**
El sistema actualmente tiene la siguiente interfaz (no cumple con ISP):

```java
public interface Account {
    void checkBalance();
    void deposit(double amount);
    void requestLoan();
    void receiveSpecialNotification();
    void generateFinancialReport();
    void internationalTransfer(double amount);
}
```

---

#### **Tareas**
1. **Divide la interfaz**: Crea múltiples interfaces que representen funcionalidades específicas como `BalanceOperations`, `LoanOperations`, `NotificationOperations`, `ReportOperations`, y `TransferOperations`.
   
2. **Implementa las clases**:
   - `BasicAccount`: Solo implementa las interfaces relacionadas con consultar saldo y realizar depósitos.
   - `PremiumAccount`: Implementa las interfaces relacionadas con saldo, depósitos, préstamos y notificaciones.
   - `CorporateAccount`: Implementa las interfaces relacionadas con saldo, depósitos, reportes y transferencias internacionales.

3. **Escribe pruebas**:
   - Instancia objetos de cada tipo de cuenta.
   - Prueba que solo las funcionalidades relevantes estén disponibles para cada clase.

---

#### **Ejemplo de salida esperada**
```plaintext
Basic account balance: $1000
Deposited $200 into Basic account.

Premium account balance: $5000
Deposited $500 into Premium account.
Loan request submitted for Premium account.
Special notification received for Premium account.

Corporate account balance: $20000
Deposited $5000 into Corporate account.
Generated financial report for Corporate account.
International transfer of $10000 completed for Corporate account.
```

---

#### **Restricciones**
- No está permitido que una clase implemente métodos que no utiliza.
- Los nombres de las interfaces deben ser descriptivos y relacionadas con su funcionalidad.

---