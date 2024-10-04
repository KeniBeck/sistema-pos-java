package com.example.dao;

import com.example.model.Venta;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    public void agregarVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO ventas (fecha, total) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(venta.getFecha().getTime()));
            pstmt.setDouble(2, venta.getTotal());
            pstmt.executeUpdate();
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
                    rs.getDouble("total")
                ));
            }
        }
        return ventas;
    }

    // Otros métodos como actualizar, eliminar, etc.
}