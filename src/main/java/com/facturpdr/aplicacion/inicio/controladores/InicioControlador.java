package com.facturpdr.aplicacion.inicio.controladores;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class InicioControlador implements Initializable {
    @FXML
    private FontAwesomeIconView IconoHelp;
    @FXML
    private Label cantidadFacturas;
    @FXML
    private Label cantidadEmpleados;
    @FXML
    private Label cantidadClientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tooltip informacion = new Tooltip("Nombre del proyecto: FacturPDR\n" +
                "Desarrollado en: JavaFX\n" +
                "Compilador: Gradle\n" +
                "Base de datos: Oracle\n" +
                "Fecha de lanzamiento: 30/05/2023\n" +
                "Versión actual: 1.0");

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
}
