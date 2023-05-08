package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.servicios.SesionServicio;
import com.facturpdr.aplicacion.sesiones.utilidades.ConfiguracionUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class LateralControlador {

    @FXML
    public void manejarInicio(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("inicio/inicio.fxml");
    }

    @FXML
    public void manejarCerrarSesion(ActionEvent event) {
        SesionServicio sesionServicio = new SesionServicio();

        Optional<ButtonType> cerrarSesion = AlertaUtilidad.confirmacion("Cerrar Sesión", null, "¿Estás seguro de que deseas cerrar sesión?", ButtonType.YES, ButtonType.NO);
        if (cerrarSesion.isPresent() && cerrarSesion.get() == ButtonType.YES) {
            sesionServicio.cerrarSesion();
        }
    }
    @FXML
    public void manejarConfiguracion(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("configuracion/configuracion.fxml");
    }
}
