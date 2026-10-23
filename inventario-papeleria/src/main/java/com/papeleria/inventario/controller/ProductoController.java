package com.papeleria.inventario.controller;

import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @GetMapping("/stock-bajo")
    public List<Producto> obtenerStockBajo(@RequestParam(defaultValue = "5") int limite) {
        return productoService.obtenerProductosStockBajo(limite);
    }

    @GetMapping("/valor-total")
    public ResponseEntity<BigDecimal> obtenerValorTotal() {
        return ResponseEntity.ok(productoService.calcularValorTotalInventario());
    }
}