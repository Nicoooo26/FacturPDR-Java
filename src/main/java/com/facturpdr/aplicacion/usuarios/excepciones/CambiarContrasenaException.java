package com.facturpdr.aplicacion.usuarios.excepciones;

/**
 * Clase de excepción personalizada para excepciones relacionadas con el cambio de contraseña.
 * Esta excepción se lanza cuando se produce un error al cambiar una contraseña.
 */
public class CambiarContrasenaException extends Exception {

    /**
     * Construye una nueva CambiarContrasenaException sin mensaje detallado.
     */
    public CambiarContrasenaException() {
        super();
    }
}
