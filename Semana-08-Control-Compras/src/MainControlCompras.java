import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class MainControlCompras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        ArrayList<Producto> listaProductos = new ArrayList<>();
        HashSet<String> conjuntoCategorias = new HashSet<>();
        HashMap<String, Double> totalesPorCategoria = new HashMap<>();

        System.out.println("=== REGISTRO DE COMPRAS DEL HOGAR ===");
        System.out.println("Ingrese los datos de los productos (Mínimo 5 requeridos).\n");

        int contador = 1;
        while (listaProductos.size() < 5) {
            System.out.println("--- Producto #" + contador + " ---");

            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine().trim();

            System.out.print("Precio unitario: ");
            double precioUnitario = Double.parseDouble(scanner.nextLine());

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());


            if (nombre.isEmpty()) {
                System.out.println("Producto no registrado: El nombre no puede estar vacío.\n");
                continue;
            }

            if (categoria.isEmpty()) {
                System.out.println("Producto no registrado: La categoría no puede estar vacía.\n");
                continue;
            }

            if (precioUnitario <= 0) {
                System.out.println("Producto no registrado: El precio debe ser mayor que cero.\n");
                continue;
            }

            if (cantidad <= 0) {
                System.out.println("Producto no registrado: La cantidad debe ser mayor que cero.\n");
                continue;
            }


            Producto producto = new Producto(nombre, categoria, precioUnitario, cantidad);


            listaProductos.add(producto);


            conjuntoCategorias.add(categoria);


            double subtotalActual = producto.calcularSubtotal();
            if (totalesPorCategoria.containsKey(categoria)) {
                double acumulado = totalesPorCategoria.get(categoria);
                totalesPorCategoria.put(categoria, acumulado + subtotalActual);
            } else {
                totalesPorCategoria.put(categoria, subtotalActual);
            }

            System.out.println("-> Producto registrado con éxito.\n");
            contador++;
        }


        System.out.println("\n===== RESUMEN DE COMPRAS =====");

        double totalGeneral = 0.0;
        Producto prodMayorGasto = listaProductos.get(0);
        Producto prodMenorGasto = listaProductos.get(0);


        for (Producto p : listaProductos) {
            double subtotal = p.calcularSubtotal();
            totalGeneral += subtotal;

            System.out.printf("%s | %s | Q%.2f x %d | Subtotal: Q%.2f%n",
                    p.getNombre(), p.getCategoria(), p.getPrecioUnitario(), p.getCantidad(), subtotal);


            if (subtotal > prodMayorGasto.calcularSubtotal()) {
                prodMayorGasto = p;
            }
            if (subtotal < prodMenorGasto.calcularSubtotal()) {
                prodMenorGasto = p;
            }
        }


        System.out.println("\nCategorías registradas:");
        System.out.println(conjuntoCategorias);


        System.out.println("\nTotal por categoría:");
        String catMayorGasto = "";
        double maxGastoCategoria = -1.0;

        for (String cat : totalesPorCategoria.keySet()) {
            double gastoCat = totalesPorCategoria.get(cat);
            System.out.printf("%s: Q%.2f%n", cat, gastoCat);

            if (gastoCat > maxGastoCategoria) {
                maxGastoCategoria = gastoCat;
                catMayorGasto = cat;
            }
        }


        System.out.println("\nProductos registrados: " + listaProductos.size());
        System.out.printf("Total general: Q%.2f%n", totalGeneral);

        System.out.println("\nProducto con mayor gasto:");
        System.out.printf("%s - Q%.2f%n", prodMayorGasto.getNombre(), prodMayorGasto.calcularSubtotal());

        System.out.println("\nProducto con menor gasto:");
        System.out.printf("%s - Q%.2f%n", prodMenorGasto.getNombre(), prodMenorGasto.calcularSubtotal());

        System.out.println("\nCategoría con mayor gasto:");
        System.out.printf("%s - Q%.2f%n", catMayorGasto, maxGastoCategoria);


        System.out.println("\n==================================");
        System.out.print("Ingrese una categoría para consultar: ");
        String catConsulta = scanner.nextLine().trim();

        if (totalesPorCategoria.containsKey(catConsulta)) {
            System.out.printf("Total gastado en %s: Q%.2f%n", catConsulta, totalesPorCategoria.get(catConsulta));
        } else {
            System.out.println("La categoría ingresada no se encuentra registrada.");
        }

        scanner.close();
    }
}