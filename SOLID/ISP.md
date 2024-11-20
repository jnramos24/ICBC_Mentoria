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