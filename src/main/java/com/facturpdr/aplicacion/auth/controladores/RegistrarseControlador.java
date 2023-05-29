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

public class RegistrarseControlador {
    @FXML
    public TextField correoElectronico, nombreUsuario;

    @FXML
    public PasswordField contrasena, confirmarContrasena;

    @FXML
    public CheckBox politicasTerminos;

    private final AuthServicio authServicio = new AuthServicio();
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();

    @FXML
    public void manejarBotonRegistrarse() {
        if (correoElectronico.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un correo electrónico", "Por favor, introduce tu correo electrónico");
            return;
        }

        boolean correoElectronicoValido = correoElectronico.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!correoElectronicoValido) {
            AlertaUtilidad.error("El correo electrónico debe ser válido", "Por favor, introduce un correo electrónico válido.");
            return;
        }

        if (nombreUsuario.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un nombre de usuario", "Por favor, introduce el nombre de usuario");
            return;
        }

        boolean nombreUsuarioValido = nombreUsuario.getText().matches("^[a-zA-Z0-9_]{4,15}$");
        if (!nombreUsuarioValido) {
            AlertaUtilidad.error("El nombre de usuario debe ser válido", " Por favor, intenta con otro nombre de usuario que tenga entre 4 y 15 caracteres, y que esté compuesto solo por letras mayúsculas o minúsculas, números y guiones bajos.");
            return;
        }

        if (contrasena.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir una contraseña", "Por favor, introduce una contraseña.");
            return;
        }


        boolean contresenaValido = contrasena.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)([A-Za-z\\d$@!%*?&]|[^ ]){8,}$");
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

        try {
            authServicio.registrar(nombreUsuario.getText(), correoElectronico.getText(), contrasena.getText());
        } catch (CorreoElectronicoExistenteException e) {
            AlertaUtilidad.error("El correo electronico ya existe", "Lo sentimos, el correo electronico que ha ingresado ya está en uso. Por favor, intente recuperar su cuenta con la opcion de olvidar contraseña");
            return;
        } catch (NombreUsuarioExistenteException e) {
            AlertaUtilidad.error("El nombre de usuario ya existe", "Lo sentimos, el nombre de usuario que ha ingresado ya está en uso. Por favor, elija otro nombre de usuario.");
            return;
        } catch (CrearUsuarioException e) {
            AlertaUtilidad.error("Error al registrar el usuario", "Lo sentimos, se ha producido un error al registrar el usuario, vuelva a intentarlo");
            return;
        }

        ventana.cambiarEscena("auth/iniciar-sesion");
        AlertaUtilidad.informacion("¡Bienvenido a FacturPDR!", "Ahora podrás explorar todas las increíbles características y servicios que ofrecemos.");
    }

    @FXML
    public void manejarEscenaIniciarSesion() {
        ventana.cambiarEscena("auth/iniciar-sesion");
    }
}
