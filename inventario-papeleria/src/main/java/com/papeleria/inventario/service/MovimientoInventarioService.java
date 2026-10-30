package com.papeleria.inventario.service;

import com.papeleria.inventario.dto.MovimientoRequestDTO;
import com.papeleria.inventario.exception.RecursoNoEncontradoException;
import com.papeleria.inventario.model.MovimientoInventario;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.model.TipoMovimiento;
import com.papeleria.inventario.repository.MovimientoInventarioRepository;
import com.papeleria.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public MovimientoInventarioService(MovimientoInventarioRepository movimientoRepository,
                                       ProductoRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public MovimientoInventario registrarMovimiento(MovimientoRequestDTO dto) {
        if (dto.getCantidad() == null || dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con el ID: " + dto.getProductoId()));

        if (dto.getTipo() == TipoMovimiento.SALIDA) {
            if (producto.getStock() < dto.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para realizar la salida. Stock actual: " + producto.getStock());
            }
            producto.setStock(producto.getStock() - dto.getCantidad());
        } else if (dto.getTipo() == TipoMovimiento.ENTRADA) {
            producto.setStock(producto.getStock() + dto.getCantidad());
        }

        productoRepository.save(producto);

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setProducto(producto);
        movimiento.setCantidad(dto.getCantidad());
        movimiento.setTipo(dto.getTipo());

        return movimientoRepository.save(movimiento);
    }


    public List<MovimientoInventario> obtenerMovimientosPorProducto(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new RecursoNoEncontradoException("El producto con ID " + productoId + " no existe.");
        }
        return movimientoRepository.findByProductoId(productoId);
    }
}