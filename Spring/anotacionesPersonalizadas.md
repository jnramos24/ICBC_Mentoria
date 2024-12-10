## **Guía: Crear una Anotación Personalizada para Contraseñas Fuertes**

---

### **1. ¿Qué es una anotación personalizada?**
Una **anotación personalizada** en Java permite encapsular lógica repetitiva en una sola anotación. En este caso, crearemos una anotación que valide si una contraseña cumple con ciertas reglas de seguridad.

---

### **2. Requisitos**
Para implementar esta anotación, necesitamos:
1. **Definir la anotación personalizada**: Usando `@interface`.
2. **Crear un validador**: Implementando la lógica de validación con `ConstraintValidator`.
3. **Integrar la anotación en un modelo o DTO**.
4. **Probar la anotación**.

---

### **3. Implementación paso a paso**

#### **3.1. Crear la anotación personalizada**

```java
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContraseñaFuerteValidator.class) // Asocia la anotación con el validador
@Target({ ElementType.FIELD }) // Define que se puede usar en campos (atributos)
@Retention(RetentionPolicy.RUNTIME) // La anotación estará disponible en tiempo de ejecución
public @interface ContraseñaFuerte {

    // Mensaje de error predeterminado
    String message() default "La contraseña no cumple con los requisitos de seguridad";

    // Grupos (opcional, para validaciones específicas)
    Class<?>[] groups() default {};

    // Payload (opcional, para metadatos adicionales)
    Class<? extends Payload>[] payload() default {};
}
```

---

#### **3.2. Crear el validador asociado**

El validador es una clase que implementa la lógica de validación. Debe extender `ConstraintValidator<A, T>`, donde:
- **`A`**: Es la anotación personalizada (`ContraseñaFuerte`).
- **`T`**: Es el tipo de dato al que se aplicará la validación (en este caso, `String`).

```java
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContraseñaFuerteValidator implements ConstraintValidator<ContraseñaFuerte, String> {

    // Método que inicializa la validación (opcional)
    @Override
    public void initialize(ContraseñaFuerte constraintAnnotation) {
        // Puedes usar esta función para configurar el validador, si es necesario.
    }

    // Lógica de validación
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false; // La contraseña no puede ser nula o vacía
        }

        // Regla: al menos 8 caracteres, una letra mayúscula, un número y un carácter especial
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return value.matches(regex);
    }
}
```

---

#### **3.3. Usar la anotación en un modelo o DTO**

Una vez definida la anotación, puedes usarla en tus clases de modelo o DTOs. Por ejemplo:

```java
import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @ContraseñaFuerte(message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial")
    private String contrasena;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

#### **3.4. Probar la validación en un controlador**

Crea un controlador para probar la validación:

```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String crearUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        return "Usuario creado: " + usuario.getNombre();
    }
}
```

---

### **4. Probar la funcionalidad**

#### **4.1. Crear un usuario válido**
- Endpoint: `POST /usuarios`
- Body:
  ```json
  {
      "nombre": "Maria",
      "contrasena": "Fuerte123@"
  }
  ```
- Respuesta:
  ```json
  {
      "message": "Usuario creado: Maria"
  }
  ```

#### **4.2. Crear un usuario inválido**
- Body:
  ```json
  {
      "nombre": "Maria",
      "contrasena": "debiles"
  }
  ```
- Respuesta:
  ```json
  {
      "contrasena": "La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial"
  }
  ```

---

### **5. Explicación de los componentes**

| Componente                         | Descripción                                                                                      |
|------------------------------------|--------------------------------------------------------------------------------------------------|
| **`@Constraint`**                  | Vincula la anotación personalizada con el validador que contiene la lógica.                      |
| **`@Target`**                      | Especifica dónde puede aplicarse la anotación (atributos, métodos, etc.).                        |
| **`@Retention`**                   | Define que la anotación estará disponible en tiempo de ejecución.                                |
| **`ConstraintValidator`**          | Clase que contiene la lógica de validación personalizada.                                        |
| **`@Valid`**                       | Anotación que obliga a validar los campos del objeto recibido en el controlador.                |
| **Regex en `isValid`**             | Expresión regular utilizada para definir las reglas de la contraseña (personalizable según necesidades). |

---

### **6. Ventajas de usar una anotación personalizada**
1. **Reutilización**: La lógica de validación puede aplicarse en cualquier clase simplemente usando la anotación.
2. **Centralización**: Si cambian las reglas, solo necesitas actualizar el validador.
3. **Código limpio**: Evitas agregar lógica de validación repetitiva en controladores o servicios.
4. **Mensajes personalizados**: Puedes definir mensajes específicos para cada uso de la anotación.

---