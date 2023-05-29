package com.facturpdr.aplicacion.auth.excepciones;

/**
 * Excepción lanzada cuando se produce un error al crear un usuario.
 */
public class CrearUsuarioException extends Exception {

    /**
     * Crea una nueva instancia de la excepción sin mensaje detallado.
     */
    public CrearUsuarioException() {
        super();
    }
}
