# Variables y operadores: Calculadora de edad

## Descripción breve del programa
Programa de consola desarrollado en Java que solicita datos personales al usuario, calcula su edad aproximada y total en meses. Valida la mayoría de edad empleando operadores relacionales sin utilizar estructuras condicionales `if`.

## Datos solicitados
* Nombre completo del estudiante
* Carné 
* Año de nacimiento
* Año actual

## Cálculos realizados
* **Edad aproximada:** `anioActual - anioNacimiento`
* **Edad en meses:** `edad * 12`
* **Mayoría de edad:** `edad >= 18` (evaluado mediante una variable de tipo `boolean`)

## Instrucciones para ejecutar el proyecto
1. Abrir el entorno **IntelliJ IDEA**.
2. Importar o abrir el proyecto `semana-02-variables-operadores`.
3. Ir a la ruta `src/CalculadoraEdad.java`.
4. Ejecutar el archivo `CalculadoraEdad.java` dando clic en el botón de reproducción (Run).
5. Interactuar con la consola ingresando los datos que solicita el programa.

## Nombre y carné del estudiante
* **Nombre:** Gloria Esperanza Punay Xocoxic
* **Carné:** 9941-25-22033

## Evidencia de ejecución
*(El documento completo `ejecucion.docx` se encuentra disponible en la carpeta `/evidencias`)*

```text
--- CALCULADORA DE EDAD ---
Ingrese su nombre completo: Gloria Esperanza Punay Xocoxic
Ingrese su carné: 9941-25-22033
Ingrese su año de nacimiento: 2005
Ingrese el año actual: 2026

=== RESULTADOS ===
Estudiante: Gloria Esperanza Punay Xocoxic
Carné: 9941-25-22033
Edad aproximada: 21 años
Edad en meses: 252 meses
¿Es mayor de edad?: true