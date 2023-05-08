package com.facturpdr.aplicacion.sesiones.utilidades;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ConfiguracionUtilidad {
    private static final String PREFERENCIAS_NODO_NOMBRE = "com.facturpdr.aplicacion";
    private static final String JWT_TOKEN_KEY = "jwt_token";
    private static final Preferences preferencias = Preferences.userRoot().node(PREFERENCIAS_NODO_NOMBRE);;

    public static void eliminarPrefencias() {
        try {
            preferencias.removeNode();
        } catch (BackingStoreException ignored) { }
    }
    public static String obtenerJWT() {
        return preferencias.get(JWT_TOKEN_KEY, null);
    }
    public static void establecerJWT(String token) {
        preferencias.put(JWT_TOKEN_KEY, token);
    }
    public static void eliminarJWT() {
        preferencias.remove(JWT_TOKEN_KEY);
    }
}