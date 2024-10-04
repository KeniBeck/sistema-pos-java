package com.example.dao;

import com.example.model.ProductoInventario;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoInventarioDAO {
    public void agregarProductoInventario(ProductoInventario productoInventario) throws SQLException {
        String sql = "INSERT INTO productos_inventarios (producto_id, inventario_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, productoInventario.getProductoId());
            pstmt.setInt(2, productoInventario.getInventarioId());
            pstmt.setInt(3, productoInventario.getCantidad());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    productoInventario.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<ProductoInventario> obtenerProductosInventarioPorProducto(int productoId) throws SQLException {
        List<ProductoInventario> productosInventario = new ArrayList<>();
        String sql = "SELECT * FROM productos_inventarios WHERE producto_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productosInventario.add(new ProductoInventario(
                        rs.getInt("id"),
                        rs.getInt("producto_id"),
                        rs.getInt("inventario_id"),
                        rs.getInt("cantidad")
                    ));
                }
            }
        }
        return productosInventario;
    }

    public void actualizarProductoInventario(ProductoInventario productoInventario) throws SQLException {
        String sql = "UPDATE productos_inventarios SET cantidad = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productoInventario.getCantidad());
            pstmt.setInt(2, productoInventario.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminarProductoInventario(int id) throws SQLException {
        String sql = "DELETE FROM productos_inventarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}