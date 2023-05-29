package com.facturpdr.aplicacion.auth.controladores;

import com.facturpdr.aplicacion.auth.servicios.AuthServicio;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.servicios.SesionServicio;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador para la vista de inicio de sesión.
 */
public class IniciarSesionControlador {
    @FXML
    private TextField correoElectronico;

    @FXML
    private PasswordField contrasena;

    private final AuthServicio authServicio = new AuthServicio();
    private final SesionServicio sesionServicio = new SesionServicio();
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();

    /**
     * Maneja el evento del botón "Acceder".
     * Valida los campos de correo electrónico y contraseña, y realiza el inicio de sesión si son válidos.
     */
    @FXML
    public void manejarBotonAcceder() {
        // Validar el campo de correo electrónico
        if (correoElectronico.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un correo electrónico", "Por favor, introduce tu correo electrónico.");
            return;
        }

        boolean correoValido = correoElectronico.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!correoValido) {
            AlertaUtilidad.error("El correo electrónico debe ser válido", "Por favor, introduce un correo electrónico válido.");
            return;
        }

        // Validar el campo de contraseña
        if (contrasena.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir una contraseña", "Por favor, introduce una contraseña.");
            return;
        }

        boolean contrasenaValida = contrasena.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)([A-Za-z\\d$@!%*?&]|[^ ]){8,}$");
        if (!contrasenaValida) {
            AlertaUtilidad.error("La contraseña debe ser válida", "La contraseña debe tener al menos 8 caracteres, una mayúscula como mínimo, y un número como mínimo.");
            return;
        }

        // Iniciar sesión
        int IDUsuario = authServicio.iniciarSesion(correoElectronico.getText(), contrasena.getText());
        if (IDUsuario == -1) {
            AlertaUtilidad.error("Error de autenticación", "El correo electrónico o la contraseña no son válidos, vuelve a intentarlo.");
            return;
        }

        sesionServicio.iniciarSesion(IDUsuario);
    }

    /**
     * Maneja el evento para cambiar a la escena de registro.
     */
    @FXML
    public void manejarEscenaRegistrarse() {
        ventana.cambiarEscena("auth/registrarse");
    }
}
