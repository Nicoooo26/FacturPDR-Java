package com.facturpdr.aplicacion.general.configuraciones;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControladorConfiguracion {

    @FXML
    private ComboBox<Label> temas;

    @FXML
    private void onTemaSelected(ActionEvent event) {
        Label selectedTema = temas.getSelectionModel().getSelectedItem();

    }
    public void initialize() {
    }
}
