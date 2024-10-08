package com.example.ui;

import com.example.controller.InventarioController;
import com.example.controller.ProductoController;
import com.example.model.Inventario;
import com.example.model.Producto;
import com.example.model.ProductoInventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class VentanaInventario extends JFrame {
    private InventarioController inventarioController;
    private ProductoController productoController;
    private JTable tablaInventario;
    private DefaultTableModel modeloTabla;
    private JButton botonActualizar, botonAgregar, botonEliminar;
    private JComboBox<Producto> comboProductos;
    private JComboBox<Inventario> comboInventarios;
    private JLabel labelCantidad;

    public VentanaInventario() {
        inventarioController = new InventarioController();
        productoController = new ProductoController();
        inicializarComponentes();
        cargarInventario();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Inventario");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Actualizar las columnas para incluir el ID del producto
        String[] columnas = {"ID Producto", "Producto", "Ubicación", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaInventario = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaInventario);

        JPanel panelControles = new JPanel(new FlowLayout());
        comboProductos = new JComboBox<>();
        comboInventarios = new JComboBox<>();
        labelCantidad = new JLabel("Cantidad: 0");
        botonAgregar = new JButton("Agregar");
        botonEliminar = new JButton("Eliminar");
        botonActualizar = new JButton("Actualizar");

        panelControles.add(new JLabel("Producto:"));
        panelControles.add(comboProductos);
        panelControles.add(new JLabel("Ubicación:"));
        panelControles.add(comboInventarios);
        panelControles.add(labelCantidad);
        panelControles.add(botonAgregar);
        panelControles.add(botonEliminar);
        panelControles.add(botonActualizar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);

        botonAgregar.addActionListener(e -> agregarProductoAInventario());
        botonEliminar.addActionListener(e -> eliminarProductoDeInventario());
        botonActualizar.addActionListener(e -> cargarInventario());

        comboProductos.addActionListener(e -> actualizarCantidad());

        cargarProductos();
        cargarInventarios();
    }

    private void cargarProductos() {
        try {
            List<Producto> productos = productoController.obtenerTodosLosProductos();
            comboProductos.removeAllItems();
            for (Producto producto : productos) {
                comboProductos.addItem(producto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
        }
    }

    private void cargarInventarios() {
        comboInventarios.removeAllItems();
        // Agrega las ubicaciones manualmente
        comboInventarios.addItem(new Inventario(1, "Almacén Principal"));
        comboInventarios.addItem(new Inventario(2, "Almacén Secundario"));
        comboInventarios.addItem(new Inventario(3, "Tienda 1"));
        comboInventarios.addItem(new Inventario(4, "Tienda 2"));
    }

    private void cargarInventario() {
        try {
            modeloTabla.setRowCount(0);
            List<Producto> productos = productoController.obtenerTodosLosProductos();
            for (Producto producto : productos) {
                List<ProductoInventario> productosInventario = inventarioController.obtenerProductosInventarioPorProducto(producto.getId());
                for (ProductoInventario pi : productosInventario) {
                    Inventario inventario = inventarioController.obtenerTodosLosInventarios().stream()
                            .filter(inv -> inv.getId() == pi.getInventarioId())
                            .findFirst()
                            .orElse(null);
                    if (inventario != null) {
                        Object[] fila = {
                            producto.getId(), // ID del producto
                            producto.getNombre(),
                            inventario.getUbicacion(),
                            pi.getCantidad()
                        };
                        modeloTabla.addRow(fila);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar inventario: " + e.getMessage());
        }
    }

    private void actualizarCantidad() {
        Producto productoSeleccionado = (Producto) comboProductos.getSelectedItem();
        if (productoSeleccionado != null) {
            try {
                int cantidad = inventarioController.obtenerStockProducto(productoSeleccionado.getId());
                labelCantidad.setText("Cantidad: " + cantidad);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener la cantidad del producto: " + e.getMessage());
            }
        }
    }

    private void agregarProductoAInventario() {
        try {
            Producto productoSeleccionado = (Producto) comboProductos.getSelectedItem();
            Inventario inventarioSeleccionado = (Inventario) comboInventarios.getSelectedItem();
            int cantidad = Integer.parseInt(labelCantidad.getText().split(": ")[1]);

            if (productoSeleccionado != null && inventarioSeleccionado != null && cantidad > 0) {
                // Verifica si el inventario existe, si no, agrégalo
                if (!inventarioController.existeInventario(inventarioSeleccionado.getId())) {
                    inventarioController.agregarInventario(inventarioSeleccionado);
                }

                // Verifica si el producto existe, si no, agrégalo
                if (!productoController.existeProducto(productoSeleccionado.getId())) {
                    productoController.agregarProducto(productoSeleccionado);
                }

                // Ahora agrega la entrada en la tabla intermedia ProductoInventario
                ProductoInventario nuevoPI = new ProductoInventario(0, productoSeleccionado.getId(), inventarioSeleccionado.getId(), cantidad);
                inventarioController.agregarProductoAInventario(nuevoPI);

                JOptionPane.showMessageDialog(this, "Producto agregado al inventario con éxito");
                cargarInventario();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad válida");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto al inventario: " + e.getMessage());
        }
    }

    private void eliminarProductoDeInventario() {
        int filaSeleccionada = tablaInventario.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            try {
                inventarioController.eliminarProductoDeInventario(id);
                JOptionPane.showMessageDialog(this, "Producto eliminado del inventario con éxito");
                cargarInventario();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar producto del inventario: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para eliminar");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaInventario().setVisible(true));
    }
}