package com.mycompany.calculadoraconvista;

import com.mycompany.calculadoraconvista.controlador.CalculadoraControlador;
import com.mycompany.calculadoraconvista.vista.CalculadoraVista;
import javax.swing.*;
import java.awt.*;

public class CalculadoraConVista {
    public static void main(String[] args) {
        // Crear la vista
        CalculadoraVista vista = new CalculadoraVista();
        vista.setVisible(true);

        // Crear el controlador
        new CalculadoraControlador(vista);
    }
}