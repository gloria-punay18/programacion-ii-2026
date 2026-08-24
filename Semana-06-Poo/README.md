Nombre: Gloria Esperanza Punay Xocoxic
Carné: 9941-25-22033
Nombre: Jussely Saraí Mutas Chitic
Carné: 9941-25-28221
Nombre: Kevin Oswaldo Rivera Sánchez
Carné: 99941-25-612
Programación II


# Programación Orientada a Objetos: Clínica Veterinaria

Descripción del proyecto

Proyecto desarrollado para poner en práctica los pilares de la Programación Orientada a Objetos en Java. En esta actividad  se implementó una solución para gestionar los pacientes de una clínica veterinaria aplicando conceptos como abstracción, encapsulamiento, herencia y polimorfismo.

# Objetivos alcanzados

- Diseñar una clase base abstracta que sirva como plantilla para los diferentes tipos de animales.
- Proteger los datos de los pacientes utilizando encapsulamiento y validaciones de datos (como controlar que el peso no sea negativo).
- Reutilizar código mediante herencia e invocar los constructores del padre con la palabra reservada `super`.
- Aplicar polimorfismo mediante métodos abstractos y sobrescritura para que cada animal tenga un comportamiento y un costo de consulta propio.

# Conceptos de POO aplicados en el código

- Clases abstractas y métodos abstractos: Para definir métodos obligatorios sin implementación en la clase base (`emitirSonido()` y `calcularCostoConsulta()`).
- Encapsulamiento: Uso de atributos privados (`private`) accesibles mediante getters y métodos de actualización controlada.
- Identificación de registros: Asignación de un `codigoPaciente` a cada animal para su control en la clínica.
- Constructores y `this`: Asignación de valores iniciales a las propiedades de los objetos.
- Herencia (`extends`) y `super`: Extensión de las subclases para heredar atributos y métodos comunes.
- Sobrescritura (`@Override`): Implementación personalizada del comportamiento en cada subclase.
- Colecciones y Polimorfismo: Manejo de un arreglo de tipo `Mascota[]` para procesar diferentes animales dentro de un mismo bucle.

# Explicación de la solución

Para resolver el problema planteado, definí la clase abstracta **Mascota** con los atributos generales (`codigoPaciente`, `nombre`, `edadMeses`, `pesoKg`). De ella se derivan las clases hijas **Perro**, **Gato** y **Ave**, cada una agregando una propiedad específica (`raza`, `esInterior` y `tipo` respectivamente).

Cada clase hija implementa la lógica particular para calcular el precio de la consulta y emitir su sonido característico.

# Estructura de archivos

src/
├── Mascota.java
├── Perro.java
├── Gato.java
├── Ave.java
└── Main.java

# ¿Cómo ejecutar mi programa?

Desde la clase `Main.java`, ejecuto el método principal:
public static void main(String[] args)

Al ejecutar la clase `Main`, el programa realiza lo siguiente:
1. Instancia distintos objetos de tipo `Perro`, `Gato` y `Ave` asignando sus respectivos códigos.
2. Almacena todos los objetos dentro de un arreglo polimórfico de tipo `Mascota[]`.
3. Recorre el arreglo mediante un bucle para mostrar la información del paciente, hacer sonar al animal y calcular automáticamente el costo total de la consulta según sus características.

# Demostración de la salida en consola

[P-001] Luna | 14 meses | 12.5 kg
Luna: ¡Guau!
Costo consulta: $21250.0
----------------------------------------
[G-001] Milo | 24 meses | 4.8 kg
Milo: ¡Miau!
Costo consulta: $12000.0
----------------------------------------
[A-001] Piolín | 10 meses | 0.4 kg
Piolín: ¡Pío, pío!
Costo consulta: $10000.0
----------------------------------------








