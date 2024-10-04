package com.example.controller;

import com.example.dao.InventarioDAO;
import com.example.dao.ProductoInventarioDAO;
import com.example.model.Inventario;
import com.example.model.ProductoInventario;

import java.sql.SQLException;
import java.util.List;

public class InventarioController {
    private InventarioDAO inventarioDAO;
    private ProductoInventarioDAO productoInventarioDAO;

    public InventarioController() {
        this.inventarioDAO = new InventarioDAO();
        this.productoInventarioDAO = new ProductoInventarioDAO();
    }

    public void agregarInventario(Inventario inventario) throws SQLException {
        inventarioDAO.agregarInventario(inventario);
    }

    public List<Inventario> obtenerTodosLosInventarios() throws SQLException {
        return inventarioDAO.obtenerTodosLosInventarios();
    }

    public void actualizarInventario(Inventario inventario) throws SQLException {
        inventarioDAO.actualizarInventario(inventario);
    }

    public void eliminarInventario(int id) throws SQLException {
        inventarioDAO.eliminarInventario(id);
    }

    public void agregarProductoAInventario(ProductoInventario productoInventario) throws SQLException {
        productoInventarioDAO.agregarProductoInventario(productoInventario);
    }

    public List<ProductoInventario> obtenerProductosInventarioPorProducto(int productoId) throws SQLException {
        return productoInventarioDAO.obtenerProductosInventarioPorProducto(productoId);
    }

    public void actualizarCantidadProductoInventario(ProductoInventario productoInventario) throws SQLException {
        productoInventarioDAO.actualizarProductoInventario(productoInventario);
    }

    public void eliminarProductoDeInventario(int id) throws SQLException {
        productoInventarioDAO.eliminarProductoInventario(id);
    }
        public boolean existeInventario(int inventarioId) throws SQLException {
        return inventarioDAO.existeInventario(inventarioId);
    }
}