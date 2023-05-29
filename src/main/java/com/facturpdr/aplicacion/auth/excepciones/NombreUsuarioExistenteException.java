package com.facturpdr.aplicacion.auth.excepciones;

/**
 * Excepción lanzada cuando se intenta crear un usuario con un nombre de usuario que ya existe.
 */
public class NombreUsuarioExistenteException extends Exception {

    /**
     * Crea una nueva instancia de la excepción sin mensaje detallado.
     */
    public NombreUsuarioExistenteException() {
        super();
    }
}