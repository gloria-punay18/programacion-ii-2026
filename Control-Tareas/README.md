


* Gloria Esperanza Punay Xocoxic
* Carné: 9941-25-2203
--------------------------------------------------------
# Control de Tareas - Proyecto Maven, HTTP y REST

## Descripción del Problema
Este proyecto es una aplicación Java desarrollada con Maven que permite administrar y controlar las tareas pendientes y completadas de una persona. Además, incluye el diseño lógico y conceptual de una API REST para la gestión futura de los recursos de tipo `Tarea`.

## Tecnologías Utilizadas
* Java 17+
* Apache Maven
* IntelliJ IDEA
* Git / GitHub

## Datos del Proyecto Maven
* **groupId:** `com.estudiante`
* **artifactId:** `control-tareas`
* **version:** `1.0-SNAPSHOT`

### Explicación de Conceptos Maven:
- **groupId:** Es el identificador único de la organización o estructura de paquetes del proyecto, utilizado para evitar conflictos de nombres entre bibliotecas.
- **artifactId:** Es el nombre del proyecto o módulo individual que se está construyendo, el cual sirve para nombrar el archivo empaquetado final.
- **version:** Indica la versión actual de desarrollo o publicación del proyecto, permitiendo la trazabilidad y control de cambios.

## Instrucciones para Compilar y Ejecutar
Para compilar y empaquetar el proyecto desde la terminal o mediante la interfaz de Maven en el IDE, se utilizan los siguientes comandos:

```bash
# Limpiar archivos generados previamente
mvn clean

# Compilar el código fuente
mvn compile

# Ejecutar las pruebas unitarias
mvn test

# Empaquetar el proyecto en un archivo JAR
mvn package
-----------------------------------------------------------------------------------------------------------------
## Diseño de Endpoints REST

| Operación | Método HTTP | Endpoint | Respuesta esperada |
| :--- | :--- | :--- | :--- |
| Consultar todas las tareas | GET | `/api/tareas` | `200 OK` |
| Consultar una tarea | GET | `/api/tareas/{id}` | `200 OK` |
| Registrar una tarea | POST | `/api/tareas` | `201 Created` |
| Modificar una tarea | PUT | `/api/tareas/{id}` | `200 OK` |
| Eliminar una tarea | DELETE | `/api/tareas/{id}` | `204 No Content` |
| Consultar una tarea inexistente | GET | `/api/tareas/{id}` | `404 Not Found` |

## Ejemplo del Objeto JSON

```json
{
  "id": 1,
  "titulo": "Comprar alimentos",
  "descripcion": "Comprar productos para la semana",
  "prioridad": "ALTA",
  "completada": false
}

