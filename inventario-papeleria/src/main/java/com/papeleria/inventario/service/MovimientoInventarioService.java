package com.papeleria.inventario.service;

import com.papeleria.inventario.model.MovimientoInventario;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.model.TipoMovimiento;
import com.papeleria.inventario.repository.MovimientoInventarioRepository;
import com.papeleria.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public MovimientoInventario registrarMovimiento(Long productoId, TipoMovimiento tipo, Integer cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("El producto no existe."));


        if (tipo == TipoMovimiento.SALIDA && producto.getStock() < cantidad) {
            throw new IllegalStateException("Stock insuficiente. No se puede realizar la salida.");
        }


        if (tipo == TipoMovimiento.ENTRADA) {
            producto.setStock(producto.getStock() + cantidad);
        } else if (tipo == TipoMovimiento.SALIDA) {
            producto.setStock(producto.getStock() - cantidad);
        }

        productoRepository.save(producto);

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProducto(producto);
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);
        movimiento.setFecha(LocalDateTime.now());

        return movimientoRepository.save(movimiento);
    }

    public List<MovimientoInventario> obtenerMovimientosPorProducto(Long productoId) {
        return movimientoRepository.findByProductoId(productoId);
    }
}