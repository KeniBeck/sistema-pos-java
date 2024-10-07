package com.example.dao;

import com.example.model.Venta;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    public void agregarVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO ventas (fecha, total, id_productos) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDate(1, new java.sql.Date(venta.getFecha().getTime()));
            pstmt.setDouble(2, venta.getTotal());
            pstmt.setInt(3, venta.getProductoId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    venta.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
    public List<Venta> obtenerTodasLasVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ventas.add(new Venta(
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getDouble("total"),
                    rs.getInt("id_productos")
                ));
            }
        }
        return ventas;
    }

    // Otros métodos como actualizar, eliminar, etc.
}