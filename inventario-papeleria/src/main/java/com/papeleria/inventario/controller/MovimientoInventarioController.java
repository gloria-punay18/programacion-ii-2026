package com.papeleria.inventario.controller;

import com.papeleria.inventario.dto.MovimientoRequestDTO;
import com.papeleria.inventario.model.MovimientoInventario;
import com.papeleria.inventario.service.MovimientoInventarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    public MovimientoInventarioController(MovimientoInventarioService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoInventario> registrarMovimiento(@Valid @RequestBody MovimientoRequestDTO dto) {
        MovimientoInventario movimiento = movimientoService.registrarMovimiento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<MovimientoInventario>> obtenerPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(movimientoService.obtenerPorProducto(productoId));
    }
}