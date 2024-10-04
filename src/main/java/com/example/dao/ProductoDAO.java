package com.example.dao;

import com.example.model.Producto;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
       public boolean existeProducto(int productoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    public void agregarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getStock());
            pstmt.executeUpdate();
        }
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        }
        return productos;
    }

    public void eliminarProducto(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getStock());
            pstmt.setInt(4, producto.getId());
            pstmt.executeUpdate();
        }
    }
}