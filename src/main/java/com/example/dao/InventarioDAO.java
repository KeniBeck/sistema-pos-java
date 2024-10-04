package com.example.dao;

import com.example.model.Inventario;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
      public boolean existeInventario(int inventarioId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inventarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    public void agregarInventario(Inventario inventario) throws SQLException {
        String sql = "INSERT INTO inventarios (ubicacion) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, inventario.getUbicacion());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inventario.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Inventario> obtenerTodosLosInventarios() throws SQLException {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventarios";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                inventarios.add(new Inventario(
                    rs.getInt("id"),
                    rs.getString("ubicacion")
                ));
            }
        }
        return inventarios;
    }

    public void actualizarInventario(Inventario inventario) throws SQLException {
        String sql = "UPDATE inventarios SET ubicacion = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inventario.getUbicacion());
            pstmt.setInt(2, inventario.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminarInventario(int id) throws SQLException {
        String sql = "DELETE FROM inventarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}