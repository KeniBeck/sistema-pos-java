package com.example.ui;

import com.example.controller.ProductoController;
import com.example.model.Producto;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VentanaProductos extends JFrame {
    private ProductoController productoController;
    private JTextField campoNombre, campoPrecio, campoStock;
    private JButton botonAgregar, botonEliminar;
    private JList<Producto> listaProductos;
    private DefaultListModel<Producto> modeloLista;

    public VentanaProductos() {
        productoController = new ProductoController();
        inicializarComponentes();
        cargarProductos();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Productos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        campoNombre = new JTextField();
        campoPrecio = new JTextField();
        campoStock = new JTextField();
        botonAgregar = new JButton("Agregar");
        botonEliminar = new JButton("Eliminar");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(campoPrecio);
        panelFormulario.add(new JLabel("Stock:"));
        panelFormulario.add(campoStock);
        panelFormulario.add(botonAgregar);
        panelFormulario.add(botonEliminar);

        modeloLista = new DefaultListModel<>();
        listaProductos = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaProductos);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        botonAgregar.addActionListener(e -> agregarProducto());
        botonEliminar.addActionListener(e -> eliminarProducto());
    }

    private void cargarProductos() {
        try {
            modeloLista.clear();
            for (Producto producto : productoController.obtenerTodosLosProductos()) {
                modeloLista.addElement(producto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
        }
    }

    private void agregarProducto() {
        try {
            String nombre = campoNombre.getText();
            double precio = Double.parseDouble(campoPrecio.getText());
            int stock = Integer.parseInt(campoStock.getText());
            Producto producto = new Producto(0, nombre, precio, stock);
            productoController.agregarProducto(producto);
            JOptionPane.showMessageDialog(this, "Producto agregado con éxito");
            limpiarCampos();
            cargarProductos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        Producto productoSeleccionado = listaProductos.getSelectedValue();
        if (productoSeleccionado != null) {
            try {
                productoController.eliminarProducto(productoSeleccionado.getId());
                JOptionPane.showMessageDialog(this, "Producto eliminado con éxito");
                cargarProductos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para eliminar");
        }
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoPrecio.setText("");
        campoStock.setText("");
    }
}