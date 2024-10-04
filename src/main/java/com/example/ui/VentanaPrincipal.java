package com.example.ui;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JButton botonVentas, botonProductos, botonInventario;

    public VentanaPrincipal() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Sistema de Ventas");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para el título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        JLabel titulo = new JLabel("Sistema de Ventas");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(titulo);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        botonVentas = new JButton("Ventas");
        botonProductos = new JButton("Gestión de Productos");
        botonInventario = new JButton("Inventario");

        panelBotones.add(botonVentas);
        panelBotones.add(botonProductos);
        panelBotones.add(botonInventario);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        botonVentas.addActionListener(e -> new VentanaVentas().setVisible(true));
        botonProductos.addActionListener(e -> new VentanaProductos().setVisible(true));
        botonInventario.addActionListener(e -> new VentanaInventario().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}