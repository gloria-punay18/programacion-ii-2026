package com.papeleria.inventario.controller;

import com.papeleria.inventario.dto.MovimientoRequestDTO;
import com.papeleria.inventario.model.MovimientoInventario;
import com.papeleria.inventario.service.MovimientoInventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoInventario> registrarMovimiento(@Valid @RequestBody MovimientoRequestDTO dto) {
        MovimientoInventario movimiento = movimientoService.registrarMovimiento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }

    @GetMapping("/producto/{productoId}")
    public List<MovimientoInventario> obtenerPorProducto(@PathVariable Long productoId) {
        return movimientoService.obtenerMovimientosPorProducto(productoId);
    }
}