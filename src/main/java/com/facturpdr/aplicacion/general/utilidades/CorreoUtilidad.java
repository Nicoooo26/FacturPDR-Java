package com.facturpdr.aplicacion.general.utilidades;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CorreoUtilidad {
    private static Session crearSesion() {
        Properties parametros = System.getProperties();
        parametros.put("mail.smtp.host", ConfiguracionUtilidad.obtenerValor("correo.direccion"));
        parametros.put("mail.smtp.port", ConfiguracionUtilidad.obtenerValor("correo.puerto"));
        parametros.put("mail.smtp.ssl.enable", "true");
        parametros.put("mail.smtp.auth", "true");

        return Session.getInstance(parametros, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfiguracionUtilidad.obtenerValor("correo.usuario"), ConfiguracionUtilidad.obtenerValor("correo.contrasena"));
            }
        });
    }

    public static void enviar(String destinatario, String asunto, String cuerpo) throws MessagingException {
        MimeMessage mensaje = new MimeMessage(crearSesion());
        mensaje.setFrom(ConfiguracionUtilidad.obtenerValor("correo.direccion"));
        mensaje.setRecipients(Message.RecipientType.TO, destinatario);
        mensaje.setSubject(asunto);
        mensaje.setContent(cuerpo, "text/html; charset=utf-8");

        Transport.send(mensaje);
    }
}
