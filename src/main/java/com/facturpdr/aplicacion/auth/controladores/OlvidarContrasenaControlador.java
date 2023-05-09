package com.facturpdr.aplicacion.auth.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class OlvidarContrasenaControlador {
    public TextField correoElectronico;

    @FXML
    public void manejarBotonRecuperar() {
        if (correoElectronico.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un correo electrónico", "Por favor, introduce tu correo electrónico.");
            return;
        }

        boolean correoValido = correoElectronico.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!correoValido) {
            AlertaUtilidad.error("El correo electrónico debe ser válido", "Por favor, introduce un correo electrónico válido.");
            return;
        }

        AlertaUtilidad.informacion("Correo de recuperación enviado", "Hemos enviado un correo de recuperación de contraseña al correo electrónico. Por favor, revisa tu bandeja de entrada y sigue los pasos indicados. ¡Gracias por utilizar nuestro servicio!");
    }

    @FXML
    public void manejarEscenaIniciarSesion() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("auth/iniciar-sesion");
    }
}
