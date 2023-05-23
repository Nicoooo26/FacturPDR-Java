package com.facturpdr.aplicacion.sesiones.utilidades;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferenciaUtilidad {
    private static final String NODO_NOMBRE = "com.facturpdr.aplicacion";
    private static final String ID_USUARIO_KEY = "id_usuario";
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
    public static int obtenerIDUsuario() {
        return nodo.getInt(ID_USUARIO_KEY, -1);
    }
    public static void establecerIDUsuario(int id_usuario) {
        try {
            nodo.putInt(ID_USUARIO_KEY, id_usuario);
            nodo.flush();
        } catch (BackingStoreException ignored) { }
    }
    public static void eliminarIDUsuario() {
        try {
            nodo.remove(ID_USUARIO_KEY);
            nodo.flush();
        } catch (BackingStoreException ignored) { }
    }
}