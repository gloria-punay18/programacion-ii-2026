

* **Nombre: Gloria Esperanza Punay Xocoxic.**
* **Carné: 9941-25-22033**
 

# Sistema de Gestión de Envíos

## Descripción breve
Aplicación de consola en Java desarrollada para gestionar y calcular el costo de envíos nacionales e internacionales de una empresa de logística, aplicando validaciones de datos en tiempo de ejecución.

## Objetivo
Implementar los conceptos clave de la Programación Orientada a Objetos (POO) mediante la construcción de un sistema dinámico que modele las tarifas y reglas de negocio para diferentes tipos de envíos.

## Temas aplicados
* **Encapsulamiento:** Protección de los atributos de las clases mediante modificadores de acceso privados y métodos getter.
* **Herencia:** Extensión de la superclase Envio en las subclases EnvioNacional y EnvioInternacional.
* **Sobrescritura (@Override):** Redefinición del método calcularCostoFinal() para adaptar el cálculo a las reglas de cada tipo de envío.
* **Sobrecarga:** Implementación de dos versiones del método mostrarResumen() para controlar la visualización de la información.
* **Polimorfismo:** Manejo de instancias de EnvioNacional y EnvioInternacional mediante referencias de tipo Envio.
* **Validación de entradas:** Manejo de excepciones e instrucciones de control para evitar datos nulos, vacíos o menores/iguales a cero.

## Estructura del proyecto
src/
├── Envio.java
├── EnvioNacional.java
├── EnvioInternacional.java
└── Main.java

## Instrucciones para ejecutar el programa
1. Clonar o descargar este repositorio en tu equipo local.
2. Abrir la carpeta del proyecto en un entorno de desarrollo (IntelliJ IDEA, Eclipse, NetBeans) o en la terminal.
3. Asegurarse de contar con Java JDK instalado.
4. Navegar hasta la carpeta src/ mediante la consola y compilar los archivos:
   javac Main.java Envio.java EnvioNacional.java EnvioInternacional.java
5. Ejecutar la clase principal Main:
   java Main

## Resultado esperado
El programa mostrará un menú interactivo en consola para registrar envíos nacionales o internacionales. Solicitará los datos correspondientes, rechazará entradas inválidas y desplegará un resumen detallado con el desglose del costo base, recargos y costo final.