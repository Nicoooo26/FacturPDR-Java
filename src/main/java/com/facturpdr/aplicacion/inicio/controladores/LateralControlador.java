package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.configuraciones.controladores.ControladorInformacionBasica;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.servicios.SesionServicio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class LateralControlador {
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();
    private final SesionServicio sesionServicio = new SesionServicio();
    private ControladorInformacionBasica infoBasicaControlador; // Agrega esta línea


    @FXML public void ManejaFacturas() {
        ventana.cambiarEscena("facturas/factura");
    }
    @FXML
    public void manejarInicio() {
        ventana.cambiarEscena("inicio/inicio");
    }

    @FXML public void manejaEmpleados() {
        ventana.cambiarEscena("empleados/empleados");
    }
    @FXML
    public void manejarCerrarSesion() {
        if (confirmarCambioEscena()) {
            Optional<ButtonType> cerrarSesion = AlertaUtilidad.confirmacion("Cerrar Sesión", null, "¿Estás seguro de que deseas cerrar sesión?", ButtonType.YES, ButtonType.NO);
            if (cerrarSesion.isPresent() && cerrarSesion.get() == ButtonType.YES) {
                sesionServicio.cerrarSesion();
            }
        }
    }

    @FXML
    public void manejarConfiguracion() {
        ventana.cambiarEscena("configuracion/configuracion");
    }

    @FXML
    public void manejarClientes() {
        if (confirmarCambioEscena()) ventana.cambiarEscena("clientes/clientes");
    }

    private boolean confirmarCambioEscena() {
        ControladorInformacionBasica info = ControladorInformacionBasica.obtenerInstancia();
        if (ControladorInformacionBasica.cambiosSinGuardar) {
            info.novisibles_nomodificables();
            return info.mostrarMensajeCambiosSinGuardar();

        }
        return true;
    }


    @FXML
    public void initialize() throws IOException {
    }
}
