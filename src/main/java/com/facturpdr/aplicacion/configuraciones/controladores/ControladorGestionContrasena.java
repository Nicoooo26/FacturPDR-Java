package com.facturpdr.aplicacion.configuraciones.controladores;

import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.general.utilidades.CorreoUtilidad;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarContrasenaException;
import com.facturpdr.aplicacion.usuarios.excepciones.ContrasenaIgualException;
import com.facturpdr.aplicacion.usuarios.servicios.UsuarioServicio;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.util.Objects;

public class ControladorGestionContrasena {
    @FXML
    private PasswordField contrasenaNueva;

    @FXML
    private PasswordField confirmarContrasenaNueva;

    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    /**
     * Manejador de eventos para el clic del botón "manejarCambiarContrasena".
     * Cambia la contraseña del usuario.
     */
    @FXML
    public void manejarCambiarContrasena() {
        int IDUsuario = PreferenciaUtilidad.obtenerIDUsuario();

        boolean contrasenaNuevaValida = contrasenaNueva.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)([A-Za-z\\d$@!%*?&]|[^ ]){8,}$");
        if (!contrasenaNuevaValida) {
            AlertaUtilidad.error("La contraseña debe ser válida", "La contraseña debe tener al menos 8 caracteres, una mayúscula como mínimo, un número como mínimo.");
            return;
        }

        if (!Objects.equals(contrasenaNueva.getText(), confirmarContrasenaNueva.getText())) {
            AlertaUtilidad.error("La contraseña y confirmar contraseña no coinciden", "La contraseña y la confirmación de la contraseña no coinciden.");
            return;
        }

        try {
            usuarioServicio.cambiarContrasena(contrasenaNueva.getText(), IDUsuario);
        } catch (CambiarContrasenaException e) {
            AlertaUtilidad.error("Error al cambiar la contraseña", "Lo sentimos, se ha producido un error al cambiar la contraseña, vuelva a intentarlo.");
            return;
        } catch (ContrasenaIgualException e) {
            AlertaUtilidad.error("Contraseña no modificada", "La contraseña nueva es igual a la actual");
            return;
        }

        AlertaUtilidad.informacion("¡Contraseña cambiada con éxito!", "¡Contraseña cambiada con éxito! Disfruta de mayor seguridad.");
    }

}
