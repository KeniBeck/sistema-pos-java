package com.example.ui;

import com.example.controller.VentaController;
import com.example.model.Venta;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;

public class VentanaVentas extends JFrame {
    private VentaController ventaController;
    private JTextField campoProductoId;
    private JTextField campoCantidad;
    private JButton botonRegistrar;
    private JButton botonVerVentas; // Nuevo botón

    public VentanaVentas() {
        ventaController = new VentaController();
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Sistema de Ventas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelProductoId = new JLabel("Producto ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelProductoId, gbc);

        campoProductoId = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(campoProductoId, gbc);

        JLabel labelCantidad = new JLabel("Cantidad:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelCantidad, gbc);

        campoCantidad = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoCantidad, gbc);

        botonRegistrar = new JButton("Registrar Venta");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(botonRegistrar, gbc);

        botonRegistrar.addActionListener(e -> registrarVenta());

        botonVerVentas = new JButton("Ver Ventas"); // Inicializar el nuevo botón
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(botonVerVentas, gbc);

        botonVerVentas.addActionListener(e -> verVentas()); // Añadir acción al botón
    }

    private void registrarVenta() {
        try {
            int productoId = Integer.parseInt(campoProductoId.getText());
            int cantidad = Integer.parseInt(campoCantidad.getText());

            // Registrar la venta y procesar la venta
            ventaController.procesarVenta(productoId, cantidad);

            JOptionPane.showMessageDialog(this, "Venta registrada con éxito");
            campoProductoId.setText("");
            campoCantidad.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void verVentas() {
        new VentanaTodasVentas().setVisible(true); // Abrir la ventana de todas las ventas
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaVentas().setVisible(true));
    }
}