package com.facturpdr.aplicacion.auth.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;

public class RegistrarseControlador {

    @FXML
    public TextField correo;

    @FXML
    public PasswordField contrasena;

    @FXML
    public PasswordField confirmarContrasena;

    @FXML
    public CheckBox politicasTerminos;

    @FXML
    public void manejarBotonRegistrarse(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        if (correo.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un correo electrónico", "Por favor, introduce tu correo electrónico");
            return;
        }

        boolean correoValido = correo.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!correoValido) {
            AlertaUtilidad.error("El correo electrónico debe ser válido", "Por favor, introduce un correo electrónico válido.");
            return;
        }

        if (contrasena.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir una contraseña", "Por favor, introduce una contraseña.");
            return;
        }

        boolean contresenaValido = contrasena.getText().matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\\d!@#$%^&*()_+]{8,}$");
        if (!contresenaValido) {
            AlertaUtilidad.error("La contraseña debe ser valida", "La contraseña debe tener al menos 8 caracteres, una mayúscula como mínimo, un número como mínimo.");
            return;
        }

        if (!Objects.equals(contrasena.getText(), confirmarContrasena.getText())) {
            AlertaUtilidad.error("La contraseña y confirmar contraseña no coinciden", "La contraseña y la confirmación de la contraseña no coinciden.");
            return;
        }

        if (!politicasTerminos.isSelected()) {
            AlertaUtilidad.error("Debes aceptar los términos y condiciones del servicio", "Por favor, acepta los términos y condiciones del servicio para continuar.");
            return;
        }

        ventana.cambiarEscena("auth/iniciar-sesion.fxml");
        ventana.cambiarTitulo("FacturPDR - Iniciar Sesion");

        AlertaUtilidad.informacion("¡Bienvenido! Confirma tu correo electronico", "Te hemos enviado un correo electrónico de verificación para activar tu cuenta. Por favor, revisa tu bandeja de entrada y sigue los pasos indicados.");
    }

    @FXML
    public void manejarEscenaIniciarSesion(ActionEvent event) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("auth/iniciar-sesion.fxml");
        ventana.cambiarTitulo("FacturPDR - Iniciar Sesion");
    }
}
