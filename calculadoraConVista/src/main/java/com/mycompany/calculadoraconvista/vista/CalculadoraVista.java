package com.mycompany.calculadoraconvista.vista;

import javax.swing.*;
import java.awt.*;

public class CalculadoraVista extends JFrame {
    // Componentes de la interfaz
    private JTextField campoIP;
    private JTextField campoMascara;
    private JButton botonCalcular;
    private JTextArea areaResultados;

    public CalculadoraVista() {
        // Configuración de la ventana principal
        setTitle("Calculadora IPv4");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para la entrada de datos
        JPanel panelEntrada = new JPanel(new GridLayout(2, 2));
        panelEntrada.add(new JLabel("Dirección IP:"));
        campoIP = new JTextField();
        panelEntrada.add(campoIP);
        panelEntrada.add(new JLabel("Máscara (CIDR):"));
        campoMascara = new JTextField();
        panelEntrada.add(campoMascara);

        // Botón para calcular
        botonCalcular = new JButton("Calcular");

        // Área de resultados
        areaResultados = new JTextArea();
        areaResultados.setEditable(false);

        // Añadir componentes a la ventana
        add(panelEntrada, BorderLayout.NORTH);
        add(botonCalcular, BorderLayout.CENTER);
        add(new JScrollPane(areaResultados), BorderLayout.SOUTH);
    }

    // Getters para acceder a los componentes
    public JTextField getCampoIP() {
        return campoIP;
    }

    public JTextField getCampoMascara() {
        return campoMascara;
    }

    public JButton getBotonCalcular() {
        return botonCalcular;
    }

    public JTextArea getAreaResultados() {
        return areaResultados;
    }
}