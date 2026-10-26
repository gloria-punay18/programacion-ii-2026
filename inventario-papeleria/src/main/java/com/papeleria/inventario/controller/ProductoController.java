package com.papeleria.inventario.controller;

import com.papeleria.inventario.dto.ProductoRequestDTO;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Producto> registrar(@Valid @RequestBody ProductoRequestDTO dto) {
        Producto registrado = productoService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> obtenerStockBajo(@RequestParam(defaultValue = "5") int limite) {
        return ResponseEntity.ok(productoService.obtenerProductosStockBajo(limite));
    }

    @GetMapping("/valor-total")
    public ResponseEntity<BigDecimal> obtenerValorTotal() {
        return ResponseEntity.ok(productoService.calcularValorTotalInventario());
    }
}