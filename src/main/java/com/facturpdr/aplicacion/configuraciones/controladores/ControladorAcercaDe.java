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
     * Manejador de eventos para el clic del botón "enviar_reporte".
     * Envía un informe por correo electrónico.
     *
     * @param event El evento de acción.
     * @throws MessagingException Si se produce un error al enviar el correo electrónico.
     */
    @FXML
    public void enviar_reporte(ActionEvent event) throws MessagingException {
        if (obtieneproblema.getText().isEmpty()) {
            AlertaUtilidad.advertencia("Advertencia Correo.", "El mensaje del correo está vacío.");
        } else {
            CorreoUtilidad.enviar("dario.quinde34@gmail.com", obtieneAsunto.getText().toString(),
                    obtieneproblema.getText().toString());
        }
    }
}
