package com.facturpdr.aplicacion.auth.controladores;

import com.facturpdr.aplicacion.auth.servicios.AuthServicio;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IniciarSesionControlador {

    public TextField correoElectronico;

    public PasswordField contrasena;

    @FXML
    public void manejarBotonAcceder(ActionEvent event) {
        AuthServicio authServicio = new AuthServicio();

        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

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

        boolean contresenaValido = contrasena.getText().matches("^(?=.*[A-Z](?=).*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\\d!@#$%^&*()_+]{8,}$");
        if (!contresenaValido) {
            AlertaUtilidad.error("La contraseña debe ser valida", "La contraseña debe tener al menos 8 caracteres, una mayúscula como mínimo, un número como mínimo.");
            return;
        }

        String token = authServicio.iniciarSesion(correoElectronico.getText(), contrasena.getText());
        if (token == null) {
            AlertaUtilidad.error("Error de autenticación", "El correo electronico o la contraseña no son validos, vuelve a intentarlo.");
            return;
        }

        ventana.cambiarEscena("inicio/inicio.fxml");
        ventana.cambiarTitulo("FacturPDR - Inicio");
    }

    @FXML
    public void manejarEscenaRegistrarse(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("auth/registrarse.fxml");
        ventana.cambiarTitulo("FacturPDR - Registrarse");
    }

    @FXML
    public void manejarEscenaOlvidarContrasena(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("auth/olvidar-contrasena.fxml");
        ventana.cambiarTitulo("FacturPDR - Olvidar contraseña");
    }
}
