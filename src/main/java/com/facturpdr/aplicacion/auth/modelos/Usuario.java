package com.facturpdr.aplicacion.auth.modelos;

import java.util.Date;

/**
 * Representa un usuario en el sistema.
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String correoElectronico;
    private String contrasena;
    private Date fechaCreacion;

    /**
     * Crea una instancia de Usuario.
     *
     * @param id               El ID del usuario.
     * @param nombreUsuario    El nombre de usuario.
     * @param correoElectronico El correo electrónico del usuario.
     * @param contrasena       La contraseña del usuario.
     * @param fechaCreacion    La fecha de creación del usuario.
     */
    public Usuario(int id, String nombreUsuario, String correoElectronico, String contrasena, Date fechaCreacion) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id El ID del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correoElectronico El correo electrónico del usuario.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena La contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene la fecha de creación del usuario.
     *
     * @return La fecha de creación del usuario.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del usuario.
     *
     * @param fechaCreacion La fecha de creación del usuario.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
