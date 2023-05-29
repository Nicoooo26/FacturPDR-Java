/**
 * El paquete `com.facturpdr.aplicacion.sesiones.utilidades` contiene clases de utilidad para gestionar las preferencias de la sesión del usuario.
 */
package com.facturpdr.aplicacion.sesiones.utilidades;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * La clase `PreferenciaUtilidad` proporciona métodos para gestionar las preferencias de la sesión del usuario.
 */
public class PreferenciaUtilidad {
    private static final String NODO_NOMBRE = "com.facturpdr.aplicacion";
    private static final String ID_USUARIO_KEY = "id_usuario";
    private static final Preferences nodo = Preferences.userRoot().node(NODO_NOMBRE);

    /**
     * Elimina todas las preferencias de la sesión del usuario.
     */
    public static void eliminarPrefencias() {
        try {
            String[] claves = nodo.keys();
            for (String clave : claves) {
                nodo.remove(clave);
            }
            nodo.flush();
        } catch (BackingStoreException ignored) {
        }
    }

    /**
     * Obtiene el ID de usuario almacenado en las preferencias de la sesión.
     *
     * @return El ID de usuario, o -1 si no se encuentra.
     */
    public static int obtenerIDUsuario() {
        return nodo.getInt(ID_USUARIO_KEY, -1);
    }

    /**
     * Establece el ID de usuario en las preferencias de la sesión.
     *
     * @param id_usuario El ID de usuario a establecer.
     */
    public static void establecerIDUsuario(int id_usuario) {
        try {
            nodo.putInt(ID_USUARIO_KEY, id_usuario);
            nodo.flush();
        } catch (BackingStoreException ignored) {
        }
    }

    /**
     * Elimina el ID de usuario de las preferencias de la sesión.
     */
    public static void eliminarIDUsuario() {
        try {
            nodo.remove(ID_USUARIO_KEY);
            nodo.flush();
        } catch (BackingStoreException ignored) {
        }
    }
}
