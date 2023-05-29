package com.facturpdr.aplicacion.usuarios.excepciones;

/**
 * Clase de excepción personalizada para excepciones relacionadas con el cambio de direcciones de correo electrónico.
 * Esta excepción se lanza cuando ocurre un error al cambiar una dirección de correo electrónico.
 */
public class CambiarCorreoElectronicoException extends Exception {

    /**
     * Construye una nueva instancia de CambiarCorreoElectronicoException sin un mensaje detallado.
     */
    public CambiarCorreoElectronicoException() {
        super();
    }
}