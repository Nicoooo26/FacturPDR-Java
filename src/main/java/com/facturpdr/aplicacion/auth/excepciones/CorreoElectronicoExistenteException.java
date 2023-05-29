package com.facturpdr.aplicacion.auth.excepciones;

/**
 * Excepción lanzada cuando se intenta registrar un correo electrónico que ya existe en el sistema.
 */
public class CorreoElectronicoExistenteException extends Exception {

    /**
     * Crea una nueva instancia de la excepción sin mensaje detallado.
     */
    public CorreoElectronicoExistenteException() {
        super();
    }
}
