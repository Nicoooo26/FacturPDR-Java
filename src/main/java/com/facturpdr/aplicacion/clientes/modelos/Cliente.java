package com.facturpdr.aplicacion.clientes.modelos;

import javafx.beans.property.*;

/**
 * Clase que representa un cliente.
 */
public class Cliente {

    private String nombre, apellidos, DNI, email, pais, ciudad, cuenta, direccion, nombrecompleto;
    private int movil, fijo, codigoPostal;

    /**
     * Constructor de la clase Cliente.
     * @param nombre El nombre del cliente.
     * @param apellidos Los apellidos del cliente.
     * @param DNI El DNI del cliente.
     * @param email El correo electrónico del cliente.
     * @param pais El país del cliente.
     * @param ciudad La ciudad del cliente.
     * @param cuenta La cuenta del cliente.
     * @param direccion La dirección del cliente.
     * @param movil El número de teléfono móvil del cliente.
     * @param fijo El número de teléfono fijo del cliente.
     * @param codigoPostal El código postal del cliente.
     * @param nombrecompleto El nombre completo del cliente.
     */
    public Cliente(String nombre, String apellidos, String DNI, String email, String pais, String ciudad, String cuenta,
                   String direccion, int movil, int fijo, int codigoPostal, String nombrecompleto) {
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

    /**
     * Constructor de la clase Cliente sin nombre completo.
     * @param nombre El nombre del cliente.
     * @param apellidos Los apellidos del cliente.
     * @param DNI El DNI del cliente.
     * @param email El correo electrónico del cliente.
     * @param pais El país del cliente.
     * @param ciudad La ciudad del cliente.
     * @param cuenta La cuenta del cliente.
     * @param direccion La dirección del cliente.
     * @param movil El número de teléfono móvil del cliente.
     * @param fijo El número de teléfono fijo del cliente.
     * @param codigoPostal El código postal del cliente.
     */
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

    /**
     * Constructor de la clase Cliente con nombre completo, DNI, cuenta y número de teléfono móvil.
     * @param nombrecompleto El nombre completo del cliente.
     * @param DNI El DNI del cliente.
     * @param cuenta La cuenta del cliente.
     * @param movil El número de teléfono móvil del cliente.
     */
    public Cliente(String nombrecompleto, String DNI, String cuenta, int movil) {
        super();
        this.setNombrecompleto(nombrecompleto);
        this.DNI = DNI;
        this.cuenta = cuenta;
        this.movil = movil;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del cliente.
     * @return Los apellidos del cliente.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el DNI del cliente.
     * @return El DNI del cliente.
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return El correo electrónico del cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene el país del cliente.
     * @return El país del cliente.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Obtiene la ciudad del cliente.
     * @return La ciudad del cliente.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Obtiene la cuenta del cliente.
     * @return La cuenta del cliente.
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Obtiene la dirección del cliente.
     * @return La dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Obtiene el número de teléfono fijo del cliente.
     * @return El número de teléfono fijo del cliente.
     */
    public int getFijo() {
        return fijo;
    }

    /**
     * Obtiene el número de teléfono móvil del cliente.
     * @return El número de teléfono móvil del cliente.
     */
    public int getMovil() {
        return movil;
    }

    /**
     * Obtiene el código postal del cliente.
     * @return El código postal del cliente.
     */
    public int getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Obtiene la propiedad del nombre del cliente.
     * @return La propiedad del nombre del cliente.
     */
    public StringProperty nombreProperty() {
        return new SimpleStringProperty(nombre);
    }

    /**
     * Obtiene la propiedad de los apellidos del cliente.
     * @return La propiedad de los apellidos del cliente.
     */
    public StringProperty apellidoProperty() {
        return new SimpleStringProperty(apellidos);
    }

    /**
     * Obtiene la propiedad del nombre completo del cliente.
     * @return La propiedad del nombre completo del cliente.
     */
    public StringProperty completoProperty() {
        return new SimpleStringProperty(nombrecompleto);
    }

    /**
     * Obtiene la propiedad del DNI del cliente.
     * @return La propiedad del DNI del cliente.
     */
    public StringProperty DNIProperty() {
        return new SimpleStringProperty(DNI);
    }

    /**
     * Obtiene la propiedad del correo electrónico del cliente.
     * @return La propiedad del correo electrónico del cliente.
     */
    public StringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }

    /**
     * Obtiene la propiedad del país del cliente.
     * @return La propiedad del país del cliente.
     */
    public StringProperty paisProperty() {
        return new SimpleStringProperty(pais);
    }

    /**
     * Obtiene la propiedad de la ciudad del cliente.
     * @return La propiedad de la ciudad del cliente.
     */
    public StringProperty ciudadProperty() {
        return new SimpleStringProperty(ciudad);
    }

    /**
     * Obtiene la propiedad de la cuenta del cliente.
     * @return La propiedad de la cuenta del cliente.
     */
    public StringProperty cuentaProperty() {
        return new SimpleStringProperty(cuenta);
    }

    /**
     * Obtiene la propiedad de la dirección del cliente.
     * @return La propiedad de la dirección del cliente.
     */
    public StringProperty direccionProperty() {
        return new SimpleStringProperty(direccion);
    }

    /**
     * Obtiene la propiedad del número de teléfono móvil del cliente.
     * @return La propiedad del número de teléfono móvil del cliente.
     */
    public IntegerProperty movilProperty() {
        return new SimpleIntegerProperty(movil);
    }

    /**
     * Obtiene la propiedad del número de teléfono fijo del cliente.
     * @return La propiedad del número de teléfono fijo del cliente.
     */
    public IntegerProperty fijoProperty() {
        return new SimpleIntegerProperty(fijo);
    }

    /**
     * Obtiene la propiedad del código postal del cliente.
     * @return La propiedad del código postal del cliente.
     */
    public IntegerProperty codigoPostalProperty() {
        return new SimpleIntegerProperty(codigoPostal);
    }

    /**
     * Obtiene el nombre completo del cliente.
     * @return El nombre completo del cliente.
     */
    public String getNombrecompleto() {
        return nombrecompleto;
    }

    /**
     * Establece el nombre completo del cliente.
     * @param nombrecompleto El nombre completo del cliente.
     */
    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

}
