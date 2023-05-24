package com.facturpdr.aplicacion.auth.modelos;

import java.util.Date;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String correoElectronico;
    private String contrasena;
    private Date fechaCreacion;

    public Usuario(int id, String nombreUsuario, String correoElectronico, String contrasena, Date fechaCreacion) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
