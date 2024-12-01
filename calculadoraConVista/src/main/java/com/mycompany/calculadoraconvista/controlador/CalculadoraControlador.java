package com.mycompany.calculadoraconvista.controlador;

import com.mycompany.calculadoraconvista.modelo.IPv4Address;
import com.mycompany.calculadoraconvista.vista.CalculadoraVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraControlador {
    private CalculadoraVista vista;

    public CalculadoraControlador(CalculadoraVista vista) {
        this.vista = vista;

        // Añadir listener al botón
        this.vista.getBotonCalcular().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarCalculo();
            }
        });
    }

    private void manejarCalculo() {
        try {
            // Obtener valores de la vista
            String ip = vista.getCampoIP().getText();
            String mascara = vista.getCampoMascara().getText();

            // Verificar que la máscara esté en el formato CIDR y separar
            int mascaraInt = 0;
            if (mascara.contains("/")) {
                String[] partes = mascara.split("/");
                ip = partes[0];
                mascara = partes[1];
                mascaraInt = Integer.parseInt(mascara);  // Convierto la máscara a int
            } else {
                mascaraInt = Integer.parseInt(mascara); // Si ya está como un número, lo convierto
            }

            // Crear modelo con los datos ingresados (IP y máscara como String y máscara como int)
            IPv4Address direccion = new IPv4Address(ip, mascaraInt);

            // Mostrar los resultados en el área de texto
            String resultados = String.format(
                "Dirección IP: %s (%s)\n" +
                "Máscara de red: %s (%s)\n" +
                "Dirección de red: %s\n" +
                "Dirección de broadcast: %s\n" +
                "Primer host: %s\n" +
                "Último host: %s\n" +
                "Máximo número de hosts: %.0f\n" +
                "Tipo: %s\n",
                direccion.getIPAddress(), direccion.getBinaryAddress(),
                direccion.getDecimalMask(), direccion.getBinaryMask(),
                direccion.getDecimalNetwork(),
                direccion.getDecimalBroadcast(),
                direccion.getDecimalFirstHost(),
                direccion.getDecimalLastHost(),
                direccion.getMaxHosts(),
                direccion.getType()
            );

            vista.getAreaResultados().setText(resultados);

        } catch (Exception ex) {
            vista.getAreaResultados().setText("Error: " + ex.getMessage());
        }
    }
}