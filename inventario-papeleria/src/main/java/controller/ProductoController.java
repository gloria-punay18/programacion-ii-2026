package com.inventario.papeleria.controller;

import com.inventario.papeleria.dto.ProductoDTO;
import com.inventario.papeleria.dto.ResumenInventarioDTO;
import com.inventario.papeleria.dto.VentaDTO;
import com.inventario.papeleria.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO dto) {
        return new ResponseEntity<>(productoService.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoDTO>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoriaId));
    }


    @GetMapping("/stock-bajo")
    public ResponseEntity<List<ProductoDTO>> listarStockBajo() {
        return ResponseEntity.ok(productoService.obtenerProductosStockBajo());
    }


    @PostMapping("/venta")
    public ResponseEntity<ProductoDTO> registrarVenta(@Valid @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok(productoService.registrarVenta(ventaDTO));
    }


    @GetMapping("/resumen")
    public ResponseEntity<ResumenInventarioDTO> obtenerResumen() {
        return ResponseEntity.ok(productoService.obtenerResumenInventario());
    }
}