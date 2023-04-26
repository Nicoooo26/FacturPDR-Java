package com.facturpdr.aplicacion.clientes.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class clientesControlador {

    @FXML
    public TableView tablaClientes;
    @FXML
    public TableColumn columnaFoto;
    @FXML
    public TableColumn columnaDNI;
    @FXML
    public TableColumn columnaNombre;
    @FXML
    public TableColumn columnaTelefono;
    @FXML
    public TableColumn columnaCuenta;
    
    @FXML
    public TextField textBuscar;
    @FXML
    public Button btnNuevo;
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnModificar;

    @FXML
    public void clickNuevo(ActionEvent event) {

    }
    @FXML
    public void clickEliminar(ActionEvent event) {

    }
    @FXML
    public void clickModificar(ActionEvent event) {

    }
}
