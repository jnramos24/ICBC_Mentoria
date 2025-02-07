## **Búsqueda rápida y eficiente en IntelliJ IDEA** 

### 1️ **Buscar cualquier cosa (Doble Shift)**
   - Presiona **Shift** dos veces rápidamente.
   - Abre la caja de búsqueda universal donde puedes buscar:
     - Archivos (`.java`, `.xml`, `.properties`, etc.).
     - Clases y métodos.
     - Acciones y configuraciones.

---

### 2️ **Buscar archivos específicos**
   - Atajo: **Ctrl + Shift + N** (Windows/Linux) o **Cmd + Shift + O** (Mac).
   - Útil para encontrar archivos como `.xml`, `.properties`, `.json`, etc.
   - Puedes escribir parte del nombre o usar comodines:
     - `*config` → Busca archivos que contengan "config".
     - `app*.yml` → Encuentra archivos que empiecen con "app" y terminen en `.yml`.

---

### 3️ **Buscar clases**
   - Atajo: **Ctrl + N** (Windows/Linux) o **Cmd + O** (Mac).
   - Para buscar clases dentro del proyecto.
   - Puedes usar:
     - **Mayúsculas en CamelCase** (`MySeCo` → `MyServiceController`).
     - **Prefijo de paquete** (`com.exa.MySe` → Encuentra `com.example.MyService`).

---

### 4️ **Buscar métodos o campos en una clase**
   - Atajo: **Ctrl + F12** (Windows/Linux) o **Cmd + F12** (Mac).
   - Muestra una lista de métodos, constructores y campos en una clase.
   - Puedes escribir el nombre de un método para encontrarlo rápidamente.

---

### 5️ **Buscar una palabra en todo el proyecto**
   - Atajo: **Ctrl + Shift + F** (Windows/Linux) o **Cmd + Shift + F** (Mac).
   - Busca texto en todos los archivos del proyecto (similar a un "grep").
   - Opciones avanzadas:
     - 🔹 **Match Case** → Distingue entre mayúsculas y minúsculas.
     - 🔹 **Regex** → Usa expresiones regulares.
     - 🔹 **Scope** → Limita la búsqueda a ciertos directorios.

---

### 6️ **Buscar referencias a un método o variable**
   - Atajo: **Alt + F7** (Windows/Linux) o **Cmd + F7** (Mac).
   - Encuentra dónde se usa un método, clase o variable.
   - Puedes buscar referencias en:
     - Todo el proyecto.
     - Solo la clase actual.
     - Solo un paquete específico.

---

### 7️ **Buscar implementaciones de una interfaz o método abstracto**
   - Atajo: **Ctrl + Alt + B** (Windows/Linux) o **Cmd + Alt + B** (Mac).
   - Muestra todas las clases que implementan una interfaz o sobrescriben un método.

---

### 8️ **Ir directamente a la declaración de una clase o método**
   - Atajo: **Ctrl + B** (Windows/Linux) o **Cmd + B** (Mac).
   - Llévate al código fuente de una clase o método.
   - Funciona también con clases de librerías externas si tienes el código disponible.

---

### 9️ **Buscar una acción o configuración dentro de IntelliJ**
   - Atajo: **Ctrl + Shift + A** (Windows/Linux) o **Cmd + Shift + A** (Mac).
   - Encuentra cualquier acción en IntelliJ, como "Reformatear código" o "Ejecutar pruebas".

---

##  **Consejos avanzados**
- **Habilita la indexación rápida**: Ve a `Settings > Editor > General > Code Completion` y optimiza la indexación para proyectos grandes.
- **Filtra por tipo de archivo**: Cuando busques, usa `:ext` (Ejemplo: `config:xml` para buscar solo archivos `.xml`).
- **Usa marcadores rápidos**: `F11` para marcar una línea y `Shift + F11` para ver la lista de marcadores.
- **Usa "Navigate Bar"**: Con `Alt + Home` (Windows) o `Cmd + ↑` (Mac) puedes moverte rápido en la jerarquía de archivos.
