### Ejercicio práctico para aplicar el Principio de Sustitución de Liskov (LSP) en una App Bancaria

Supongamos que estás desarrollando una aplicación bancaria que maneja diferentes tipos de cuentas: cuentas de ahorro (`SavingsAccount`) y cuentas corrientes (`CurrentAccount`). Ambas tienen operaciones básicas como consultar el saldo y retirar dinero. Sin embargo, solo las cuentas de ahorro generan intereses y tienen ciertas restricciones para el retiro.

El objetivo es diseñar un sistema que respete el LSP para que las clases hijas puedan sustituir a la clase base sin romper la lógica del sistema.

---

### Requerimientos

1. Crear una clase base `BankAccount` que defina operaciones comunes como `getBalance()` y `withdraw(double amount)`.
2. Crear subclases `SavingsAccount` y `CurrentAccount`.
   - `SavingsAccount` genera intereses y tiene un límite de retiro mensual.
   - `CurrentAccount` no genera intereses y no tiene restricciones de retiro.
3. Implementar una clase de prueba donde se manipulen las cuentas usando la clase base `BankAccount`, garantizando que las operaciones funcionen sin errores o comportamientos inesperados.

---

### Ejercicio

1. **Diseña la jerarquía de clases**:
   - Clase base `BankAccount`.
   - Subclases `SavingsAccount` y `CurrentAccount`.

2. **Implementa las siguientes funcionalidades**:
   - Todas las cuentas tienen un saldo inicial.
   - La clase base define un método para consultar el saldo y retirar dinero.
   - La clase `SavingsAccount` debe agregar:
     - Cálculo de intereses.
     - Restricción de retiro: no más de 3 retiros por mes.
   - La clase `CurrentAccount` debe permitir retiros sin restricciones.

3. **Prueba el sistema**:
   - Crea una lista de cuentas (`List<BankAccount>`).
   - Realiza operaciones comunes como retiros y consultas de saldo.
   - Verifica que todas las cuentas funcionan correctamente al sustituir objetos de subclases por la clase base.

---

### Resultado esperado:

1. **Cuentas de ahorro** respetan las reglas de retiro y cálculo de intereses.
2. **Cuentas corrientes** permiten retiros sin restricciones.
3. La aplicación puede manejar ambas cuentas a través de la clase base `BankAccount` sin errores.

### Retos adicionales:
- Agregar una funcionalidad de transferencia entre cuentas.
- Implementar un sistema de seguimiento de transacciones (como un historial/logs).