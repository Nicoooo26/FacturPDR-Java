package com.facturpdr.aplicacion.auth.utilidades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtilidad {
    public static String sha256(String cadena) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(cadena.getBytes());
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hash) {
                hexHash.append(String.format("%02x", b));
            }
            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
