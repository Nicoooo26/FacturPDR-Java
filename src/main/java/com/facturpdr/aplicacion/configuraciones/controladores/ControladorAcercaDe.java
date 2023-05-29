package com.facturpdr.aplicacion.configuraciones.controladores;

import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.general.utilidades.CorreoUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;

public class ControladorAcercaDe {

    @FXML
    TextField obtieneAsunto;
    @FXML
    TextArea obtieneproblema;

    /**
     * Event handler for the "enviar_reporte" button click.
     * Sends a report via email.
     * @param event The action event.
     * @throws MessagingException If an error occurs while sending the email.
     */
    @FXML
    public void enviar_reporte(ActionEvent event) throws MessagingException {
        if (obtieneproblema.getText().isEmpty()) {
            AlertaUtilidad.advertencia("Advertencia Correo.", "El mensaje del correo esta vacio.");
        } else {
            CorreoUtilidad.enviar("dario.quinde34@gmail.com", obtieneAsunto.getText().toString(),
                    obtieneproblema.getText().toString());
        }
    }
}
