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
## **Anotacion @interface**

La anotación **`@interface`** en Java se utiliza para definir una **nueva anotación personalizada**. Es una forma especial de declarar tipos que pueden usarse como metadatos en el código. Las anotaciones permiten agregar información adicional a clases, métodos, campos, parámetros y otros elementos del código, que luego pueden ser procesados por herramientas, compiladores o en tiempo de ejecución.

---

### **1. Sintaxis básica de `@interface`**

Para declarar una nueva anotación, usas `@interface` seguido del nombre de la anotación. Por ejemplo:

```java
public @interface MiAnotacion {
    // Atributos opcionales
}
```

Esto define una nueva anotación llamada `@MiAnotacion` que puedes usar en tu código como:

```java
@MiAnotacion
public class MiClase {
    // Código aquí
}
```

---

### **2. Componentes principales de `@interface`**

#### **2.1. Declarar atributos en la anotación**

Dentro de una anotación, puedes declarar atributos que permitan configurarla. Por ejemplo:

```java
public @interface MiAnotacion {
    String valor();
    int numero() default 0; // Atributo con valor predeterminado
}
```

- **Atributos sin valor predeterminado**: Son obligatorios y deben especificarse al usar la anotación.
- **Atributos con valor predeterminado (`default`)**: Son opcionales y, si no se proporcionan, toman el valor definido.

Uso:

```java
@MiAnotacion(valor = "Hola", numero = 5) // Ambos atributos proporcionados
public class MiClase {}

@MiAnotacion(valor = "Hola") // Se usa el valor predeterminado para 'numero'
public class OtraClase {}
```

---

#### **2.2. Tipos de atributos permitidos**

Los atributos en una anotación solo pueden ser de ciertos tipos:

1. **Tipos primitivos**: `int`, `float`, `boolean`, etc.
2. **`String`**.
3. **`Class`**.
4. **Enumeraciones (`enum`)**.
5. **Otras anotaciones**.
6. **Arreglos** de los tipos anteriores.

Ejemplo:

```java
public @interface Configuracion {
    int numero();
    String texto() default "Por defecto";
    Class<?> clase();
    Nivel nivel() default Nivel.MEDIO;

    enum Nivel { BAJO, MEDIO, ALTO }
}
```

Uso:

```java
@Configuracion(numero = 42, clase = String.class, nivel = Configuracion.Nivel.ALTO)
public class Ejemplo {}
```

---

### **3. Meta-anotaciones comunes**

Las **meta-anotaciones** son anotaciones especiales que se aplican a otras anotaciones para definir su comportamiento. Estas son las más importantes:

| Meta-anotación           | Descripción                                                                                   |
|--------------------------|-----------------------------------------------------------------------------------------------|
| **`@Retention`**         | Define en qué momento estará disponible la anotación (compilación, ejecución, etc.).         |
| **`@Target`**            | Especifica dónde puede aplicarse la anotación (clases, métodos, campos, etc.).                |
| **`@Documented`**        | Indica que la anotación debe incluirse en la documentación generada (como Javadoc).           |
| **`@Inherited`**         | Permite que una anotación en una clase base se herede a las subclases.                        |
| **`@Repeatable`**        | Permite aplicar la misma anotación varias veces a un elemento.                                |

#### **3.1. `@Retention`**

Define en qué momento la anotación estará disponible:

- **`RetentionPolicy.CLASS`**: La anotación se guarda en el archivo `.class`, pero no está disponible en tiempo de ejecución.
- **`RetentionPolicy.RUNTIME`**: La anotación está disponible en tiempo de ejecución (útil para frameworks como Spring).
- **`RetentionPolicy.SOURCE`**: La anotación se elimina después de la compilación (solo para herramientas como linters).

Ejemplo:

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface MiAnotacion {}
```

#### **3.2. `@Target`**

Especifica los elementos a los que se puede aplicar la anotación. Opciones comunes:
- **`ElementType.TYPE`**: Clases, interfaces o enums.
- **`ElementType.FIELD`**: Atributos de clase.
- **`ElementType.METHOD`**: Métodos.
- **`ElementType.PARAMETER`**: Parámetros de métodos.
- **`ElementType.CONSTRUCTOR`**: Constructores.

Ejemplo:

```java
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MiAnotacion {}
```

#### **3.3. `@Inherited`**

Permite que las subclases hereden la anotación de una clase base. Solo se aplica a `@Target(ElementType.TYPE)`.

Ejemplo:

```java
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnotacionHeredada {}

@AnotacionHeredada
public class ClaseBase {}

public class SubClase extends ClaseBase {} // SubClase también tiene @AnotacionHeredada
```

#### **3.4. `@Repeatable`**

Permite aplicar varias veces la misma anotación a un elemento. Necesita un contenedor.

Ejemplo:

```java
@Repeatable(MisAnotaciones.class)
public @interface MiAnotacion {
    String valor();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MisAnotaciones {
    MiAnotacion[] value();
}

// Uso
@MiAnotacion(valor = "Primera")
@MiAnotacion(valor = "Segunda")
public class MiClase {}
```

---

### **4. Ejemplo práctico**

#### **4.1. Crear una anotación personalizada**
```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidarCampo {
    String mensaje() default "Campo inválido";
}
```

#### **4.2. Usar la anotación**
```java
public class Usuario {
    @ValidarCampo(mensaje = "El nombre no puede estar vacío")
    private String nombre;

    // Getters y setters
}
```

#### **4.3. Procesar la anotación en tiempo de ejecución**
```java
import java.lang.reflect.Field;

public class Validador {
    public static void validar(Object objeto) throws Exception {
        for (Field campo : objeto.getClass().getDeclaredFields()) {
            if (campo.isAnnotationPresent(ValidarCampo.class)) {
                campo.setAccessible(true);
                Object valor = campo.get(objeto);
                if (valor == null || valor.toString().isEmpty()) {
                    ValidarCampo anotacion = campo.getAnnotation(ValidarCampo.class);
                    throw new Exception(anotacion.mensaje());
                }
            }
        }
    }
}
```

---

### **5. Ventajas de usar `@interface`**
1. **Reutilización**: Centraliza lógica repetitiva.
2. **Claridad**: Proporciona un mecanismo claro para aplicar metadatos.
3. **Extensibilidad**: Compatible con herramientas y frameworks como Spring, Hibernate, etc.

---