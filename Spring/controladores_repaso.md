Un controlador en **Spring** es una clase que maneja las solicitudes HTTP entrantes y las redirige a servicios o lógica de negocio. Spring utiliza anotaciones para configurar controladores, lo que facilita el manejo de rutas y la gestión de respuestas. A continuación, te explico cómo crear y usar un controlador paso a paso.

---

### **1. Crear un proyecto en Spring Boot**
- Puedes crear un proyecto Spring Boot usando [Spring Initializr](https://start.spring.io/).
- Agrega las dependencias necesarias, como **Spring Web**.

---

### **2. Crear la clase del controlador**
Los controladores se definen utilizando la anotación `@RestController` o `@Controller`. 

- **`@RestController`**: Combina `@Controller` y `@ResponseBody`, lo que significa que los métodos devolverán datos directamente en lugar de vistas.
- **`@Controller`**: Se usa para manejar vistas (en aplicaciones web tradicionales con HTML, Thymeleaf, etc.).

---

### **3. Definir rutas y manejar solicitudes**

```java
import org.springframework.web.bind.annotation.*;

@RestController // Define esta clase como un controlador REST
@RequestMapping("/api") // Ruta base para todas las solicitudes de este controlador
public class MiControlador {

    // Manejar una solicitud GET
    @GetMapping("/saludo")
    public String obtenerSaludo() {
        return "¡Hola desde Spring Boot!";
    }

    // Manejar una solicitud GET con parámetros
    @GetMapping("/saludo/{nombre}")
    public String saludoPersonalizado(@PathVariable String nombre) {
        return "¡Hola, " + nombre + "!";
    }

    // Manejar una solicitud POST
    @PostMapping("/crear")
    public String crearDato(@RequestBody String dato) {
        return "Dato recibido: " + dato;
    }

    // Manejar una solicitud PUT
    @PutMapping("/actualizar/{id}")
    public String actualizarDato(@PathVariable int id, @RequestBody String dato) {
        return "Dato con ID " + id + " actualizado a: " + dato;
    }

    // Manejar una solicitud DELETE
    @DeleteMapping("/eliminar/{id}")
    public String eliminarDato(@PathVariable int id) {
        return "Dato con ID " + id + " eliminado";
    }
}
```

---

### **4. Explicación del código**
- **`@RequestMapping`**: Define una ruta base común para el controlador.
- **`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`**: Mapean rutas específicas según el método HTTP.
- **`@PathVariable`**: Captura valores dinámicos de la URL.
- **`@RequestBody`**: Captura el cuerpo de una solicitud HTTP (por ejemplo, JSON).
- **`@ResponseBody`**: Indica que el valor devuelto se serializará como JSON (implícito en `@RestController`).

---

### **5. Probar el controlador**
Puedes usar herramientas como **Postman**, **cURL** o el navegador para probar las rutas. Por ejemplo:
- Para la ruta `GET /api/saludo`, se debería devolver el mensaje: "¡Hola desde Spring Boot!".
- Para la ruta `POST /api/crear` con un cuerpo, se devolverá el mensaje con el dato recibido.

---

### **6. Agregar lógica adicional**
Un controlador puede delegar la lógica de negocio a servicios. Por ejemplo:

```java
@RestController
@RequestMapping("/api")
public class MiControlador {
    private final MiServicio servicio;

    public MiControlador(MiServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/saludo")
    public String obtenerSaludo() {
        return servicio.generarSaludo();
    }
}
```

Servicio asociado:

```java
import org.springframework.stereotype.Service;

@Service
public class MiServicio {
    public String generarSaludo() {
        return "¡Hola desde el servicio!";
    }
}
```

---

### **7. Estructura recomendada del proyecto**
La estructura típica de un proyecto Spring es:
```
src/main/java/com/miapp/
    ├── controller/
    │       └── MiControlador.java
    ├── service/
    │       └── MiServicio.java
    ├── repository/
    │       └── MiRepositorio.java
    └── model/
            └── MiEntidad.java
```

Con esta estructura, separas responsabilidades y mantienes un código más limpio y escalable.