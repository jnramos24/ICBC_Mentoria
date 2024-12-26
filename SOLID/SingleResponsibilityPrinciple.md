### Principio de Responsabilidad Única (Single Responsibility Principle - SRP)

El **Principio de Responsabilidad Única** establece que una clase debe tener una única responsabilidad o razón para cambiar. En otras palabras, cada clase debe encargarse de una única tarea específica dentro del sistema, y no debe mezclar responsabilidades. Esto mejora la cohesión y hace que el código sea más fácil de mantener y comprender, ya que cada clase se ocupa de una parte específica del problema.

### Ejemplo: Caso de una clase sin aplicar SRP

Supongamos que tenemos una clase llamada `Employee` que almacena información de un empleado y también maneja la lógica de persistencia de datos y la generación de informes. Esto va en contra del SRP, ya que la clase `Employee` tiene múltiples responsabilidades: manejar la información del empleado, guardar datos en la base de datos y generar informes, lo cual complica su mantenimiento y dificulta la realización de pruebas.

```java
public class Employee {
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // Responsibility 1: Manage employee information
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    // Responsibility 2: Save to database
    public void saveToDatabase() {
        // Code to save the employee to the database
        System.out.println("Employee saved to database");
    }

    // Responsibility 3: Generate employee report
    public void generateReport() {
        // Code to generate an employee report
        System.out.println("Generating employee report");
    }
}
```

Esta clase `Employee` viola el SRP porque tiene tres responsabilidades:
1. Almacenar y manejar la información del empleado.
2. Guardar los datos del empleado en una base de datos.
3. Generar un reporte del empleado.

Si hay cambios en la lógica de generación de informes o en la forma de persistir los datos, tendremos que modificar la clase `Employee`, lo cual puede introducir errores y hacer que el código sea menos flexible.

### Aplicando el SRP

Para aplicar el SRP, dividiremos la funcionalidad de esta clase en varias clases especializadas, cada una con una única responsabilidad:

1. `Employee` — manejará solo la información del empleado.
2. `EmployeeRepository` — será responsable de la persistencia (guardar el empleado en la base de datos).
3. `EmployeeReportService` — generará el reporte del empleado.

#### Código refactorizado

```java
// Employee class only stores employee information
public class Employee {
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}

// Class responsible for saving employee data to the database
public class EmployeeRepository {
    public void save(Employee employee) {
        // Code to save the employee to the database
        System.out.println("Employee saved to database");
    }
}

// Class responsible for generating employee reports
public class EmployeeReportService {
    public void generate(Employee employee) {
        // Code to generate the employee's report
        System.out.println("Generating report for employee: " + employee.getName());
    }
}
```

### Uso de las clases refactorizadas

Ahora, en lugar de que `Employee` maneje todas las responsabilidades, podemos crear instancias de `Employee`, `EmployeeRepository` y `EmployeeReportService` para que cada clase cumpla con su responsabilidad única.

```java
public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("John Doe", "Developer", 50000);

        // Use EmployeeRepository to save the employee
        EmployeeRepository employeeRepo = new EmployeeRepository();
        employeeRepo.save(employee);

        // Use EmployeeReportService to generate the report
        EmployeeReportService reportService = new EmployeeReportService();
        reportService.generate(employee);
    }
}
```

### Ventajas de aplicar SRP

1. **Mantenimiento más sencillo**: Cada clase tiene una única responsabilidad, lo que facilita el cambio o la ampliación de una funcionalidad sin afectar al resto del sistema.
2. **Pruebas más específicas**: Las clases especializadas son más fáciles de probar, ya que solo se encargan de una funcionalidad específica.
3. **Mayor reutilización y flexibilidad**: Podemos reutilizar `EmployeeRepository` y `EmployeeReportService` en otras partes del sistema sin depender de la clase `Employee`.

Con este enfoque, cada clase sigue el **Principio de Responsabilidad Única** y se ocupa de una única tarea, lo que hace el sistema más modular y fácil de mantener.