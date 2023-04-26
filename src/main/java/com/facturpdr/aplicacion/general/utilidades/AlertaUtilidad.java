package com.facturpdr.aplicacion.general.utilidades;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

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

    public static Optional<ButtonType> mostrarAlertaConfirmacion(String titulo, String texto ,String mensaje, ButtonType... botones) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(texto);
        alert.setContentText(mensaje);
        alert.getButtonTypes().setAll(botones);
        alert.getDialogPane().setPrefHeight(200); // Establece el ancho preferido de la alerta
        return alert.showAndWait();

    }
}
