import java.util.Scanner;

public class CajeroAutomatico {

    static double saldo = 10000.00;
    static String pinCorrecto = "010203";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Nombre: Gloria Esperanza Punay Xocoxic");
        System.out.println("Carné: 9941-25-22033");


        System.out.println("====================================");
        System.out.println("     BIENVENIDO AL CAJERO BI       ");
        System.out.println("====================================");

        System.out.print("Ingrese su PIN: ");
        String pinIngresado = teclado.next();

        if (!pinIngresado.equals(pinCorrecto)) {
            System.out.println("PIN incorrecto. Acceso denegado.");
            teclado.close();
            return;
        }

        int opcion = 0;

        while (opcion != 4) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;

                case 2:
                    System.out.print("Ingrese el monto a retirar: Q");
                    double montoRetiro = teclado.nextDouble();

                    System.out.print("¿Especificar cuenta? (1 = No, 2 = Sí): ");
                    int tipoRetiro = teclado.nextInt();

                    if (tipoRetiro == 1) {
                        retirar(montoRetiro);
                    } else {
                        System.out.print("Nombre de la cuenta (Ahorros / Monetaria): ");
                        String tipoCuenta = teclado.next();
                        retirar(montoRetiro, tipoCuenta);
                    }
                    break;

                case 3:
                    System.out.println("1. Depositar monto total");
                    System.out.println("2. Depositar por billetes de una denominación");
                    System.out.print("Opción: ");
                    int tipoDep = teclado.nextInt();

                    if (tipoDep == 1) {
                        System.out.print("Monto a depositar: Q");
                        double montoDep = teclado.nextDouble();
                        depositar(montoDep);
                    } else {
                        System.out.print("Cantidad de billetes: ");
                        int cant = teclado.nextInt();
                        System.out.print("Denominación (Q20, Q50, Q100, Q200): Q");
                        int denom = teclado.nextInt();
                        depositar(cant, denom);
                    }
                    break;

                case 4:
                    System.out.println("\n¡Gracias por usar el cajero automático!");
                    break;

                default:
                    System.out.println("\nOpción no válida.");
                    break;
            }
        }

        teclado.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("             MENÚ PRINCIPAL          ");
        System.out.println("------------------------------------");
        System.out.println("1. Consultar Saldo");
        System.out.println("2. Retirar Dinero");
        System.out.println("3. Depositar Dinero");
        System.out.println("4. Salir");
        System.out.println("------------------------------------");
    }

    public static void consultarSaldo() {
        System.out.printf("%nSu saldo actual es: Q%.2f%n", saldo);
    }


    public static boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            System.out.printf("[✓] Retiro de Q%.2f exitoso.%n", monto);
            imprimirComprobante("Retiro Directo", monto);
            return true;
        } else {
            System.out.println("[X] Fondo insuficiente o monto no válido.");
            return false;
        }
    }


    public static boolean retirar(double monto, String tipoCuenta) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            System.out.printf("[✓] Retiro de Q%.2f procesado desde la cuenta de %s.%n", monto, tipoCuenta);
            imprimirComprobante("Retiro " + tipoCuenta, monto);
            return true;
        } else {
            System.out.println("[X] Fondo insuficiente o monto no válido.");
            return false;
        }
    }


    public static boolean depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.printf("[✓] Depósito de Q%.2f acreditado.%n", monto);
            imprimirComprobante("Depósito Directo", monto);
            return true;
        } else {
            System.out.println("[X] El monto debe ser mayor a cero.");
            return false;
        }
    }


    public static double depositar(int cantidadBilletes, int denominacion) {
        if (cantidadBilletes > 0 && denominacion > 0) {
            double total = cantidadBilletes * denominacion;
            saldo += total;
            System.out.printf("[✓] Depósito de %d billetes de Q%d acreditado (Total: Q%.2f).%n",
                    cantidadBilletes, denominacion, total);
            imprimirComprobante("Depósito por Billetes", total);
            return total;
        } else {
            System.out.println("[X] Valores ingresados no válidos.");
            return 0.0;
        }
    }

    public static void imprimirComprobante(String operacion, double monto) {
        System.out.println("---- COMPROBANTE DE PAGO ----");
        System.out.println("Operación: " + operacion);
        System.out.printf("Monto:     Q%.2f%n", monto);
        System.out.printf("Nuevo Saldo: Q%.2f%n", saldo);
        System.out.println("-----------------------------");
    }
}