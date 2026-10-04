package com.estudiante.despensa.controller;

import com.estudiante.despensa.model.Producto;
import com.estudiante.despensa.model.ResumenInventario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {

        productos.add(new Producto(1L, "Leche Entera, LALA 1 ltro.", "Lácteos", 5, 2.50));
        productos.add(new Producto(2L, "Queso Fresco", "Lácteos", 2, 4.00));
        productos.add(new Producto(3L, "Arroz Gallo Dorado", "Granos", 10, 1.80));
        productos.add(new Producto(4L, "Frijoles Colorados", "Granos", 3, 2.20));
        productos.add(new Producto(5L, "Detergente, Blanca Nieve", "Limpieza", 1, 15.00));
        productos.add(new Producto(6L, "Jabón de Manos Olimpo, Aroma Lavanda", "Higiene", 8, 1.20));
    }

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return ResponseEntity.ok(producto);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/categoria/{categoria}")
    public List<Producto> obtenerPorCategoria(@PathVariable String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    @GetMapping("/stock-bajo")
    public List<Producto> obtenerStockBajo() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCantidad() <= 3) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    @GetMapping("/mayor-valor")
    public ResponseEntity<Producto> obtenerMayorValor() {
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Producto mayor = productos.get(0);
        for (Producto producto : productos) {
            if (producto.calcularSubtotal() > mayor.calcularSubtotal()) {
                mayor = producto;
            }
        }
        return ResponseEntity.ok(mayor);
    }

    @GetMapping("/resumen")
    public ResumenInventario obtenerResumen() {
        int cantidadProductos = productos.size();
        int totalUnidades = 0;
        double valorTotal = 0.0;

        for (Producto producto : productos) {
            totalUnidades += producto.getCantidad();
            valorTotal += producto.calcularSubtotal();
        }

        return new ResumenInventario(cantidadProductos, totalUnidades, valorTotal);
    }
}