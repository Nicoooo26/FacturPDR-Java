package com.facturpdr.aplicacion.inicio.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import java.util.Optional;


public class InicioControlador implements Initializable {

    @FXML private FontAwesomeIconView IconoHelp;
    @FXML private FontAwesomeIconView IconoConfiguracion ;
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
