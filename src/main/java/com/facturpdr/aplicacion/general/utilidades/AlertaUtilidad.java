/**
 * El paquete `com.facturpdr.aplicacion.general.utilidades` contiene clases que proporcionan utilidades generales para la aplicación.
 */
package com.facturpdr.aplicacion.general.utilidades;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * La clase `AlertaUtilidad` proporciona métodos para mostrar alertas de diferentes tipos (información, advertencia, error y confirmación) en una aplicación JavaFX.
 */
public class AlertaUtilidad {
    /**
     * Muestra una alerta de información con el título y descripción especificados.
     *
     * @param titulo      El título de la alerta.
     * @param descripcion La descripción de la alerta.
     */
    public static void informacion(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de advertencia con el título y descripción especificados.
     *
     * @param titulo      El título de la alerta.
     * @param descripcion La descripción de la alerta.
     */
    public static void advertencia(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de error con el título y descripción especificados.
     *
     * @param titulo      El título de la alerta.
     * @param descripcion La descripción de la alerta.
     */
    public static void error(String titulo, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(titulo);
        alert.setContentText(descripcion);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de confirmación con el título, texto y mensaje especificados, y permite al usuario seleccionar entre los botones proporcionados.
     *
     * @param titulo   El título de la alerta de confirmación.
     * @param texto    El texto de la alerta de confirmación.
     * @param mensaje  El mensaje de la alerta de confirmación.
     * @param botones  Los botones de la alerta de confirmación.
     * @return Un objeto Optional que contiene el botón seleccionado por el usuario.
     */
    public static Optional<ButtonType> confirmacion(String titulo, String texto, String mensaje, ButtonType... botones) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(texto);
        alert.setContentText(mensaje);
        alert.getButtonTypes().setAll(botones);
        alert.getDialogPane().setPrefHeight(100);
        return alert.showAndWait();
    }
}
