package com.inventario.papeleria.dto;

import java.math.BigDecimal;

public class ResumenInventarioDTO {

    private long totalProductos;
    private long totalCategorias;
    private long productosStockBajo;
    private BigDecimal valorTotalInventario;

    public ResumenInventarioDTO() {}

    public ResumenInventarioDTO(long totalProductos, long totalCategorias, long productosStockBajo, BigDecimal valorTotalInventario) {
        this.totalProductos = totalProductos;
        this.totalCategorias = totalCategorias;
        this.productosStockBajo = productosStockBajo;
        this.valorTotalInventario = valorTotalInventario;
    }

    public long getTotalProductos() { return totalProductos; }
    public void setTotalProductos(long totalProductos) { this.totalProductos = totalProductos; }

    public long getTotalCategorias() { return totalCategorias; }
    public void setTotalCategorias(long totalCategorias) { this.totalCategorias = totalCategorias; }

    public long getProductosStockBajo() { return productosStockBajo; }
    public void setProductosStockBajo(long productosStockBajo) { this.productosStockBajo = productosStockBajo; }

    public BigDecimal getValorTotalInventario() { return valorTotalInventario; }
    public void setValorTotalInventario(BigDecimal valorTotalInventario) { this.valorTotalInventario = valorTotalInventario; }
}