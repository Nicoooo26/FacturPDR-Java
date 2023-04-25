package com.facturpdr.aplicacion.general.configuraciones;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Font;

public class ControladorConfiguracion {

    @FXML private ChoiceBox<String> fuenteSelecionado;

    /*@FXML
    private void onTemaSelected(ActionEvent event) {
        Label selectedTema = temas.getSelectionModel().getSelectedItem();

    }*/

        @FXML
        public void initialize() {
            ObservableList<String> families = FXCollections.observableArrayList(Font.getFamilies());
            fuenteSelecionado.setItems(families);
        }

        // ...
    }
