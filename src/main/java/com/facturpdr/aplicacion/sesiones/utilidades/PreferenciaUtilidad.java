package com.facturpdr.aplicacion.sesiones.utilidades;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferenciaUtilidad {
    private static final String NODO_NOMBRE = "com.facturpdr.aplicacion";
    private static final String JWT_TOKEN_KEY = "jwt_token";
    private static final Preferences nodo = Preferences.userRoot().node(NODO_NOMBRE);;

    public static void eliminarPrefencias() {
        try {
            String[] claves = nodo.keys();
            for (String clave : claves) {
                nodo.remove(clave);
            }
            nodo.flush();
    } catch (BackingStoreException ignored) { }
    }
    public static String obtenerJWT() {
        return nodo.get(JWT_TOKEN_KEY, null);
    }
    public static void establecerJWT(String token) {
        try {
            nodo.put(JWT_TOKEN_KEY, token);
            nodo.flush();
        } catch (BackingStoreException ignored) { }
    }
    public static void eliminarJWT() {
        try {
            nodo.remove(JWT_TOKEN_KEY);
            nodo.flush();
        } catch (BackingStoreException ignored) { }
    }
}