package com.example.controller;

import com.example.dao.ProductoDAO;
import com.example.model.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;

    public ProductoController() {
        this.productoDAO = new ProductoDAO();
    }

    public void agregarProducto(Producto producto) throws SQLException {
        productoDAO.agregarProducto(producto);
    }

    public void eliminarProducto(int id) throws SQLException {
        productoDAO.eliminarProducto(id);
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        return productoDAO.obtenerTodosLosProductos();
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        productoDAO.actualizarProducto(producto);
    }
}