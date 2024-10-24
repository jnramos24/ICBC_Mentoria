Para empaquetar un microservicio Spring con Maven y luego utilizarlo como una dependencia en otro microservicio, sigue estos pasos:

### 1. Empaquetar el microservicio como una biblioteca JAR

#### a) Configura el `pom.xml` de tu microservicio que deseas empaquetar

En el microservicio que deseas empaquetar, asegúrate de que en el archivo `pom.xml` tengas las configuraciones necesarias para construir un archivo JAR. Aquí tienes un ejemplo básico de configuración en `pom.xml`:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>mi-microservicio</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <dependencies>
        <!-- Aquí van tus dependencias de Spring y otras -->
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin para crear JAR -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

#### b) Compilar y empaquetar el microservicio

Usa el siguiente comando Maven para empaquetar el microservicio en un archivo JAR:

```bash
mvn clean package
```

Este comando generará un archivo JAR en la carpeta `target/` dentro del proyecto. El archivo será algo como `mi-microservicio-1.0.0.jar`.

### 2. Instalar el JAR localmente (opcional)

Si no vas a publicar este JAR en un repositorio remoto (como Nexus o Artifactory), puedes instalar el JAR en tu repositorio local de Maven para que otro proyecto lo utilice.

Para instalarlo localmente, usa el siguiente comando:

```bash
mvn install
```

Esto colocará el JAR en el repositorio local de Maven (`~/.m2/repository`), lo que lo hará accesible para otros proyectos en tu máquina.

### 3. Usar el JAR como dependencia en otro microservicio

#### a) Modificar el `pom.xml` del segundo microservicio

En el segundo microservicio donde quieras utilizar este JAR, debes agregarlo como dependencia en su archivo `pom.xml`:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>mi-microservicio</artifactId>
    <version>1.0.0</version>
</dependency>
```

Si instalaste el JAR en tu repositorio local, Maven lo encontrará allí. Si tienes un repositorio remoto, puedes publicar el JAR allí para que esté disponible para otros desarrolladores.

### 4. Publicar el JAR en un repositorio remoto (opcional)

Si necesitas que otros desarrolladores puedan acceder a este JAR, deberás publicarlo en un repositorio remoto. Para ello, configura un servidor como Nexus o Artifactory y ajusta las secciones de distribución en el `pom.xml`:

```xml
<distributionManagement>
    <repository>
        <id>repositorio-interno</id>
        <url>http://nexus.miempresa.com/repository/maven-releases/</url>
    </repository>
</distributionManagement>
```

Luego, publica el JAR con:

```bash
mvn deploy
```

Esto lo colocará en el repositorio remoto, y otros proyectos podrán usarlo agregando la dependencia en su `pom.xml`.

### Resumen

1. Configura el `pom.xml` del microservicio que deseas empaquetar como JAR.
2. Usa `mvn clean package` para crear el archivo JAR.
3. Instala el JAR localmente con `mvn install` o publícalo en un repositorio remoto.
4. Agrega el JAR como dependencia en el `pom.xml` de otro microservicio para reutilizarlo.

Esto te permitirá utilizar el microservicio empaquetado como dependencia en otros microservicios dentro de tu ecosistema Spring.