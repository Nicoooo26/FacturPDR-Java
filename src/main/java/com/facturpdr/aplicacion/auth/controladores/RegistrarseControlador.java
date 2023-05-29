package com.facturpdr.aplicacion.auth.controladores;

import com.facturpdr.aplicacion.auth.excepciones.CorreoElectronicoExistenteException;
import com.facturpdr.aplicacion.auth.excepciones.CrearUsuarioException;
import com.facturpdr.aplicacion.auth.excepciones.NombreUsuarioExistenteException;
import com.facturpdr.aplicacion.auth.servicios.AuthServicio;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 * Controlador para la vista de registro de usuarios.
 */
public class RegistrarseControlador {
    @FXML
    public TextField correoElectronico, nombreUsuario;

    @FXML
    public PasswordField contrasena, confirmarContrasena;

    @FXML
    public CheckBox politicasTerminos;

    private final AuthServicio authServicio = new AuthServicio();
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();

    /**
     * Maneja el evento del botón "Registrarse".
     * Realiza las validaciones necesarias y registra un nuevo usuario en el sistema.
     */
    @FXML
    public void manejarBotonRegistrarse() {
        // Validaciones de campos vacíos y formato de correo electrónico
        // ...

        try {
            authServicio.registrar(nombreUsuario.getText(), correoElectronico.getText(), contrasena.getText());
        } catch (CorreoElectronicoExistenteException e) {
            // Manejo de excepción: correo electrónico existente
            // ...
        } catch (NombreUsuarioExistenteException e) {
            // Manejo de excepción: nombre de usuario existente
            // ...
        } catch (CrearUsuarioException e) {
            // Manejo de excepción: error al crear usuario
            // ...
        }

        // Cambio de escena y mensaje de éxito
        // ...
    }

    /**
     * Maneja el evento para cambiar a la escena de inicio de sesión.
     */
    @FXML
    public void manejarEscenaIniciarSesion() {
        ventana.cambiarEscena("auth/iniciar-sesion");
    }
}
