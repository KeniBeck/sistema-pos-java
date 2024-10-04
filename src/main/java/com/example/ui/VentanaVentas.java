package com.example.ui;

import com.example.controller.VentaController;
import com.example.model.Venta;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;

public class VentanaVentas extends JFrame {
    private VentaController ventaController;
    private JTextField campoTotal;
    private JButton botonRegistrar;

    public VentanaVentas() {
        ventaController = new VentaController();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Sistema de Ventas");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        campoTotal = new JTextField(10);
        botonRegistrar = new JButton("Registrar Venta");

        add(new JLabel("Total:"));
        add(campoTotal);
        add(botonRegistrar);

        botonRegistrar.addActionListener(e -> registrarVenta());
    }

    private void registrarVenta() {
        try {
            double total = Double.parseDouble(campoTotal.getText());
            Venta venta = new Venta(0, new Date(), total);
            ventaController.registrarVenta(venta);
            JOptionPane.showMessageDialog(this, "Venta registrada con éxito");
            campoTotal.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un total válido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la venta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaVentas().setVisible(true));
    }
}