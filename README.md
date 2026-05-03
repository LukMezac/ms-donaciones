# Microservicio de Donaciones - Proyecto Donaton

Este repositorio contiene el microservicio de **Donaciones**, un componente fundamental del ecosistema **Donaton** desarrollado con **Spring Boot 3.x**. Su función principal es gestionar el ciclo de vida de los suministros donados, asegurando una solución técnica escalable y eficiente.

## 🏗️ Arquitectura y Patrones de Diseño
Este microservicio ha sido construido utilizando arquetipos **Maven** para asegurar una estructura profesional. Se han implementado los siguientes patrones de diseño:

*   **🏭 Factory Pattern**: Es el patrón principal de este módulo. Se utiliza la clase `DonacionFactory` para instanciar dinámicamente objetos de tipo `Alimento`, `Higiene`, `Ropa` e `InsumoMedico` según el tipo de donación recibida.
*   **📂 Repository Pattern**: Utiliza **Spring Data JPA** para abstraer la lógica de persistencia, facilitando el mantenimiento y desacoplamiento del código.
*   **📦 DTO (Data Transfer Object)**: Implementado para asegurar que la comunicación con el BFF sea eficiente y no exponga directamente las entidades de la base de datos.

## 🛠️ Tecnologías Utilizadas
*   **☕ Java 17**: Para aprovechar las mejoras en rendimiento y tipos de datos modernos.
*   **🍃 Spring Boot 3.x**: Framework ágil para la creación de microservicios robustos.
*   **🔨 Maven**: Gestión de dependencias y estandarización del proyecto bajo arquetipos.
*   **📊 Spring Data JPA**: Para una gestión de datos ágil y persistencia eficiente.

## 📂 Estructura del Componente
El código fuente está organizado siguiendo el formato de entrega solicitado:
*   `controller/`: Endpoints REST para la gestión y recepción de donaciones.
*   `factory/`: Implementación del patrón de diseño Factory (Clases concretas y Creador).
*   `service/`: Lógica de negocio y orquestación de la persistencia.
*   `model/`: Entidades de negocio que representan los suministros.
*   `repository/`: Interfaces para la comunicación con la base de datos.

## 🚀 Instalación y Ejecución

1.  **📋 Requisitos**:
    *   Java 17 instalado.
    *   Maven 3.8+.

2.  **⚙️ Configuración**:
    El servicio opera de forma predeterminada en el puerto `8081`. Puedes revisar este valor en `src/main/resources/application.properties`.

3.  **▶️ Ejecución**:
    ```bash
    mvn spring-boot:run
    ```

## 🧪 Pruebas y Calidad
*   **✅ Pruebas Unitarias**: El proyecto incluye validaciones unitarias (JUnit/Mockito) para asegurar que la lógica de la Factory y los servicios funcione correctamente.
*   **🧹 Clean Code**: Se aplican principios de código limpio para asegurar que el sistema sea fácil de leer, mantener y extender.

---
