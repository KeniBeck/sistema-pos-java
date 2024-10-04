package com.example;

import com.example.ui.VentanaPrincipal;

public class MainClass {
    public static void main(String[] args) {
        // Iniciar la aplicación Swing en el hilo de despacho de eventos
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}