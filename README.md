# clean-architecture

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/clean-architecture-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)


# Estructura de carpetas

src
└── main
    ├── java
    │   └── local
    │       └── mateo
    │           └── cleanArchitecture
    │               ├── application     // Capa de Aplicación (casos de uso, lógica de negocio)
    │               │   ├── usecase     // Casos de uso de la aplicación
    │               │   └── service     // Servicios de aplicación
    │               ├── domain          // Capa de Dominio (entidades y lógica de negocio)
    │               │   ├── model       // Entidades de dominio
    │               │   └── repository  // Interfaces de repositorios (contratos)
    │               ├── infrastructure  // Capa de Infraestructura
    │               │   ├── adapter     // Adaptadores para conectarse a servicios externos
    │               │   │   ├── db      // Adaptadores de acceso a la base de datos
    │               │   │   └── rest    // Adaptadores REST (llamadas externas)
    │               │   └── repository  // Implementación de repositorios (DAO, JPA, etc.)
    │               └── presentation    // Capa de Controladores (exposición)
    │                   ├── controller  // Controladores REST para manejar las solicitudes HTTP
    │                   └── dto         // Objetos de Transferencia de Datos (DTOs)
    └── resources                       // Archivos de configuración y recursos de Quarkus
        ├── application.properties      // Configuración de Quarkus
        └── META-INF                    // Persistencia y configuraciones adicionales


---
## Descripción de cada carpeta:

### application: 
Contiene la lógica de negocio que orquesta las operaciones de la aplicación, conocida como la capa de aplicación. Aquí defines los casos de uso (use cases) y los servicios de aplicación que interactúan con el dominio.
### domain: 
Esta es la capa central del sistema, donde se definen las entidades de negocio y las interfaces de los repositorios. Es la capa independiente que representa el "corazón" del sistema.
### infrastructure: 
Esta capa contiene las implementaciones de las interfaces de dominio (como repositorios) y los adaptadores para acceder a servicios externos (base de datos, APIs REST, etc.). Aquí, puedes implementar las conexiones a las bases de datos y otras dependencias externas.
### presentation: 
Define los controladores REST y los objetos de transferencia de datos (DTOs) para exponer los endpoints de tu API. Aquí es donde se manejan las solicitudes y respuestas HTTP.
