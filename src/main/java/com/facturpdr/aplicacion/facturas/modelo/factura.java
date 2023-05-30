package com.facturpdr.aplicacion.facturas.modelo;
import java.sql.Timestamp;

public class factura {
        private int idCliente;
        private String nifEmpleado;
        private String matricula ;
        private double costeManoObra;
        private String notaInterna;
        private String notaExterna;
        private Timestamp fechaCreacion;
        private Timestamp fechaVencimiento;
        private String tipo;
        private int material;
        private int aluminio;
        private int pintura;
        private String tamano;
        private int precioUnitario;
        private double precioTotal;
+

    public factura(int id, String dni, double precioTotal, String matricula) {
        this.idCliente = id;
        this.nifEmpleado = dni ;
        this.precioTotal = precioTotal;
        this.matricula = matricula ;
    }
        public factura(int id, int idCliente, String nifEmpleado, double costeManoObra, String notaInterna, String notaExterna, Timestamp fechaCreacion, Timestamp fechaVencimiento, String tipo, int material, int aluminio, int pintura, String tamano, int precioUnitario, double precioTotal) {
            this.idCliente = idCliente;
            this.nifEmpleado = nifEmpleado;
            this.costeManoObra = costeManoObra;
            this.notaInterna = notaInterna;
            this.notaExterna = notaExterna;
            this.fechaCreacion = fechaCreacion;
            this.fechaVencimiento = fechaVencimiento;
            this.tipo = tipo;
            this.material = material;
            this.aluminio = aluminio;
            this.pintura = pintura;
            this.tamano = tamano;
            this.precioUnitario = precioUnitario;
            this.precioTotal = precioTotal;
        }

        // Getters y Setters para los atributos

        public String getMatricula() {
            return this.matricula    ;
    }
        public void setMatricula(String matri) {
            this.matricula = matri;
        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public String getNifEmpleado() {
            return nifEmpleado;
        }

        public void setNifEmpleado(String nifEmpleado) {
            this.nifEmpleado = nifEmpleado;
        }

        public double getCosteManoObra() {
            return costeManoObra;
        }

        public void setCosteManoObra(double costeManoObra) {
            this.costeManoObra = costeManoObra;
        }

        public String getNotaInterna() {
            return notaInterna;
        }

        public void setNotaInterna(String notaInterna) {
            this.notaInterna = notaInterna;
        }

        public String getNotaExterna() {
            return notaExterna;
        }

        public void setNotaExterna(String notaExterna) {
            this.notaExterna = notaExterna;
        }

        public Timestamp getFechaCreacion() {
            return fechaCreacion;
        }

        public void setFechaCreacion(Timestamp fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public Timestamp getFechaVencimiento() {
            return fechaVencimiento;
        }

        public void setFechaVencimiento(Timestamp fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public int getMaterial() {
            return material;
        }

        public void setMaterial(int material) {
            this.material = material;
        }

        public int getAluminio() {
            return aluminio;
        }

        public void setAluminio(int aluminio) {
            this.aluminio = aluminio;
        }

        public int getPintura() {
            return pintura;
        }

        public void setPintura(int pintura) {
            this.pintura = pintura;
        }

        public String getTamano() {
            return tamano;
        }

        public void setTamano(String tamano) {
            this.tamano = tamano;
        }

        public int getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(int precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public double getPrecioTotal() {
            return precioTotal;
        }

        public void setPrecioTotal(double precioTotal) {
            this.precioTotal = precioTotal;
        }
    }

}
