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
        panelTitulo.setBackground(new Color(70, 130, 180)); // Color de fondo azul
        JLabel titulo = new JLabel("Sistema de Ventas");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE); // Color del texto blanco
        panelTitulo.add(titulo);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Márgenes
        panelBotones.setBackground(new Color(245, 245, 245)); // Color de fondo gris claro

        botonVentas = crearBoton("Ventas", "resources/ventas.png");
        botonProductos = crearBoton("Gestión de Productos", "resources/productos.png");
        botonInventario = crearBoton("Inventario", "resources/inventario.png");

        panelBotones.add(botonVentas);
        panelBotones.add(botonProductos);
        panelBotones.add(botonInventario);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        botonVentas.addActionListener(e -> new VentanaVentas().setVisible(true));
        botonProductos.addActionListener(e -> new VentanaProductos().setVisible(true));
        botonInventario.addActionListener(e -> new VentanaInventario().setVisible(true));
    }

    private JButton crearBoton(String texto, String rutaIcono) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 18));
        boton.setFocusPainted(false);
        boton.setBackground(new Color(70, 130, 180)); // Color de fondo azul
        boton.setForeground(Color.WHITE); // Color del texto blanco
        boton.setIcon(new ImageIcon(rutaIcono)); // Icono del botón
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}