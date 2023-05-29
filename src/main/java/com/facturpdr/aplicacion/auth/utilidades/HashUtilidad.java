package com.facturpdr.aplicacion.auth.utilidades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilidad para generar hashes utilizando el algoritmo SHA-256.
 */
public class HashUtilidad {
    /**
     * Genera un hash SHA-256 de una cadena de texto.
     *
     * @param cadena La cadena de texto a hashear.
     * @return El hash SHA-256 generado.
     */
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
