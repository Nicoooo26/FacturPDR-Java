package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.servicios.SesionServicio;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class LateralControlador {
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();
    private final SesionServicio sesionServicio = new SesionServicio();

    @FXML
    public void manejarInicio() {
        ventana.cambiarEscena("inicio/inicio");
    }

    @FXML
    public void manejarCerrarSesion() {
        Optional<ButtonType> cerrarSesion = AlertaUtilidad.confirmacion("Cerrar Sesión", null, "¿Estás seguro de que deseas cerrar sesión?", ButtonType.YES, ButtonType.NO);
        if (cerrarSesion.isPresent() && cerrarSesion.get() == ButtonType.YES) {
            sesionServicio.cerrarSesion();
        }
    }
    @FXML
    public void manejarConfiguracion() {
        ventana.cambiarEscena("configuracion/configuracion");
    }

    @FXML
    public void manejarClientes() {
        ventana.cambiarEscena("clientes/clientes");
    }
}
