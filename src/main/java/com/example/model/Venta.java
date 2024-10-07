package com.example.model;

import java.util.Date;

public class Venta {
    private int id;
    private Date fecha;
    private double total;
    private int productoId;

    // Constructor, getters y setters
    public Venta(int id, Date fecha, double total, int productoId) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.productoId = productoId;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
}