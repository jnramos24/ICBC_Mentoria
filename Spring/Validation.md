## TEORÍA

La **validación** en Spring Boot es un mecanismo esencial para asegurar que los datos proporcionados por los usuarios (a través de formularios, APIs REST, etc.) cumplan ciertos requisitos antes de ser procesados. Spring Boot utiliza **Bean Validation** (basado en la especificación JSR 380) para facilitar este proceso.

Veamos una guía paso a paso para implementar validaciones en tu aplicación:

---

## **1. Dependencias necesarias**

Si estás utilizando Spring Boot, la dependencia de validación viene incluida con **spring-boot-starter-web**. Si no está presente, puedes agregarla manualmente:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## **2. Anotaciones de validación**

Spring Boot utiliza anotaciones para definir reglas de validación directamente en tus clases de modelo o DTOs. Estas son algunas de las más comunes:

| Anotación                     | Descripción                                     |
|-------------------------------|-------------------------------------------------|
| `@NotNull`                    | El campo no puede ser `null`.                   |
| `@NotEmpty`                   | El campo no puede estar vacío (listas, strings).|
| `@NotBlank`                   | No puede ser null ni contener solo espacios.    |
| `@Size(min, max)`             | Define el tamaño mínimo/máximo de strings/listas. |
| `@Min(valor)`                 | El valor debe ser mayor o igual al especificado.|
| `@Max(valor)`                 | El valor debe ser menor o igual al especificado.|
| `@Email`                      | El campo debe ser un correo electrónico válido.|
| `@Pattern(regexp)`            | Debe cumplir con una expresión regular.         |
| `@Positive` / `@Negative`     | El valor debe ser positivo/negativo.           |
| `@Past` / `@Future`           | La fecha debe estar en el pasado/futuro.       |

---

## **3. Crear un DTO con validaciones**

Define un **Data Transfer Object (DTO)** con las anotaciones necesarias. Por ejemplo:

```java
import jakarta.validation.constraints.*;

public class UsuarioDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 18, message = "La edad mínima es 18 años")
    private Integer edad;

    @Email(message = "El correo debe ser válido")
    private String correo;

    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    private String contrasena;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
```

---

## **4. Usar validación en un controlador**

En el controlador, utiliza la anotación `@Valid` para indicar que los datos deben ser validados antes de procesarse:

```java
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public String crearUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        return "Usuario creado: " + usuario.getNombre();
    }
}
```

Si los datos no cumplen con las reglas de validación, Spring genera automáticamente un error **400 Bad Request** con un mensaje descriptivo.

## ** Buenas prácticas**
1. **Usar DTOs para validaciones**: No mezcles las validaciones con tus entidades del modelo.
2. **Validaciones en el lado del cliente**: También implementa validaciones en el cliente para mejorar la experiencia del usuario.
3. **Usar mensajes descriptivos**: Los mensajes de error deben ser claros y fáciles de entender.
4. **Manejo global de excepciones**: Centraliza el manejo de errores para mantener limpio el código de tus controladores.

---

## EJERCICIO

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

#### **2. Crear la anotación personalizada para contraseñas fuertes**

#### **3. Crear el controlador**

---

### **Pruebas de la API con postman**

---

### **Objetivos del ejercicio**
1. **Validaciones**: Asegúrate de que los datos cumplan con las reglas antes de almacenarlos.
2. **Simulación de base de datos**: Usa un `ArrayList` para almacenar y gestionar datos en memoria.
3. **Prueba de la Api con postman**: Utiliza postman para crear usuarios validos y testear la validación.