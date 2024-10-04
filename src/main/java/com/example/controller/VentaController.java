package com.example.controller;

import com.example.dao.VentaDAO;
import com.example.model.Venta;

import java.sql.SQLException;
import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
    }

    public void registrarVenta(Venta venta) throws SQLException {
        ventaDAO.agregarVenta(venta);
    }

    public List<Venta> obtenerTodasLasVentas() throws SQLException {
        return ventaDAO.obtenerTodasLasVentas();
    }

    // Otros métodos de negocio relacionados con ventas
}