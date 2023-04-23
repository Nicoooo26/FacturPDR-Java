package com.facturpdr.aplicacion.configuracion.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class configuracionControlador {

    @FXML
    private ComboBox<Label> temas;

    @FXML
    private void onTemaSelected(ActionEvent event) {
        Label selectedTema = temas.getSelectionModel().getSelectedItem();

    }
    public void initialize() {
    }
}
