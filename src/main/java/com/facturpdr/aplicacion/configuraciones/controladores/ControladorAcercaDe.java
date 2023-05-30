package com.facturpdr.aplicacion.configuraciones.controladores;

import com.facturpdr.aplicacion.configuraciones.servicios.ConfiguracionServicio;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.general.utilidades.CorreoUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;

public class ControladorAcercaDe {
    @FXML
    public TextField asunto;
    @FXML
    public TextArea cuerpo;

    /**
     * Manejador de eventos para el clic del botón "enviar_reporte".
     * Envía un informe por correo electrónico.
     *
     * @param event El evento de acción.
     * @throws MessagingException Si se produce un error al enviar el correo electrónico.
     */
    @FXML
    public void enviarReporte(ActionEvent event) throws MessagingException {
        if (cuerpo.getText().isEmpty()) {
            AlertaUtilidad.advertencia("Advertencia Correo.", "El mensaje del correo está vacío.");
        } else {
            CorreoUtilidad.enviar(ConfiguracionServicio.obtenerAtributo("correoElectronico").toString(), "No contestar este mensaje.", "Mensaje recibidio : " + cuerpo.getText().toString() + "\nHemos recibido su mensaje y agradecemos su interés en contactar con nosotros en FACTUR PD. Lamentamos sinceramente cualquier inconveniente o molestia que haya experimentado.\n" +
                    "\n" +
                    "Nos comprometemos a investigar a fondo su caso y a tomar las medidas necesarias para abordar cualquier problema que haya surgido. Nuestro equipo de atención al cliente se pondrá en contacto con usted a la brevedad posible para brindarle una solución adecuada.\n" +
                    "\n" +
                    "Agradecemos su paciencia y comprensión mientras trabajamos para resolver este asunto. Su satisfacción es nuestra prioridad y haremos todo lo posible para asegurarnos de que su experiencia con nosotros sea satisfactoria.\n" +
                    "\n" +
                    "Atentamente,\n" +
                    "El equipo de FACTUR PDR");
        }
    }
}