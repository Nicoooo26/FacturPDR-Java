package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class LateralControlador {

    @FXML public void manejarHome(ActionEvent event ) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("inicio/inicio.fxml");
        ventana.cambiarTitulo("FacturPDR - Configurafcion");
    }

    @FXML public void manejarCerrarSesion(ActionEvent event) {
        Optional<ButtonType> cerrarsesion = AlertaUtilidad.mostrarAlertaConfirmacion("Cerrar Sesión ", null, "¿Estás seguro de que deseas cerrar sesión?",
                ButtonType.YES, ButtonType.NO);
        if (cerrarsesion.get() == ButtonType.YES) {
            // Obtiene la ventana actual a través del evento de acción
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close(); // Cierra la ventana actual
        }
    }
    @FXML public void manejarConfiguracion(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("configuracion/configuracion.fxml");
        ventana.cambiarTitulo("FacturPDR - Configuracion");
    }
}
