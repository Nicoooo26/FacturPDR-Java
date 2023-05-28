package com.facturpdr.aplicacion.configuraciones.controladores;

import com.facturpdr.aplicacion.configuraciones.servicios.ConfiguracionServicio;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
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

    @FXML private TextField NombreDeUsuario , mail , fechacreacion , telefono;
    @FXML private Button Boton_Salir , Boton_Guardar , Boton_Modifica;

    public static boolean cambiosSinGuardar = false;

    private static ControladorInformacionBasica instancia;

    public static ControladorInformacionBasica obtenerInstancia() {
        return instancia;
    }
    public TextField getNombreDeUsuario() {
        return NombreDeUsuario;
    }
    public void novisibles_nomodificables() {
        NombreDeUsuario.setEditable(false);mail.setEditable(false);fechacreacion.setEditable(false);telefono.setEditable(false);
        Boton_Salir.setVisible(false);Boton_Guardar.setVisible(false);
        NombreDeUsuario.setStyle("-fx-background-color: transparent;");
        mail.setStyle("-fx-background-color: transparent;");
        telefono.setStyle("-fx-background-color: transparent;");
    }

    private void muestra_informacion_personal() {
        NombreDeUsuario.setText(ConfiguracionServicio.obtenerAtributo("nombreUsuario").toString());
        mail.setText(ConfiguracionServicio.obtenerAtributo("correoElectronico").toString());
        fechacreacion.setText(ConfiguracionServicio.obtenerAtributo("fechaCreacion").toString());
    }

    @FXML public void PulsaGuardaCambios(ActionEvent event) {

    }

    @FXML public void PulsaModificaDatos(ActionEvent event) {
        NombreDeUsuario.setEditable(true);mail.setEditable(true);telefono.setEditable(true);
        Boton_Salir.setVisible(true);Boton_Guardar.setVisible(true);
        NombreDeUsuario.setStyle("-fx-border-color: #9a9a9a; -fx-border-width: 2px;  -fx-shape: \"M30,30 L100,30 A70,70 0 0,1 100,100 L30,100 A70,70 0 0,1 30,30 Z\";");
        mail.setStyle("-fx-border-color: #9a9a9a; -fx-border-width: 2px;  -fx-shape: \"M30,30 L100,30 A70,70 0 0,1 100,100 L30,100 A70,70 0 0,1 30,30 Z\";");
        telefono.setStyle("-fx-border-color: #9a9a9a; -fx-border-width: 2px; -fx-shape: \"M30,30 L100,30 A70,70 0 0,1 100,100 L30,100 A70,70 0 0,1 30,30 Z\";");
    }

    @FXML public void PulsaBotonSalir() {
        novisibles_nomodificables();
    }

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
        NombreDeUsuario.textProperty().addListener((observable, oldValue, newValue) -> cambiosSinGuardar = true);
        mail.textProperty().addListener((observable, oldValue, newValue) -> cambiosSinGuardar = true);
    }
}
