import java.util.Scanner;
public class PresupuestoSemanal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        String nombre;
        double presupuesto;
        double alimentacion;
        double transporte;
        double otros;

        System.out.println("=== CONTROL DE PRESUPUESTO SEMANAL ===");

        System.out.print("Ingrese su nombre: ");
        nombre = teclado.nextLine();

        System.out.print("Ingrese su presupuesto semanal: Q");
        presupuesto = teclado.nextDouble();

        System.out.print("Ingrese gasto de alimentacion: Q");
        alimentacion = teclado.nextDouble();

        System.out.print("Ingrese gasto de transporte: Q");
        transporte = teclado.nextDouble();

        System.out.print("Ingrese gasto de otros: Q");
        otros = teclado.nextDouble();

        double totalGastado = alimentacion + transporte + otros;

        System.out.println();
        System.out.println("=== RESUMEN SEMANAL ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Presupuesto: Q" + presupuesto);
        System.out.println("Total gastado: Q" + totalGastado);
    }
}