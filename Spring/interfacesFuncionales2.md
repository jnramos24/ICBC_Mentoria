### **Caso de Uso**
Supongamos que tienes un servicio en Spring que aplica diferentes tipos de descuentos a un precio basado en el tipo de usuario.

#### **Código original**
```java
@Service
public class DescuentoService {

    public double calcularDescuento(String tipoUsuario, double precio) {
        if ("VIP".equals(tipoUsuario)) {
            return precio * 0.8; // 20% de descuento
        } else if ("REGULAR".equals(tipoUsuario)) {
            return precio * 0.9; // 10% de descuento
        } else {
            return precio; // Sin descuento
        }
    }
}
```

Este enfoque utiliza condicionales que dificultan la escalabilidad cuando se agregan más tipos de usuarios o reglas de descuento.

---

### **Refactorización Usando Interfaces Funcionales**

#### **Paso 1: Crear una interfaz funcional**
```java
@FunctionalInterface
public interface ReglaDescuento {
    double aplicar(double precio);
}
```

#### **Paso 2: Crear un Map de estrategias**
Usa un `Map` para asociar cada tipo de usuario con su lógica de descuento.

```java
@Service
public class DescuentoService {

    private final Map<String, ReglaDescuento> reglasDescuento = new HashMap<>();

    public DescuentoService() {
        reglasDescuento.put("VIP", precio -> precio * 0.8);      // 20% de descuento
        reglasDescuento.put("REGULAR", precio -> precio * 0.9);  // 10% de descuento
        reglasDescuento.put("DEFAULT", precio -> precio);        // Sin descuento
    }

    public double calcularDescuento(String tipoUsuario, double precio) {
        return reglasDescuento
                .getOrDefault(tipoUsuario, reglasDescuento.get("DEFAULT"))
                .aplicar(precio);
    }
}
```

---

### **Ventajas del Enfoque**
- **Eliminación de condicionales complejos**: Usar un `Map` con lambdas simplifica el código.
- **Escalabilidad**: Es fácil agregar nuevas reglas de descuento.
- **Legibilidad**: El código es más claro y directo.

---

### **Ejemplo de Uso**
```java
@RestController
@RequestMapping("/descuento")
public class DescuentoController {

    private final DescuentoService descuentoService;

    public DescuentoController(DescuentoService descuentoService) {
        this.descuentoService = descuentoService;
    }

    @GetMapping("/{tipoUsuario}/{precio}")
    public double obtenerDescuento(
        @PathVariable String tipoUsuario, 
        @PathVariable double precio
    ) {
        return descuentoService.calcularDescuento(tipoUsuario, precio);
    }
}
```
---
