package com.facturpdr.aplicacion.auth.controladores;

import com.facturpdr.aplicacion.auth.excepciones.NoVerificadoException;
import com.facturpdr.aplicacion.auth.servicios.AuthServicio;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.servicios.SesionServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IniciarSesionControlador {

    public TextField correoElectronico;

    public PasswordField contrasena;

    @FXML
    public void manejarBotonAcceder() {
        AuthServicio authServicio = new AuthServicio();
        SesionServicio sesionServicio = new SesionServicio();

        if (correoElectronico.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un correo electrónico", "Por favor, introduce tu correo electrónico.");
            return;
        }

        boolean correoValido = correoElectronico.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!correoValido) {
            AlertaUtilidad.error("El correo electrónico debe ser válido", "Por favor, introduce un correo electrónico válido.");
            return;
        }

        if (contrasena.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir una contraseña", "Por favor, introduce una contraseña.");
            return;
        }

        /*
        boolean contresenaValido = contrasena.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)([A-Za-z\\d$@!%*?&]|[^ ]){8,}$");
        if (!contresenaValido) {
            AlertaUtilidad.error("La contraseña debe ser valida", "La contraseña debe tener al menos 8 caracteres, una mayúscula como mínimo, un número como mínimo.");
            return;
        }
        */

        try {
            String token = authServicio.iniciarSesion(correoElectronico.getText(), contrasena.getText());
            if (token == null) {
                AlertaUtilidad.error("Error de autenticación", "El correo electronico o la contraseña no son validos, vuelve a intentarlo.");
                return;
            }

            sesionServicio.iniciarSesion(token);
        } catch (NoVerificadoException e) {
            AlertaUtilidad.advertencia("Verificación de cuenta pendiente", "Por favor, verifica tu cuenta para completar esta acción. Hemos enviado un correo electrónico a tu cuenta con instrucciones sobre cómo hacerlo. ¡Gracias por tu comprensión!");
        }
    }

    @FXML
    public void manejarEscenaRegistrarse(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("auth/registrarse");
    }

    @FXML
    public void manejarEscenaOlvidarContrasena(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("auth/olvidar-contrasena");
    }
}
