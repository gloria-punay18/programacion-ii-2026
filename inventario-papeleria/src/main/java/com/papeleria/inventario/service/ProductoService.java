package com.papeleria.inventario.service;

import com.papeleria.inventario.dto.ProductoRequestDTO;
import com.papeleria.inventario.exception.RecursoNoEncontradoException;
import com.papeleria.inventario.model.Categoria;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.repository.CategoriaRepository;
import com.papeleria.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Registro no encontrado con ID: " + id));
    }

    @Transactional
    public Producto registrar(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        convertirDtoAEntidad(dto, producto);

        producto.setStock(dto.getStock() != null ? dto.getStock() : 0);
        return repository.save(producto);
    }

    @Transactional
    public Producto actualizar(Long id, ProductoRequestDTO dto) {
        Producto productoExistente = buscarPorId(id);


        Integer stockActual = productoExistente.getStock();


        convertirDtoAEntidad(dto, productoExistente);

        productoExistente.setStock(stockActual);

        return repository.save(productoExistente);
    }

    @Transactional
    public void eliminar(Long id) {
        Producto producto = buscarPorId(id);

        try {
            repository.delete(producto);
            repository.flush();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar el producto '" + producto.getNombre() +
                    "' porque cuenta con movimientos de inventario registrados en el historial.");
        }
    }

    public List<Producto> obtenerProductosStockBajo() {
        return repository.findAll().stream()
                .filter(p -> p.getStock() <= p.getStockMinimo())
                .toList();
    }

    public List<Producto> obtenerPorCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new RecursoNoEncontradoException("La categoría con ID " + categoriaId + " no existe.");
        }
        return repository.findByCategoriaId(categoriaId);
    }

    public BigDecimal calcularValorTotalInventario() {
        return repository.findAll().stream()
                .map(p -> p.getPrecio().multiply(BigDecimal.valueOf(p.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void convertirDtoAEntidad(ProductoRequestDTO dto, Producto producto) {
        if (dto.getCodigo() == null || dto.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código del producto es obligatorio.");
        }
        if (dto.getPrecio() == null || dto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a cero.");
        }

        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStockMinimo(dto.getStockMinimo() != null ? dto.getStockMinimo() : 0);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("La categoría especificada no existe con ID: " + dto.getCategoriaId()));
        producto.setCategoria(categoria);
    }
}