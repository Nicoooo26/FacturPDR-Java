package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Optional;

public class InicioControlador implements Initializable {

    @FXML private FontAwesomeIconView IconoHelp;

    @FXML public void manejarCerrarSesion(ActionEvent event) {
        Optional<ButtonType> cerrarsesion = AlertaUtilidad.mostrarAlertaConfirmacion("Cerrar Sesión ", null, "¿Estás seguro de que deseas cerrar sesión?",
                ButtonType.YES, ButtonType.NO);
        if (cerrarsesion.get() == ButtonType.YES) {
            // Obtiene la ventana actual a través del evento de acción
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close(); // Cierra la ventana actual
        }
    }
    public void MensajeToolTip() {
        Tooltip informacion = new Tooltip("Nombre del proyecto: FacturPDR\nDesarrollado en: JavaFX\nCompilador: Gradle\nBase de datos: Oracle\n" +
                                               "Fecha de lanzamiento:30/05/2023 \nVersión actual: 1.0");
        Tooltip.install(IconoHelp,informacion);
        IconoHelp.setOnMouseClicked(event -> {
            if(!informacion.isShowing()) {
                informacion.show(IconoHelp, event.getScreenX() + 30, event.getScreenY() + 30);
            }
            else {
                informacion.hide();
            }
        });
    }

    public void initialize(URL url, ResourceBundle rb) {
        MensajeToolTip();
    }
}
