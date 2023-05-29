package com.facturpdr.aplicacion.configuraciones.controladores;

import com.facturpdr.aplicacion.configuraciones.servicios.ConfiguracionServicio;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarCorreoElectronicoException;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarNombreUsuarioException;
import com.facturpdr.aplicacion.usuarios.servicios.UsuarioServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorInformacionBasica implements Initializable {
    private UsuarioServicio usuarioServicio = new UsuarioServicio();

    @FXML private TextField nombreUsuario, correoElectronico, fechaCreacion, telefono;
    @FXML private Button botonSalir , botonGuardar , botonModifica;

    public static boolean cambiosSinGuardar = false;

    private static ControladorInformacionBasica instancia;

    public static ControladorInformacionBasica obtenerInstancia() {
        return instancia;
    }

    /**
     * Obtiene el campo de texto del nombre de usuario.
     * @return El campo de texto del nombre de usuario.
     */
    public TextField getNombreDeUsuario() {
        return nombreUsuario;
    }

    /**
     * Hace que los campos de texto no sean visibles ni modificables.
     */
    public void novisibles_nomodificables() {
        nombreUsuario.setEditable(false);
        correoElectronico.setEditable(false);
        fechaCreacion.setEditable(false);
        telefono.setEditable(false);
        botonSalir.setVisible(false);
        botonGuardar.setVisible(false);
        nombreUsuario.setStyle("-fx-background-color: transparent;");
        correoElectronico.setStyle("-fx-background-color: transparent;");
        telefono.setStyle("-fx-background-color: transparent;");
    }

    private void muestra_informacion_personal() {
        nombreUsuario.setText(ConfiguracionServicio.obtenerAtributo("nombreUsuario").toString());
        correoElectronico.setText(ConfiguracionServicio.obtenerAtributo("correoElectronico").toString());
        fechaCreacion.setText(ConfiguracionServicio.obtenerAtributo("fechaCreacion").toString());
    }

    /**
     * Manejador de eventos para el clic del botón "PulsaGuardaCambios".
     * Guarda los cambios realizados en los campos de texto.
     */
    @FXML
    public void PulsaGuardaCambios() {
        int IDUsuario = PreferenciaUtilidad.obtenerIDUsuario();

        if (correoElectronico.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un correo electrónico", "Por favor, introduce tu correo electrónico");
            return;
        }

        boolean correoElectronicoValido = correoElectronico.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!correoElectronicoValido) {
            AlertaUtilidad.error("El correo electrónico debe ser válido", "Por favor, introduce un correo electrónico válido.");
            return;
        }

        try {
            usuarioServicio.cambiarCorreoElectronico(correoElectronico.getText(), IDUsuario);
        } catch (CambiarCorreoElectronicoException e) {
            AlertaUtilidad.error("Error al cambiar el correo electrónico", "Por favor, intenta con otro correo electrónico válido.");
            return;
        }

        if (nombreUsuario.getText().isEmpty()) {
            AlertaUtilidad.error("Debes introducir un nombre de usuario", "Por favor, introduce el nombre de usuario");
            return;
        }

        boolean nombreUsuarioValido = nombreUsuario.getText().matches("^[a-zA-Z0-9_]{4,15}$");
        if (!nombreUsuarioValido) {
            AlertaUtilidad.error("El nombre de usuario debe ser válido", "Por favor, intenta con otro nombre de usuario que tenga entre 4 y 15 caracteres, y que esté compuesto solo por letras mayúsculas o minúsculas, números y guiones bajos.");
            return;
        }

        try {
            usuarioServicio.cambiarNombreUsuario(nombreUsuario.getText(), IDUsuario);
        } catch (CambiarNombreUsuarioException e) {
            AlertaUtilidad.error("Error al cambiar el nombre de usuario", "Por favor, intenta con otro nombre de usuario que tenga entre 4 y 15 caracteres, y que esté compuesto solo por letras mayúsculas o minúsculas, números y guiones bajos.");
            return;
        }

        AlertaUtilidad.informacion("¡Cambios guardados con éxito!", "Los cambios han sido guardados exitosamente.");
    }

    /**
     * Manejador de eventos para el clic del botón "PulsaModificaDatos".
     * Permite modificar los datos personales.
     */
    @FXML
    public void PulsaModificaDatos() {
        nombreUsuario.setEditable(true);
        correoElectronico.setEditable(true);
        telefono.setEditable(true);
        botonSalir.setVisible(true);
        botonGuardar.setVisible(true);
        nombreUsuario.setStyle("-fx-border-color: #9a9a9a; -fx-border-width: 2px;  -fx-shape: \"M30,30 L100,30 A70,70 0 0,1 100,100 L30,100 A70,70 0 0,1 30,30 Z\";");
        correoElectronico.setStyle("-fx-border-color: #9a9a9a; -fx-border-width: 2px;  -fx-shape: \"M30,30 L100,30 A70,70 0 0,1 100,100 L30,100 A70,70 0 0,1 30,30 Z\";");
        telefono.setStyle("-fx-border-color: #9a9a9a; -fx-border-width: 2px; -fx-shape: \"M30,30 L100,30 A70,70 0 0,1 100,100 L30,100 A70,70 0 0,1 30,30 Z\";");
    }

    /**
     * Manejador de eventos para el clic del botón "PulsaBotonSalir".
     * Sale del modo de modificación sin guardar los cambios.
     */
    @FXML
    public void PulsaBotonSalir() {
        novisibles_nomodificables();
    }

    /**
     * Muestra un mensaje de confirmación cuando hay cambios sin guardar y se intenta salir de la página.
     * @return true si se confirma la salida, false en caso contrario.
     */
    public boolean mostrarMensajeCambiosSinGuardar() {
        Optional<ButtonType> resultado = AlertaUtilidad.confirmacion("Confirmar salida", "¿Estás seguro que quieres salir?", "Guardar y salir o salir sin guardar",
                new ButtonType("Guardar y salir", ButtonBar.ButtonData.YES),
                new ButtonType("Salir sin guardar" , ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE)
        );

        if (resultado.isPresent()) {
            ButtonType botonSeleccionado = resultado.get();
            if (botonSeleccionado.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                cambiosSinGuardar = false;
                PulsaGuardaCambios();
                muestra_informacion_personal();
                return true;
            } else if (botonSeleccionado.getButtonData() == ButtonBar.ButtonData.YES) {
                cambiosSinGuardar = false;
                muestra_informacion_personal();
                return true;
            } else if (botonSeleccionado.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                cambiosSinGuardar = false;
                muestra_informacion_personal();
                return false;
            }
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancia = this;
        novisibles_nomodificables();
        muestra_informacion_personal();
        nombreUsuario.textProperty().addListener((observable, oldValue, newValue) -> cambiosSinGuardar = true);
        correoElectronico.textProperty().addListener((observable, oldValue, newValue) -> cambiosSinGuardar = true);
    }
}