package com.facturpdr.aplicacion.general.utilidades;

import javafx.scene.control.Alert;

public class AlertaUtilidad {
    public static void informacion(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }

    public static void advertencia(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }

    public static void error(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }
}
