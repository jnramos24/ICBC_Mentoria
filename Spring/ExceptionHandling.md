## Manejo Global de Excepciones en Spring Boot

El manejo global de excepciones se implementa utilizando la anotación **`@RestControllerAdvice`**, que actúa como un controlador especializado para interceptar excepciones lanzadas en toda la aplicación.

### **1. ¿Por qué usar manejo global de excepciones?**
- **Consistencia**: Todas las excepciones se gestionan de forma uniforme.
- **Simplicidad**: Reduce el código repetitivo en los controladores.
- **Mantenibilidad**: Cambiar la lógica de manejo de errores en un solo lugar afecta a toda la aplicación.
- **Claridad para el cliente**: Proporciona respuestas claras y estandarizadas en formato JSON.

---

### **2. Implementación paso a paso**

#### **2.1. Crear una clase de manejo global de excepciones**
Usaremos `@RestControllerAdvice` para interceptar y manejar excepciones específicas o genéricas.

```java
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejar errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return errores;
    }

    // Manejar excepciones de tipo RuntimeException
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> manejarRuntimeException(RuntimeException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", ex.getMessage());
        return respuesta;
    }

    // Manejar cualquier otra excepción no prevista
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> manejarExcepcionGeneral(Exception ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", "Ha ocurrido un error inesperado");
        respuesta.put("detalles", ex.getMessage());
        return respuesta;
    }
}
```

---

#### **2.2. Explicación del código**

1. **`@RestControllerAdvice`**:
   - Marca esta clase como un asesor global que intercepta excepciones en toda la aplicación.
   - Funciona en combinación con `@ExceptionHandler`.

2. **`@ExceptionHandler`**:
   - Asocia un método específico con una o más excepciones.
   - Captura la excepción lanzada en cualquier controlador de la aplicación.

3. **`@ResponseStatus`**:
   - Define el código HTTP que se devolverá al cliente (por ejemplo, `400 Bad Request`, `500 Internal Server Error`).

4. **Manejadores de excepción en el ejemplo**:
   - **`MethodArgumentNotValidException`**: Maneja errores de validación, como los generados por anotaciones como `@NotBlank`, `@Email`, etc.
   - **`RuntimeException`**: Captura cualquier excepción que herede de `RuntimeException`.
   - **`Exception`**: Captura cualquier excepción genérica no manejada previamente.

---

### **3. Ejemplo de uso**

Supongamos que tienes el siguiente controlador:

```java
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private List<UsuarioDTO> usuarios = new ArrayList<>();
    private long contadorId = 1;

    @PostMapping
    public UsuarioDTO crearUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        usuario.setId(contadorId++);
        usuarios.add(usuario);
        return usuario;
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerUsuario(@PathVariable Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
```

---

#### **Pruebas de la API**

1. **Error de validación (400 Bad Request)**
   - Endpoint: `POST /usuarios`
   - Body inválido:
     ```json
     {
         "nombre": "",
         "email": "correo-invalido",
         "edad": 15,
         "contrasena": "123"
     }
     ```
   - Respuesta:
     ```json
     {
         "nombre": "El nombre no puede estar vacío",
         "email": "El email debe ser válido",
         "edad": "La edad mínima es 18 años",
         "contrasena": "La contraseña debe tener al menos 8 caracteres, una mayúscula y un número"
     }
     ```

2. **Error al buscar un usuario inexistente (500 Internal Server Error)**
   - Endpoint: `GET /usuarios/100`
   - Respuesta:
     ```json
     {
         "error": "Usuario no encontrado"
     }
     ```

3. **Error inesperado (500 Internal Server Error)**
   - Si ocurre un error no previsto en el código, se capturará con el manejador genérico:
     ```json
     {
         "error": "Ha ocurrido un error inesperado",
         "detalles": "Descripción detallada del error"
     }
     ```

---

### **4. Extensiones avanzadas**

#### **4.1. Personalizar el formato de la respuesta**
Puedes crear una clase de respuesta estándar para estructurar las respuestas de error:

```java
public class ErrorResponse {
    private String mensaje;
    private String detalle;

    public ErrorResponse(String mensaje, String detalle) {
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getDetalle() {
        return detalle;
    }
}
```

Usar esta clase en el manejador:

```java
@ExceptionHandler(Exception.class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public ErrorResponse manejarExcepcionGeneral(Exception ex) {
    return new ErrorResponse("Ha ocurrido un error inesperado", ex.getMessage());
}
```

---

#### **4.2. Registrar logs**
Es una buena práctica registrar los errores usando un framework como **SLF4J** o **Logback**:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> manejarExcepcionGeneral(Exception ex) {
        logger.error("Error no manejado: ", ex);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", "Ha ocurrido un error inesperado");
        respuesta.put("detalles", ex.getMessage());
        return respuesta;
    }
}
```

---

### **5. Ventajas del manejo global**

1. **Centralización**: Todas las excepciones se manejan en un solo lugar.
2. **Reutilización**: Evita repetir código de manejo de errores en cada controlador.
3. **Consistencia**: Las respuestas son coherentes, lo que mejora la experiencia del cliente.
4. **Mantenibilidad**: Cambios en la lógica de manejo de errores afectan a toda la aplicación de forma inmediata.

---

### **6. Resumen**

| Característica                       | Descripción                                                |
|--------------------------------------|------------------------------------------------------------|
| **`@RestControllerAdvice`**          | Clase para manejar excepciones de forma global.            |
| **`@ExceptionHandler`**              | Método que gestiona una o más excepciones específicas.     |
| **`@ResponseStatus`**                | Define el código de estado HTTP devuelto.                  |
| **Centralización**                   | Evita duplicación de código en múltiples controladores.     |
| **Personalización**                  | Permite formatear las respuestas de error.                 |

---

