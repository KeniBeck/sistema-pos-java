package com.example.ui;

import com.example.controller.VentaController;
import com.example.model.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class VentanaTodasVentas extends JFrame {
    private VentaController ventaController;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;

    public VentanaTodasVentas() {
        ventaController = new VentaController();
        inicializarComponentes();
        cargarVentas();
    }

    private void inicializarComponentes() {
        setTitle("Todas las Ventas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Total");
        modeloTabla.addColumn("Producto ID");

        tablaVentas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaVentas);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarVentas() {
        try {
            List<Venta> ventas = ventaController.obtenerTodasLasVentas();
            for (Venta venta : ventas) {
                modeloTabla.addRow(new Object[]{
                    venta.getId(),
                    venta.getFecha(),
                    venta.getTotal(),
                    venta.getProductoId()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las ventas: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaTodasVentas().setVisible(true));
    }
}