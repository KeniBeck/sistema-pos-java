package com.example.model;

public class ProductoInventario {
    private int id;
    private int productoId;
    private int inventarioId;
    private int cantidad;

    public ProductoInventario(int id, int productoId, int inventarioId, int cantidad) {
        this.id = id;
        this.productoId = productoId;
        this.inventarioId = inventarioId;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    public int getInventarioId() { return inventarioId; }
    public void setInventarioId(int inventarioId) { this.inventarioId = inventarioId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}