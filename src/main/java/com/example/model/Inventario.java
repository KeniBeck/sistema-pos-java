package com.example.model;

public class Inventario {
    private int id;
    private String ubicacion;

    public Inventario(int id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public String toString() {
        return ubicacion;
    }
}