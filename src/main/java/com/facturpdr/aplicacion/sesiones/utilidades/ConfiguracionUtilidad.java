package com.facturpdr.aplicacion.sesiones.utilidades;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ConfiguracionUtilidad {
    private static final String NODO_NOMBRE = "com.facturpdr.aplicacion";
    private static final String JWT_TOKEN_KEY = "jwt_token";
    private static final Preferences nodo = Preferences.userRoot().node(NODO_NOMBRE);;

    public static void eliminarPrefencias() {
        try {
            String valor = nodo.get(NODO_NOMBRE, null);
            if (valor != null) {
                String[] subnodos = nodo.childrenNames();
                for (String subnodo : subnodos) {
                    nodo.node(subnodo).removeNode();
                }
            }
        } catch (BackingStoreException ignored) { }
    }
    public static String obtenerJWT() {
        return nodo.get(JWT_TOKEN_KEY, null);
    }
    public static void establecerJWT(String token) {
        nodo.put(JWT_TOKEN_KEY, token);
    }
    public static void eliminarJWT() {
        nodo.remove(JWT_TOKEN_KEY);
    }
}