package com.facturpdr.aplicacion.inicio.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;

public class InicioControlador implements Initializable {

    @FXML private FontAwesomeIconView IconoHelp;
    public void MensajeToolTip() {
        Tooltip informacion = new Tooltip("Nombre del proyecto: FacturPDR\nDesarrollado en: JavaFX\nCompilador: Gradle\nBase de datos: Oracle\n" + "Fecha de lanzamiento:30/05/2023 \nVersión actual: 1.0");
        Tooltip.install(IconoHelp, informacion);
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
