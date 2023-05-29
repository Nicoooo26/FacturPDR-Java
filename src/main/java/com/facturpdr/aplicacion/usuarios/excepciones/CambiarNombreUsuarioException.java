package com.facturpdr.aplicacion.usuarios.excepciones;

/**
 * Clase de excepción personalizada para excepciones relacionadas con el cambio de nombres de usuario.
 * Esta excepción se lanza cuando ocurre un error al cambiar un nombre de usuario.
 */
public class CambiarNombreUsuarioException extends Exception {

    /**
     * Construye una nueva instancia de CambiarNombreUsuarioException sin un mensaje detallado.
     */
    public CambiarNombreUsuarioException() {
        super();
    }
}