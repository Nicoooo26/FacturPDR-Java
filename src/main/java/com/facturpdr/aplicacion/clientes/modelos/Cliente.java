package com.facturpdr.aplicacion.clientes.modelos;

import javafx.beans.property.*;

public class Cliente {

    private String nombre,apellidos,DNI,email,pais,ciudad,cuenta,direccion,nombrecompleto;

    private int movil,fijo,codigoPostal;

    public Cliente(String nombre, String apellidos, String DNI, String email, String pais, String ciudad, String cuenta,
                   String direccion, int movil, int fijo, int codigoPostal,String nombrecompleto) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.email = email;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cuenta = cuenta;
        this.direccion = direccion;
        this.movil = movil;
        this.fijo = fijo;
        this.codigoPostal = codigoPostal;
        this.setNombrecompleto(nombrecompleto);
    }
    public Cliente(String nombre, String apellidos, String DNI, String email, String pais, String ciudad, String cuenta,
                   String direccion, int movil, int fijo, int codigoPostal) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.email = email;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cuenta = cuenta;
        this.direccion = direccion;
        this.movil = movil;
        this.fijo = fijo;
        this.codigoPostal = codigoPostal;
    }
    public Cliente(String nombrecompleto, String DNI,String cuenta, int movil) {
        super();
        this.setNombrecompleto(nombrecompleto);
        this.DNI = DNI;
        this.cuenta = cuenta;
        this.movil = movil;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public String getEmail() {
        return email;
    }
    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }
    public String getCuenta() {
        return cuenta;
    }

    public String getDireccion() {
        return direccion;
    }
    public String getnombreCompleto() {
        return nombrecompleto;
    }

    public int getFijo() {
        return fijo;
    }

    public int getMovil() {
        return movil;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public StringProperty nombreProperty() {
        return new SimpleStringProperty(nombre);
    }

    public StringProperty apellidoProperty() {
        return new SimpleStringProperty(apellidos);
    }

    public StringProperty completoProperty() {
        return new SimpleStringProperty(nombrecompleto);
    }
    public StringProperty DNIProperty() {
        return new SimpleStringProperty(DNI);
    }

    public StringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }
    public StringProperty paisProperty() {
        return new SimpleStringProperty(pais);
    }

    public StringProperty ciudadProperty() {
        return new SimpleStringProperty(ciudad);
    }
    public StringProperty cuentaProperty() {
        return new SimpleStringProperty(cuenta);
    }

    public StringProperty direccionProperty() {
        return new SimpleStringProperty(direccion);
    }
    public IntegerProperty movilProperty() {
        return new SimpleIntegerProperty(movil);
    }
    public IntegerProperty fijoProperty() {
        return new SimpleIntegerProperty(fijo);
    }
    public IntegerProperty codigoPostalProperty() {
        return new SimpleIntegerProperty(codigoPostal);
    }
    public String getNombrecompleto() {
        return nombrecompleto;
    }
    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

}
