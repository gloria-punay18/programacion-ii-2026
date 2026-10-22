package com.inventario.papeleria.service;

import com.inventario.papeleria.dto.ProductoDTO;
import com.inventario.papeleria.dto.ResumenInventarioDTO;
import com.inventario.papeleria.dto.VentaDTO;
import com.inventario.papeleria.entity.Categoria;
import com.inventario.papeleria.entity.Producto;
import com.inventario.papeleria.exception.BadRequestException;
import com.inventario.papeleria.exception.ResourceNotFoundException;
import com.inventario.papeleria.repository.CategoriaRepository;
import com.inventario.papeleria.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return convertirADTO(producto);
    }

    public ProductoDTO crear(ProductoDTO dto) {
        if (productoRepository.existsByCodigo(dto.getCodigo())) {
            throw new BadRequestException("El código de producto '" + dto.getCodigo() + "' ya está registrado");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Producto producto = new Producto(
                null,
                dto.getCodigo(),
                dto.getNombre(),
                dto.getPrecio(),
                dto.getStock(),
                dto.getStockMinimo(),
                categoria
        );

        Producto guardado = productoRepository.save(producto);
        return convertirADTO(guardado);
    }

    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setCategoria(categoria);

        Producto actualizado = productoRepository.save(producto);
        return convertirADTO(actualizado);
    }

    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }


    public List<ProductoDTO> obtenerPorCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + categoriaId);
        }
        return productoRepository.findByCategoriaId(categoriaId).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    public List<ProductoDTO> obtenerProductosStockBajo() {
        return productoRepository.findProductosStockBajo().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    public ProductoDTO registrarVenta(VentaDTO ventaDTO) {
        Producto producto = productoRepository.findById(ventaDTO.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + ventaDTO.getProductoId()));

        if (producto.getStock() < ventaDTO.getCantidad()) {
            throw new BadRequestException("Stock insuficiente para realizar la venta. Stock actual: " + producto.getStock() + ", Solicitado: " + ventaDTO.getCantidad());
        }

        producto.setStock(producto.getStock() - ventaDTO.getCantidad());
        Producto guardado = productoRepository.save(producto);
        return convertirADTO(guardado);
    }


    public ResumenInventarioDTO obtenerResumenInventario() {
        List<Producto> productos = productoRepository.findAll();
        long totalProductos = productos.size();
        long totalCategorias = categoriaRepository.count();
        long stockBajo = productoRepository.findProductosStockBajo().size();

        BigDecimal valorTotal = productos.stream()
                .map(p -> p.getPrecio().multiply(BigDecimal.valueOf(p.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ResumenInventarioDTO(totalProductos, totalCategorias, stockBajo, valorTotal);
    }

    private ProductoDTO convertirADTO(Producto p) {
        return new ProductoDTO(
                p.getId(),
                p.getCodigo(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getStockMinimo(),
                p.getCategoria().getId(),
                p.getCategoria().getNombre()
        );
    }
}