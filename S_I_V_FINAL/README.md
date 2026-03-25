# S.I.V - Sistema de Inventario y Valoración

Aplicación de escritorio desarrollada en Java para la gestión integral de inventario, contabilidad y administración de productos. Utiliza el patrón arquitectónico **MVP (Model-View-Presenter)** con soporte para múltiples idiomas.

## Características Principales

- Gestión de Productos
- Registro de Personas
- Control de Movimientos Contables
- Exportación en Archivos CSV
- Soporte Multiidioma (Español, Inglés, Portugués)

## Requisitos

- Java 17 o superior
- Maven 3.6+

## Instalación y Ejecución

### 1. Compilar el Proyecto
```bash
mvn clean compile
```

### 2. Ejecutar la Aplicación
```bash
mvn exec:java -Dexec.mainClass="co.edu.uptc.Runner"
```

O empaquetarlo y ejecutar:
```bash
mvn clean package
java -jar target/S_I_V-1.0-SNAPSHOT.jar
```

## Estructura del Proyecto

```
src/main/java/co/edu/uptc/
├── Runner.java                 # Punto de entrada
├── model/                      # Capa de Modelo
│   ├── Model.java
│   ├── ValidationService.java
│   ├── service/               # Servicios de negocio
│   ├── persistence/           # Manejo de CSV
│   └── structures/            # Estructuras de datos (DoublyLinkedList)
├── view/                       # Capa de Vista (CLI)
│   ├── MainView.java
│   ├── InputReader.java
│   └── menu/                  # Menús temáticos
├── presenter/                  # Capa de Presentador (MVP)
├── config/                     # Configuración e i18n
└── pojo/                       # Modelos de datos
```

## Pruebas

```bash
mvn test
```

---

## Autor

Daniel Felipe Villamil Pinto