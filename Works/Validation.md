
Vamos a crear una API REST sencilla para manejar usuarios, asegurándonos de que los datos cumplan con ciertas reglas de validación antes de almacenarlos.

---

### **Descripción del ejercicio**
- **Entidad**: Usuario (con campos: `id`, `nombre`, `email`, `edad`, `contraseña`).
- **Validaciones**:
  - `nombre`: No puede estar vacío (`@NotBlank`).
  - `email`: Debe ser un correo válido (`@Email`).
  - `edad`: Debe ser mayor o igual a 18 (`@Min(18)`).
  - `contraseña`: Debe ser "fuerte" (con una validación personalizada).
- **Simulación de base de datos**: Usaremos un `List<Usuario>` como almacenamiento en memoria.

---

#### **1. Crear la clase UsuarioDTO con validaciones**

---

#### **2. Crear la anotación personalizada para contraseñas fuertes**

---

#### **3. Crear el controlador**
---

### **Pruebas de la API con postman**

---

### **Objetivos del ejercicio**
1. **Validaciones**: Asegúrate de que los datos cumplan con las reglas antes de almacenarlos.
2. **Simulación de base de datos**: Usa un `ArrayList` para almacenar y gestionar datos en memoria.
3. **Prueba de la Api con postman**: Utiliza postman para crear usuarios validos y testear la validación.
