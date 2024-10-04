package com.example.model;

import java.util.Date;

public class Venta {
    private int id;
    private Date fecha;
    private double total;

    // Constructor, getters y setters
    public Venta(int id, Date fecha, double total) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}