import java.util.Scanner;

public class CondicionalesyCiclos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Estudiante: Gloria Esperanza Punay Xocoxic");
        System.out.println("Carné: 9941-25-22033");
        System.out.println("Semana 3 — Condiciones y ciclos");
        System.out.println("-------------------------------------------");

        int opcion = 0;


        do {
            System.out.println("\n========= DESAFÍOS LÓGICOS =========");
            System.out.println("1. Generar una secuencia");
            System.out.println("2. Realizar un conteo regresivo");
            System.out.println("3. Analizar números");
            System.out.println("4. Dibujar una pirámide");
            System.out.println("5. Validar palabra secreta");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();


            switch (opcion) {


                case 1:
                    System.out.println("\n--- Opción 1: Generar una secuencia ---");
                    System.out.print("Número inicial: ");
                    int inicio = scanner.nextInt();

                    System.out.print("Número final: ");
                    int fin = scanner.nextInt();

                    System.out.print("Incremento: ");
                    int incremento = scanner.nextInt();

                    if (incremento <= 0) {
                        System.out.println("Error: El incremento debe ser mayor que cero.");
                    } else if (fin <= inicio) {
                        System.out.println("Error: El número final debe ser mayor que el inicial.");
                    } else {
                        System.out.print("Resultado: ");
                        for (int i = inicio; i <= fin; i += incremento) {
                            System.out.print(i + " ");
                        }
                        System.out.println();
                    }
                    break;


                case 2:
                    System.out.println("\n--- Opción 2: Conteo regresivo ---");
                    int numRegresivo = 0;

                    while (true) {
                        System.out.print("Ingrese el número inicial (entre 10 y 50): ");
                        numRegresivo = scanner.nextInt();

                        if (numRegresivo >= 10 && numRegresivo <= 50) {
                            break;
                        }
                        System.out.println("Número fuera de rango. Intente nuevamente.");
                    }

                    System.out.print("Resultado: ");
                    while (numRegresivo >= 0) {
                        System.out.print(numRegresivo + " ");
                        numRegresivo--;
                    }
                    System.out.println("\n¡Despegue!");
                    break;


                case 3:
                    System.out.println("\n--- Opción 3: Analizar números ---");
                    int positivos = 0;
                    int negativos = 0;
                    int sumaValida = 0;
                    int ignorados = 0;

                    while (true) {
                        System.out.print("Ingrese un número: ");
                        int num = scanner.nextInt();

                        if (num == 0) {
                            break;
                        }

                        if (num % 5 == 0) {
                            System.out.println("El número " + num + " fue ignorado.");
                            ignorados++;
                            continue;
                        }

                        if (num > 0) {
                            positivos++;
                        } else {
                            negativos++;
                        }
                        sumaValida += num;
                    }

                    System.out.println("\nResultado:");
                    System.out.println("Positivos: " + positivos);
                    System.out.println("Negativos: " + negativos);
                    System.out.println("Suma válida: " + sumaValida);
                    System.out.println("Números ignorados: " + ignorados);
                    break;


                case 4:
                    System.out.println("\n--- Opción 4: Dibujar una pirámide ---");
                    System.out.print("Ingrese la altura (entre 3 y 10): ");
                    int altura = scanner.nextInt();

                    if (altura < 3 || altura > 10) {
                        System.out.println("Error: La altura debe estar entre 3 y 10.");
                    } else {
                        System.out.println("Resultado:");
                        for (int i = 1; i <= altura; i++) {
                            for (int j = 1; j <= altura - i; j++) {
                                System.out.print(" ");
                            }
                            for (int k = 1; k <= (2 * i - 1); k++) {
                                System.out.print("*");
                            }
                            System.out.println();
                        }
                    }
                    break;


                case 5:
                    System.out.println("\n--- Opción 5: Validar palabra secreta ---");
                    scanner.nextLine(); // Limpieza del buffer
                    String palabra = "";

                    do {
                        System.out.print("Ingrese la palabra secreta: ");
                        palabra = scanner.nextLine();

                        if (palabra.trim().equalsIgnoreCase("Guatemala")) {
                            System.out.println("Palabra correcta.");
                            break;
                        } else {
                            System.out.println("Palabra incorrecta. Intente nuevamente.\n");
                        }
                    } while (true);
                    break;

                // OPCIÓN 6: SALIR
                case 6:
                    System.out.println("Programa finalizado correctamente.");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 6);

        scanner.close();
    }
}

