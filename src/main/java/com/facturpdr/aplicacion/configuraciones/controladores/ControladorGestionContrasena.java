package com.facturpdr.aplicacion.configuraciones.controladores;

import com.facturpdr.aplicacion.auth.utilidades.HashUtilidad;
import com.facturpdr.aplicacion.configuraciones.servicios.ConfiguracionServicio;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
public class ControladorGestionContrasena {

    private UsuarioRepositorio user = new UsuarioRepositorio();
    @FXML private PasswordField contrasena_actual , contrasena_nueva , contrasena_nueva2;

    private boolean contrasena_regex(String contrasena) {
        return contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)([A-Za-z\\d$@!%*?&]|[^ ]){8,}$");
    }
    private boolean comprueba_constrasenaactual()  {
        if ( HashUtilidad.sha256(contrasena_actual.toString()) != ConfiguracionServicio.obtenerAtributo("contrasena")) return false ;
        return true ;
    }

    private boolean contrasena_nueva() {
       if (!contrasena_regex(contrasena_nueva.getText()) || !contrasena_regex(contrasena_nueva2.getText())) return false;
       if (contrasena_nueva.toString() != contrasena_nueva2.toString()) return false ;
       return true;
    }

    @FXML public void GuardarCambios(ActionEvent event) {
        if (!comprueba_constrasenaactual()) {
            AlertaUtilidad.error("Mensaje de Error","La contraseña actual no es valida");
            return;
        }
        if(!contrasena_nueva()) {
            AlertaUtilidad.advertencia("Advertencia contraseñas", "Las contraseñas no son iguales o no cumplen los requisitos.");
            return;
        }

        if (user.cambiarContrasena(contrasena_nueva.toString(), PreferenciaUtilidad.obtenerIDUsuario())) {
            AlertaUtilidad.confirmacion("Mensaje de Confirmacion",null,"Su contraseña ha sido modificada correctamente\"");
        }
    }
}
