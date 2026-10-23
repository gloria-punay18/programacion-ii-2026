package com.papeleria.inventario.controller;

import com.papeleria.inventario.model.MovimientoInventario;
import com.papeleria.inventario.model.TipoMovimiento;
import com.papeleria.inventario.service.MovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoService;

    @PostMapping
    public ResponseEntity<?> registrarMovimiento(
            @RequestParam Long productoId,
            @RequestParam TipoMovimiento tipo,
            @RequestParam Integer cantidad) {
        try {
            MovimientoInventario movimiento = movimientoService.registrarMovimiento(productoId, tipo, cantidad);
            return ResponseEntity.ok(movimiento);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/producto/{productoId}")
    public List<MovimientoInventario> obtenerPorProducto(@PathVariable Long productoId) {
        return movimientoService.obtenerMovimientosPorProducto(productoId);
    }
}