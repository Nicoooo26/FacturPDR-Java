package com.facturpdr.aplicacion.empleados.modelos;

import java.util.Date;

public class Empleado {
    private String NIF, apellidos,nombre ;
    private Date fechaNacimiento;
    private int telefono;

    public Empleado(String NIF,String apellidos,String nombre,Date fechaNacimiento, int telefono) {
        this.NIF = NIF;
        this.apellidos=apellidos;
        this.nombre=nombre;
        this.fechaNacimiento=fechaNacimiento;
        this.telefono=telefono;
    }
    public Empleado(String NIF,String nombre,String apellidos,int telefono){
        this.NIF=NIF;
        this.apellidos =apellidos;
        this.nombre=nombre;
        this.telefono=telefono;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
