## TEORÍA

El principio de **Segregación de Interfaces (ISP)**, establece que una clase no debería estar forzada a implementar interfaces que no utiliza. Esto promueve la creación de interfaces pequeñas y específicas en lugar de grandes interfaces generales, para evitar que las clases tengan métodos que no necesitan.

Veamos una explicación en profundidad con un ejemplo relacionado con funcionalidades de usuarios.

---

### **Concepto**
Supongamos que tenemos un sistema de usuarios donde los roles pueden variar. Algunos usuarios solo necesitan funcionalidades básicas como autenticación, mientras que otros requieren características avanzadas como gestión de reportes o configuración del sistema. Si creamos una interfaz muy grande con todos los métodos, algunas clases estarán obligadas a implementar métodos que no son relevantes para ellas.

---

### **Mal diseño sin ISP**
Aquí hay un ejemplo de un diseño que viola el principio ISP:

```java
// Interfaz que incluye demasiados métodos
public interface UserOperations {
    void login();
    void logout();
    void generateReports();
    void configureSystem();
}

// Usuario que no necesita todas las funcionalidades
public class BasicUser implements UserOperations {
    @Override
    public void login() {
        System.out.println("Basic user logged in.");
    }

    @Override
    public void logout() {
        System.out.println("Basic user logged out.");
    }

    @Override
    public void generateReports() {
        // No tiene sentido para un usuario básico
        throw new UnsupportedOperationException("Basic user cannot generate reports.");
    }

    @Override
    public void configureSystem() {
        // No tiene sentido para un usuario básico
        throw new UnsupportedOperationException("Basic user cannot configure the system.");
    }
}

// Usuario avanzado
public class AdminUser implements UserOperations {
    @Override
    public void login() {
        System.out.println("Admin user logged in.");
    }

    @Override
    public void logout() {
        System.out.println("Admin user logged out.");
    }

    @Override
    public void generateReports() {
        System.out.println("Admin user generated reports.");
    }

    @Override
    public void configureSystem() {
        System.out.println("Admin user configured the system.");
    }
}
```

En este caso:
- La interfaz `UserOperations` es demasiado grande.
- `BasicUser` implementa métodos como `generateReports` y `configureSystem`, que no necesita. Esto conduce a métodos no utilizados o excepciones no deseadas.

---

### **Buen diseño con ISP**
Siguiendo el principio ISP, dividimos la interfaz en interfaces más pequeñas y específicas:

```java
// Interfaces pequeñas y específicas
public interface AuthOperations {
    void login();
    void logout();
}

public interface ReportOperations {
    void generateReports();
}

public interface ConfigOperations {
    void configureSystem();
}

// Usuario básico que solo implementa la funcionalidad necesaria
public class BasicUser implements AuthOperations {
    @Override
    public void login() {
        System.out.println("Basic user logged in.");
    }

    @Override
    public void logout() {
        System.out.println("Basic user logged out.");
    }
}

// Usuario avanzado que implementa funcionalidades adicionales
public class AdminUser implements AuthOperations, ReportOperations, ConfigOperations {
    @Override
    public void login() {
        System.out.println("Admin user logged in.");
    }

    @Override
    public void logout() {
        System.out.println("Admin user logged out.");
    }

    @Override
    public void generateReports() {
        System.out.println("Admin user generated reports.");
    }

    @Override
    public void configureSystem() {
        System.out.println("Admin user configured the system.");
    }
}
```

---

### **Ventajas del diseño con ISP**
1. **Flexibilidad**: Cada clase solo implementa las interfaces que necesita.
2. **Reutilización**: Las interfaces pequeñas son más fáciles de reutilizar en otros contextos.
3. **Mantenibilidad**: Es más fácil modificar o extender funcionalidades específicas.

---

### **Uso en un programa**
Un ejemplo práctico para demostrarlo:

```java
public class Main {
    public static void main(String[] args) {
        AuthOperations basicUser = new BasicUser();
        basicUser.login();
        basicUser.logout();

        AdminUser adminUser = new AdminUser();
        adminUser.login();
        adminUser.generateReports();
        adminUser.configureSystem();
        adminUser.logout();
    }
}
```

---

### **Salida esperada**
```
Basic user logged in.
Basic user logged out.
Admin user logged in.
Admin user generated reports.
Admin user configured the system.
Admin user logged out.
```

Este diseño cumple con el principio de segregación de interfaces al asegurar que cada clase solo implemente métodos relevantes para su funcionalidad específica.

---

## EJERCICIO

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