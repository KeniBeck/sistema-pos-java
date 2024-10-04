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
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        botonVentas = new JButton("Ventas");
        botonProductos = new JButton("Gestión de Productos");
        botonInventario = new JButton("Inventario");

        add(botonVentas);
        add(botonProductos);
        add(botonInventario);

        botonVentas.addActionListener(e -> new VentanaVentas().setVisible(true));
        botonProductos.addActionListener(e -> new VentanaProductos().setVisible(true));
        botonInventario.addActionListener(e -> new VentanaInventario().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}