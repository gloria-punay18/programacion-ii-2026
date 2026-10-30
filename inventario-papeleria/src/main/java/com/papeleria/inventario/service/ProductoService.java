package com.papeleria.inventario.service;

import com.papeleria.inventario.dto.ProductoRequestDTO;
import com.papeleria.inventario.exception.RecursoNoEncontradoException;
import com.papeleria.inventario.model.Categoria;
import com.papeleria.inventario.model.Producto;
import com.papeleria.inventario.repository.CategoriaRepository;
import com.papeleria.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

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

    public Producto registrar(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        convertirDtoAEntidad(dto, producto);
        return repository.save(producto);
    }

    public Producto actualizar(Long id, ProductoRequestDTO dto) {
        Producto productoExistente = buscarPorId(id);
        convertirDtoAEntidad(dto, productoExistente);
        return repository.save(productoExistente);
    }

    public void eliminar(Long id) {
        Producto producto = buscarPorId(id);
        repository.delete(producto);
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
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setStockMinimo(dto.getStockMinimo());


        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("La categoría especificada no existe con ID: " + dto.getCategoriaId()));
        producto.setCategoria(categoria);
    }
}