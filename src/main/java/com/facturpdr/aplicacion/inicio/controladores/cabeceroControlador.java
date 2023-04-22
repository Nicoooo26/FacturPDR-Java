package com.facturpdr.aplicacion.inicio.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
public class cabeceroControlador implements Initializable {

    private static cabeceroControlador instancia = null;
    private static cabeceroControlador controlador = null;

    @FXML
    private HBox Cabecero ;
    @FXML
    private Button BotonSalir ;

    public HBox getCabecero() {
        return Cabecero;
    }

    public Button getBotonSalir() {
        return BotonSalir;
    }

    public void setControlador(cabeceroControlador Controlador) {
        this.controlador = Controlador;
    }

    public static cabeceroControlador getControlador() {
        getInstancia();
        return controlador;
    }

    public static cabeceroControlador getInstancia() {
        if (instancia == null) {
            instancia = new cabeceroControlador();
        }
        return instancia;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setControlador(this);
    }
}
