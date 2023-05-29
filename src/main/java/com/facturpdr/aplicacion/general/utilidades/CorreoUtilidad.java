/**
 * El paquete `com.facturpdr.aplicacion.general.utilidades` contiene clases que proporcionan utilidades generales para la aplicación.
 */
package com.facturpdr.aplicacion.general.utilidades;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * La clase `CorreoUtilidad` proporciona métodos para enviar correos electrónicos utilizando la configuración de correo especificada.
 */
public class CorreoUtilidad {
    /**
     * Crea y devuelve una sesión de correo configurada con los parámetros especificados en el archivo de configuración.
     *
     * @return La sesión de correo creada.
     */
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

    /**
     * Envía un correo electrónico al destinatario especificado con el asunto y cuerpo proporcionados.
     *
     * @param destinatario La dirección de correo electrónico del destinatario.
     * @param asunto       El asunto del correo electrónico.
     * @param cuerpo       El cuerpo del correo electrónico.
     * @throws MessagingException Si ocurre un error durante el envío del correo electrónico.
     */
    public static void enviar(String destinatario, String asunto, String cuerpo) throws MessagingException {
        MimeMessage mensaje = new MimeMessage(crearSesion());
        mensaje.setFrom(ConfiguracionUtilidad.obtenerValor("correo.direccion"));
        mensaje.setRecipients(Message.RecipientType.TO, destinatario);
        mensaje.setSubject(asunto);
        mensaje.setContent(cuerpo, "text/html; charset=utf-8");

        Transport.send(mensaje);
    }
}
