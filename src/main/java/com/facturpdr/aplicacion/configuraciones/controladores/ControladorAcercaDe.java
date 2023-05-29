package com.facturpdr.aplicacion.configuraciones.controladores;

import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.general.utilidades.CorreoUtilidad;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;

public class ControladorAcercaDe {
    @FXML
    public TextField asunto;
    @FXML
    public TextArea cuerpo;

    @FXML
    public void enviarReporte() {
        if (asunto.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un asunto", "Por favor, introduce el asunto");
            return;
        }

        boolean asuntoValido = asunto.getText().matches("^[A-Za-z0-9\\s\\-.,?!'\"@]+$");
        if (!asuntoValido) {
            AlertaUtilidad.error("El asunto debe ser válido", "El texto proporcionado no cumple con el formato válido. Por favor, asegúrate de que solo se utilicen letras (mayúsculas y minúsculas), números y los siguientes caracteres especiales: espacios, guiones, puntos, comas, signos de interrogación, comillas simples, comillas dobles y el símbolo @");
            return;
        }

        if (cuerpo.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un cuerpo", "Por favor, introduce el cuerpo");
            return;
        }

        boolean cuerpoValido = asunto.getText().matches("^[A-Za-z0-9\\s\\-.,?!'\"@]+$");
        if (!cuerpoValido) {
            AlertaUtilidad.error("El cuerpo debe ser válido", "El texto proporcionado no cumple con el formato válido. Por favor, asegúrate de que solo se utilicen letras (mayúsculas y minúsculas), números y los siguientes caracteres especiales: espacios, guiones, puntos, comas, signos de interrogación, comillas simples, comillas dobles y el símbolo @");
            return;
        }

        try {
            CorreoUtilidad.enviar("facturpdr@gmail.com", asunto.getText(), cuerpo.getText());
        } catch (MessagingException e) {
            AlertaUtilidad.error("Error al enviar el reporte", "Lo sentimos, se ha producido un error al registrar el usuario, vuelva a intentarlo");
            return;
        }

        AlertaUtilidad.informacion("Reporte de fallo enviado exitosamente", "¡Enhorabuena! Tu reporte de fallo ha sido enviado correctamente. Agradecemos tu colaboración para mejorar nuestro sistema");
    }
}
