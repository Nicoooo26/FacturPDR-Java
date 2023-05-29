package com.facturpdr.aplicacion.usuarios.excepciones;

/**
 * Clase de excepción personalizada para excepciones relacionadas con contraseñas iguales.
 * Esta excepción se lanza cuando se intenta cambiar la contraseña por una que es igual a la anterior.
 */
public class ContrasenaIgualException extends Exception {

    /**
     * Construye una nueva instancia de ContrasenaIgualException sin un mensaje detallado.
     */
    public ContrasenaIgualException() {
        super();
    }
}
