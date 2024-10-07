package com.example.controller;

import com.example.dao.ProductoDAO;
import com.example.dao.ProductoInventarioDAO;
import com.example.dao.VentaDAO;
import com.example.model.Producto;
import com.example.model.ProductoInventario;
import com.example.model.Venta;

import java.sql.SQLException;
import java.util.List;

public class VentaController {
    private VentaDAO ventaDAO;
    private ProductoInventarioDAO productoInventarioDAO;
    private ProductoDAO productoDAO;

    public VentaController() {
        this.ventaDAO = new VentaDAO();
        this.productoInventarioDAO = new ProductoInventarioDAO();
        this.productoDAO = new ProductoDAO();
    }

    public void registrarVenta(Venta venta) throws SQLException {
        ventaDAO.agregarVenta(venta);
    }

    public List<Venta> obtenerTodasLasVentas() throws SQLException {
        return ventaDAO.obtenerTodasLasVentas();
    }

    public double obtenerPrecioProducto(int productoId) throws SQLException {
        return productoDAO.obtenerPrecioProducto(productoId);
    }

    public void procesarVenta(int productoId, int cantidadVendida) throws SQLException {
        // Obtener el producto
        Producto producto = productoDAO.obtenerProductoPorId(productoId);

        // Obtener el stock actual del producto
        int stockActual = producto.getStock();

        // Validar el stock
        if (stockActual == 0) {
            throw new SQLException("El producto se ha agotado.");
        } else if (cantidadVendida > stockActual) {
            throw new SQLException("La cantidad solicitada excede el stock disponible.");
        }

        // Calcular el nuevo stock
        int nuevoStock = stockActual - cantidadVendida;

        // Actualizar stock en productos
        productoDAO.actualizarStockProducto(productoId, nuevoStock);

        // Obtener el inventario del producto
        ProductoInventario productoInventario = productoInventarioDAO.obtenerProductoInventarioPorProductoId(productoId);

        // Actualizar la cantidad en el inventario
        int nuevaCantidadInventario = productoInventario.getCantidad() - cantidadVendida;
        productoInventario.setCantidad(nuevaCantidadInventario);
        productoInventarioDAO.actualizarProductoInventario(productoInventario);

        // Registrar la venta
        Venta venta = new Venta(0, new java.util.Date(), producto.getPrecio() * cantidadVendida, productoId);
        registrarVenta(venta);
    }
}