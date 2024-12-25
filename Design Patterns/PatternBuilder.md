El patrón de diseño **Builder** es un patrón creacional que se utiliza para construir objetos complejos paso a paso. Este patrón es útil cuando un objeto tiene muchas propiedades opcionales o requiere una configuración compleja para su creación. Con el patrón Builder, se puede separar el proceso de construcción de un objeto de su representación, permitiendo construir el objeto en pasos específicos y proporcionar una interfaz fluida para la construcción del objeto.

### Ventajas del patrón Builder
1. **Mejor legibilidad del código:** Facilita la creación de objetos con muchas propiedades opcionales.
2. **Inmutabilidad:** Permite crear objetos inmutables de forma controlada.
3. **Separación de la lógica de construcción:** La lógica de construcción está separada de la clase del objeto que se crea.

### Ejemplo en Java

Imaginemos que estamos construyendo un objeto `Persona` que tiene múltiples atributos opcionales:

```java
class Persona {
    private final String nombre; // Requerido
    private final String apellido; // Requerido
    private final int edad; // Opcional
    private final String direccion; // Opcional
    private final String telefono; // Opcional

    // Constructor privado, solo accesible desde el Builder
    private Persona(PersonaBuilder builder) {
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.edad = builder.edad;
        this.direccion = builder.direccion;
        this.telefono = builder.telefono;
    }

    // Getters para cada campo
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return "Persona{" +
               "nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", edad=" + edad +
               ", direccion='" + direccion + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }

    // Builder estático
    public static class PersonaBuilder {
        private final String nombre;
        private final String apellido;
        private int edad;
        private String direccion;
        private String telefono;

        // Constructor del builder con los campos obligatorios
        public PersonaBuilder(String nombre, String apellido) {
            this.nombre = nombre;
            this.apellido = apellido;
        }

        // Métodos para agregar propiedades opcionales
        public PersonaBuilder edad(int edad) {
            this.edad = edad;
            return this;
        }

        public PersonaBuilder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public PersonaBuilder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        // Método para construir el objeto Persona
        public Persona build() {
            return new Persona(this);
        }
    }
}
```

### Uso del patrón Builder

Ahora, para construir un objeto `Persona`, usamos el `PersonaBuilder`:

```java
public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona.PersonaBuilder("Juan", "Pérez")
                .edad(30)
                .direccion("123 Calle Falsa")
                .telefono("555-1234")
                .build();

        System.out.println(persona);
    }
}
```

### Explicación del código

1. **Constructor privado en `Persona`:** Esto asegura que el objeto solo se pueda crear a través del `PersonaBuilder`.
2. **PersonaBuilder:** Contiene los métodos `edad`, `direccion` y `telefono` para establecer los valores opcionales y retorna el mismo `PersonaBuilder` para encadenar las llamadas.
3. **Método `build`:** Este método crea una instancia de `Persona` usando los valores asignados en el `PersonaBuilder`.

Con este enfoque, el código es más legible y permite configurar los valores opcionales de forma flexible.

Si utilizamos la anotación `@Builder` de **Lombok**, podemos simplificar la implementación del patrón Builder en nuestra clase sin tener que escribir todo el código del builder manualmente. Lombok genera automáticamente el código del builder, lo cual facilita el mantenimiento y reduce la cantidad de código repetitivo.

Aquí tienes el ejemplo anterior de `Persona`, pero ahora utilizando `@Builder` de Lombok:

### Ejemplo usando Lombok

Para usar Lombok, asegúrate de agregar la dependencia en tu archivo de configuración (Maven o Gradle) y tenerlo habilitado en tu IDE.

```java
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Persona {
    private final String nombre; // Requerido
    private final String apellido; // Requerido
    private final int edad; // Opcional
    private final String direccion; // Opcional
    private final String telefono; // Opcional
}
```

### Explicación de las anotaciones:

1. **@Builder**: Genera un builder para la clase `Persona`. Lombok creará automáticamente una clase `PersonaBuilder` que incluye métodos para configurar cada campo.
2. **@Getter**: Genera automáticamente getters para todos los campos.
3. **@ToString**: Genera un método `toString()` para la clase, lo cual es útil para imprimir la representación del objeto.

### Uso del Builder con Lombok

El uso del builder de Lombok es similar al ejemplo anterior, pero el código de construcción de objetos es más limpio porque Lombok se encarga de generar el código del builder:

```java
public class Main {
    public static void main(String[] args) {
        Persona persona = Persona.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .edad(30)
                .direccion("123 Calle Falsa")
                .telefono("555-1234")
                .build();

        System.out.println(persona);
    }
}
```

### Ventajas de usar `@Builder` de Lombok

- **Menos código repetitivo**: No necesitas implementar manualmente el patrón Builder.
- **Legibilidad**: El código es más limpio y fácil de leer.
- **Flexibilidad**: Lombok también permite algunos ajustes personalizados en el builder, como métodos adicionales si fuera necesario.

Este enfoque es ideal cuando trabajas en proyectos donde Lombok está permitido, ya que reduce la carga de mantenimiento y agiliza la construcción de clases inmutables o con muchas propiedades opcionales.