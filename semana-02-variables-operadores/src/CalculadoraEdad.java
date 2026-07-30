import java.util.Scanner;
public class CalculadoraEdad {
    public static void main(String[] args) {


        // Habilita la lectura de datos que el usuario ingresa durante la ejecución
        Scanner teclado = new Scanner(System.in);

        String nombre;
        String carne;
        int anioNacimiento;
        int anioActual;
        int edad;
        int edadMeses;
        boolean mayorEdad;


        System.out.println("Ingrese su nombre: ");

        nombre = teclado.nextLine();

        System.out.println("Ingrese su carné: ");

        carne = teclado.nextLine();

        System.out.println("Ingrese su Año de nacimiento: ");

        anioNacimiento = teclado.nextInt();

        System.out.println("Ingrese Año actual: ");

        anioActual = teclado.nextInt();


        edad = anioActual - anioNacimiento;
        edadMeses = edad * 12;

        // Utiliza una comparación booleana para saber si la persona es mayor de edad
        mayorEdad = edad >=18;


        System.out.println("----- RESULTADO -----");
        System.out.println("Nombre: " + nombre);
        System.out.println("carné: " + carne);
        System.out.println("Edad Aproximada: " + edad + " años");
        System.out.println("Edad Aproximada en meses: " + edadMeses  + " meses");
        System.out.println("¿Es mayor de edad?: " + mayorEdad);

        teclado.close();


    }
}