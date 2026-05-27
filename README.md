# 🏢 Sistema de Gestión de Empleados - Spring Boot

[cite_start]Este proyecto consiste en una aplicación backend desarrollada con **Spring Boot** para la gestión y verificación de una base de datos de empresa (Entidad: Empleados)[cite: 3]. [cite_start]Se han implementado tanto la lógica de negocio básica (CRUD) como una suite completa de pruebas automatizadas (unitarias y de integración) para garantizar el correcto funcionamiento del software[cite: 8, 10].

[cite_start]Este repositorio cumple con los requisitos prácticos de la asignatura **Entornos de Desarrollo** (Resultado de Aprendizaje: *Ra3 - Verifica el funcionamiento de programas diseñando y realizando pruebas*)[cite: 1, 33, 34].

---

## 🛠️ Requisitos Previos

Antes de ejecutar la aplicación o sus pruebas, asegúrate de tener instalado:
* **Java JDK 17** o superior.
* **Apache Maven 3.8+** (o utilizar el wrapper `./mvnw` incluido).
* [cite_start]Un IDE compatible (ej. **IntelliJ IDEA** o **Eclipse**).

---

## 🧪 Qué se ha probado (Plan de Pruebas)

[cite_start]El aseguramiento de la calidad del software se ha estructurado en tres niveles[cite: 4]:

### 1. Casos de Prueba (QA Teórico)
[cite_start]Se han diseñado y verificado 5 escenarios fundamentales[cite: 5]:
* [cite_start]**CP-01 (Alta):** Creación de un registro válido con generación de ID automático[cite: 5, 6].
* [cite_start]**CP-02 (Consulta):** Recuperación de datos de un empleado existente por su ID[cite: 5, 6].
* [cite_start]**CP-03 (Modificación):** Actualización de datos de un registro en la base de datos[cite: 5, 6].
* [cite_start]**CP-04 (Borrado):** Eliminación física de un registro y verificación de su ausencia posterior[cite: 5, 6].
* [cite_start]**CP-05 (Error de Datos):** Intento de registro con un email ya duplicado en el sistema[cite: 5, 6].

### 2. Pruebas Unitarias (Capa de Servicio)
[cite_start]Se implementaron 3 pruebas unitarias utilizando **JUnit 5** y **Mockito** enfocadas en aislar la lógica de negocio de `EmpleadoService`[cite: 8]:
* `testGuardarEmpleadoExitoso()`: Valida el registro correcto simulando un repositorio vacío.
* `testGuardarEmpleadoEmailDuplicado()`: Comprueba que el sistema lanza una excepción `IllegalArgumentException` si el email ya existe.
* `testEliminarEmpleadoInexistente()`: Verifica el control de errores al intentar borrar un ID que no figura en la base de datos.

### 3. Pruebas de Integración (Flujo Completo)
[cite_start]Se desarrolló 1 prueba de integración (`EmpleadoIntegrationTest`) usando `@SpringBootTest` y `MockMvc`. [cite_start]Esta prueba simula peticiones HTTP reales y verifica la comunicación íntegra entre las capas de **Controlador ➡️ Servicio ➡️ Base de datos en memoria (H2)**.

---

## 🚀 Cómo Ejecutar las Pruebas

[cite_start]Para validar el funcionamiento del software y ejecutar toda la suite de pruebas automatizadas desde la consola de comandos, sitúate en la raíz del proyecto y ejecuta[cite: 17, 22]:

```bash
mvn clean test
