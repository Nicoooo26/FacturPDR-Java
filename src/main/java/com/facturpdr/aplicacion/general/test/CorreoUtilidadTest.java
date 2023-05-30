package com.facturpdr.aplicacion.general.test;

import com.facturpdr.aplicacion.general.utilidades.CorreoUtilidad;
import org.junit.Test;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import static org.junit.Assert.*;

public class CorreoUtilidadTest {

    @Test(expected = MessagingException.class)
    public void enviar_CorreoInvalido_LanzarExcepcion() throws MessagingException {
        // Configurar un correo inválido
        String destinatario = "destinatario@example.com";
        String asunto = "Prueba de correo";
        String cuerpo = "Este es un correo de prueba.";

        // Ejecutar el método a probar
        CorreoUtilidad.enviar(destinatario, asunto, cuerpo);

        // La prueba pasa si se lanza una excepción MessagingException
    }
}
