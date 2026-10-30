package com.papeleria.inventario.controller;

import com.papeleria.inventario.dto.ProductoRequestDTO;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Producto> registrar(@Valid @RequestBody ProductoRequestDTO dto) {
        Producto nuevoProducto = productoService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        Producto productoActualizado = productoService.actualizar(id, dto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/stock-bajo")
    public List<Producto> obtenerStockBajo() {
        return productoService.obtenerProductosStockBajo();
    }


    @GetMapping("/categoria/{categoriaId}")
    public List<Producto> obtenerPorCategoria(@PathVariable Long categoriaId) {
        return productoService.obtenerPorCategoria(categoriaId);
    }

    @GetMapping("/valor-total")
    public ResponseEntity<BigDecimal> calcularValorTotal() {
        return ResponseEntity.ok(productoService.calcularValorTotalInventario());
    }
}