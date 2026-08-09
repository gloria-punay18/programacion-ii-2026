import java.util.Scanner;

public class CajeroAutomatico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String titular = "Gloria Esperanza Punay Xocoxic";
        String numeroCuenta = "2033";
        final int PIN_CORRECTO = 2026;
        final double SALDO_INICIAL = 1000.00;
        final double COMISION = 10.00;

        double saldoActual = SALDO_INICIAL;
        int depositosExitosos = 0;
        double totalDepositado = 0.0;
        int retirosExitosos = 0;
        double totalRetirado = 0.0;
        double totalComisiones = 0.0;
        int operacionesRechazadas = 0;
        int opcionesInvalidas = 0;

        boolean accesoConcedido = validarAcceso(scanner, PIN_CORRECTO);

        if (!accesoConcedido) {
            System.out.println("\nCuenta bloqueada. El programa finalizara.");
            scanner.close();
            return;
        }

        int opcion = 0;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                scanner.next();
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    consultarSaldo(titular, numeroCuenta, saldoActual);
                    break;

                case 2:
                    double montoDeposito = solicitarMontoDeposito(scanner);
                    saldoActual += montoDeposito;
                    depositosExitosos++;
                    totalDepositado += montoDeposito;
                    break;

                case 3:
                    System.out.print("Ingrese el monto a retirar (multiplo de Q20.00, maximo Q2,000.00): ");
                    double montoRetiroNormal = scanner.nextDouble();

                    if (procesarRetiro(montoRetiroNormal, saldoActual)) {
                        double saldoAnterior = saldoActual;
                        saldoActual -= montoRetiroNormal;
                        retirosExitosos++;
                        totalRetirado += montoRetiroNormal;

                        mostrarComprobanteRetiro(montoRetiroNormal, 0.0, montoRetiroNormal, saldoAnterior, saldoActual);
                    } else {
                        operacionesRechazadas++;
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el monto a retirar (comision de Q10.00 aplicable): ");
                    double montoRetiroComision = scanner.nextDouble();

                    if (procesarRetiro(montoRetiroComision, saldoActual, COMISION)) {
                        double saldoAnterior = saldoActual;
                        double totalDebitado = montoRetiroComision + COMISION;
                        saldoActual -= totalDebitado;
                        retirosExitosos++;
                        totalRetirado += montoRetiroComision;
                        totalComisiones += COMISION;

                        mostrarComprobanteRetiro(montoRetiroComision, COMISION, totalDebitado, saldoAnterior, saldoActual);
                    } else {
                        operacionesRechazadas++;
                    }
                    break;

                case 5:
                    mostrarResumen(SALDO_INICIAL, depositosExitosos, totalDepositado,
                            retirosExitosos, totalRetirado, totalComisiones,
                            operacionesRechazadas, opcionesInvalidas, saldoActual);
                    break;

                case 6:
                    System.out.println("\n--- RESUMEN FINAL DE LA SESION ---");
                    mostrarResumen(SALDO_INICIAL, depositosExitosos, totalDepositado,
                            retirosExitosos, totalRetirado, totalComisiones,
                            operacionesRechazadas, opcionesInvalidas, saldoActual);
                    System.out.println("\nGracias por utilizar nuestros servicios. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Error: Opcion invalida. Intente de nuevo.");
                    opcionesInvalidas++;
                    continue;
            }

        } while (opcion != 6);

        scanner.close();
    }

    public static boolean validarAcceso(Scanner sc, int pinCorrecto) {
        int maxIntentos = 3;
        for (int intento = 1; intento <= maxIntentos; intento++) {
            System.out.print("Ingrese su PIN de 4 digitos: ");
            int pinIngresado = sc.nextInt();

            if (pinIngresado == pinCorrecto) {
                System.out.println("\n¡Bienvenido al sistema de Cajero Automatico!");
                return true;
            } else {
                int restantes = maxIntentos - intento;
                System.out.println("PIN incorrecto.");
                if (restantes > 0) {
                    System.out.println("Intentos restantes: " + restantes);
                }
            }
        }
        return false;
    }

    public static void mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("        CAJERO AUTOMATICO         ");
        System.out.println("==================================");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar dinero");
        System.out.println("3. Realizar retiro normal");
        System.out.println("4. Realizar retiro con comision");
        System.out.println("5. Mostrar resumen de la sesion");
        System.out.println("6. Salir");
        System.out.println("==================================");
    }

    public static void consultarSaldo(String titular, String cuenta, double saldo) {
        System.out.println("\n--- CONSULTA DE SALDO ---");
        System.out.println("Titular: " + titular);
        System.out.println("Numero de Cuenta: ****" + cuenta);
        System.out.printf("Saldo disponible: Q%.2f\n", saldo);
    }

    public static double solicitarMontoDeposito(Scanner sc) {
        double monto = 0.0;
        System.out.print("Ingrese el monto a depositar: ");
        monto = sc.nextDouble();

        while (monto <= 0.0 || monto > 5000.0) {
            if (monto <= 0.0) {
                System.out.println("Error: El monto a depositar debe ser mayor a Q0.00.");
            } else if (monto > 5000.0) {
                System.out.println("Error: El monto maximo por deposito es de Q5,000.00.");
            }
            System.out.print("Ingrese un monto valido a depositar: ");
            monto = sc.nextDouble();
        }
        return monto;
    }

    public static boolean esValidoMontoRetiro(double monto, double saldoDisponible, double comision) {
        double totalRequerido = monto + comision;

        if (monto <= 0.0) {
            System.out.println("Error de Retiro: El monto debe ser mayor a Q0.00.");
            return false;
        }
        if (monto % 20.0 != 0) {
            System.out.println("Error de Retiro: El monto debe ser multiplo de Q20.00.");
            return false;
        }
        if (monto > 2000.0) {
            System.out.println("Error de Retiro: El monto maximo por operacion es de Q2,000.00.");
            return false;
        }
        if (totalRequerido > saldoDisponible && saldoDisponible >= monto) {
            System.out.println("Error de Retiro: Su saldo cubre el retiro pero no la comision de Q10.00.");
            return false;
        } else if (totalRequerido > saldoDisponible) {
            System.out.println("Error de Retiro: Fondos insuficientes para realizar la transaccion.");
            return false;
        }

        return true;
    }

    public static boolean procesarRetiro(double monto, double saldoDisponible) {
        return esValidoMontoRetiro(monto, saldoDisponible, 0.0);
    }

    public static boolean procesarRetiro(double monto, double saldoDisponible, double comision) {
        return esValidoMontoRetiro(monto, saldoDisponible, comision);
    }

    public static void mostrarComprobanteRetiro(double monto, double comision, double totalDebitado, double saldoAnterior, double saldoNuevo) {
        System.out.println("\n--- RETIRO PROCESADO EXITOSAMENTE ---");
        System.out.printf("Monto solicitado: Q%.2f\n", monto);
        if (comision > 0.0) {
            System.out.printf("Comision cobrada: Q%.2f\n", comision);
        }
        System.out.printf("Total debitado:   Q%.2f\n", totalDebitado);
        System.out.printf("Saldo anterior:   Q%.2f\n", saldoAnterior);
        System.out.printf("Saldo actualizado: Q%.2f\n", saldoNuevo);
    }

    public static void mostrarResumen(double saldoInicial, int depExitosos, double totDepositado,
                                      int retExitosos, double totRetirado, double totComisiones,
                                      int opRechazadas, int opInvalidas, double saldoActual) {
        System.out.println("\n==================================");
        System.out.println("       RESUMEN DE LA SESION       ");
        System.out.println("==================================");
        System.out.printf("Saldo inicial:                  Q%.2f\n", saldoInicial);
        System.out.println("Cantidad de depositos exitosos: " + depExitosos);
        System.out.printf("Total dinero depositado:        Q%.2f\n", totDepositado);
        System.out.println("Cantidad de retiros exitosos:   " + retExitosos);
        System.out.printf("Total entregado en retiros:     Q%.2f\n", totRetirado);
        System.out.printf("Total comisiones cobradas:      Q%.2f\n", totComisiones);
        System.out.println("Operaciones rechazadas:         " + opRechazadas);
        System.out.println("Opciones de menu invalidas:     " + opInvalidas);
        System.out.printf("Saldo actual disponible:        Q%.2f\n", saldoActual);
        System.out.println("==================================");
    }
}