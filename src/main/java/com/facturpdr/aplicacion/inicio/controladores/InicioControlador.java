package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class InicioControlador {

    @FXML public void manejarCerrarSesion(ActionEvent event) {
        Optional<ButtonType> cerrarsesion = AlertaUtilidad.mostrarAlertaConfirmacion("Cerrar Sesión ", null , "¿Estás seguro de que deseas cerrar sesión?",
                                            ButtonType.YES , ButtonType.NO);
    }
}
