package com.mycompany.calculadoraconvista.modelo;

public class IPv4Address {
    private String IPAddress;
    private int mask; // La máscara se maneja ahora como un entero

    private String binaryAddress;
    private String binaryMask;
    private String decimalMask;
    private String CIDRMask;
    
    private String binaryNetwork;
    private String decimalNetwork;
    
    private String binaryBroadcast;
    private String decimalBroadcast;
    
    private String binaryFirstHost;
    private String decimalFirstHost;
    
    private String binaryLastHost;
    private String decimalLastHost;
    
    private double maxHosts;
    
    private String type;

    // Constructor
    public IPv4Address(String IPAddress, int mask) {
        this.IPAddress = IPAddress;
        this.mask = mask;

        // Convertir la IP a binario
        this.binaryAddress = IPAddressToBinary();

        // Convertir la máscara a binario
        this.binaryMask = "1".repeat(mask) + "0".repeat(32 - mask);
        this.decimalMask = binaryToDecimal(binaryMask);
        this.CIDRMask = "/" + mask;

        // Calcular la dirección de red
        this.binaryNetwork = binaryAddress.substring(0, mask) + "0".repeat(32 - mask);
        this.decimalNetwork = binaryToDecimal(binaryNetwork);

        // Calcular la dirección de broadcast
        this.binaryBroadcast = binaryAddress.substring(0, mask) + "1".repeat(32 - mask);
        this.decimalBroadcast = binaryToDecimal(binaryBroadcast);

        // Calcular el primer host
        this.binaryFirstHost = binaryNetwork.substring(0, 31) + "1";
        this.decimalFirstHost = binaryToDecimal(binaryFirstHost);

        // Calcular el último host
        this.binaryLastHost = binaryBroadcast.substring(0, 31) + "0";
        this.decimalLastHost = binaryToDecimal(binaryLastHost);

        // Calcular el máximo número de hosts
        this.maxHosts = Math.pow(2, 32 - mask) - 2;

        // Determinar si la IP es privada o pública
        this.type = addressType();
    }

    // Métodos getter (como antes)
    public String getIPAddress() {
        return IPAddress;
    }

    public String getBinaryAddress() {
        return binaryNotation(binaryAddress);
    }

    public String getBinaryMask() {
        return binaryNotation(binaryMask);
    }

    public String getDecimalMask() {
        return decimalMask;
    }

    public String getCIDRMask() {
        return CIDRMask;
    }

    public String getBinaryNetwork() {
        return binaryNotation(binaryNetwork);
    }

    public String getDecimalNetwork() {
        return decimalNetwork;
    }

    public String getBinaryBroadcast() {
        return binaryNotation(binaryBroadcast);
    }

    public String getDecimalBroadcast() {
        return decimalBroadcast;
    }

    public String getBinaryLastHost() {
        return binaryNotation(binaryLastHost);
    }

    public String getBinaryFirstHost() {
        return binaryNotation(binaryFirstHost);
    }

    public String getDecimalFirstHost() {
        return decimalFirstHost;
    }

    public String getDecimalLastHost() {
        return decimalLastHost;
    }

    public double getMaxHosts() {
        return maxHosts;
    }

    public String getType() {
        return type;
    }

    // Métodos privados para convertir la IP y la máscara a binario y decimal
    private String IPAddressToBinary() {
        String result = "";
        String[] octets = IPAddress.split("\\.");
        for (String octet : octets) {
            result += String.format("%8s", Integer.toBinaryString(Integer.valueOf(octet))).replace(" ", "0");
        }
        return result;
    }

    private String binaryNotation(String binary) {
        return binary.substring(0, 8) + "." +
               binary.substring(8, 16) + "." +
               binary.substring(16, 24) + "." +
               binary.substring(24, 32);
    }

    private String binaryToDecimal(String binary) {
        return Integer.parseInt(binary.substring(0, 8), 2) + "." +
               Integer.parseInt(binary.substring(8, 16), 2) + "." +
               Integer.parseInt(binary.substring(16, 24), 2) + "." +
               Integer.parseInt(binary.substring(24, 32), 2);
    }

    private String addressType() {
        String[] octets = IPAddress.split("\\.");
        int firstOctet = Integer.parseInt(octets[0]);
        if ((firstOctet >= 10 && firstOctet <= 10) ||
            (firstOctet >= 172 && firstOctet <= 172) ||
            (firstOctet >= 192 && firstOctet <= 192)) {
            return "Privada";
        } else {
            return "Pública";
        }
    }
}