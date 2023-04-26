package com.facturpdr.aplicacion.general.configuraciones.utilidades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Font;

public class choiceBoxUtil {
    public static void obtieneFuentes (ChoiceBox<String> fuenteSelecionado) {
        // Obtener todas las familias de fuentes disponibles en el sistema
        ObservableList<String> fontFamilies = FXCollections.observableArrayList(Font.getFamilies());
        // Añadir las familias de fuentes al ChoiceBox
        fuenteSelecionado.setItems(fontFamilies);
    }
    public static void obtieneSize(ChoiceBox<String> tamanoSelecionado) {
        ObservableList<String> tamanos = FXCollections.observableArrayList();
        for (int i = 8; i <= 72; i++) {
            tamanos.add(Integer.toString(i));
        }
        tamanoSelecionado.setItems(tamanos);
    }
    public static void obtieneTemas (ChoiceBox<String> theme) {
    // Crea una lista observable con los valores de tu ChoiceBox
    ObservableList<String> items = FXCollections.observableArrayList("Default", "Light", "Dark");

    // Agrega la lista observable al ChoiceBox
    theme.setItems(items);
    }

    public static void istrue (ChoiceBox<String> comprueba ) {
        // Crea una lista observable con los valores "Sí" y "No"
        ObservableList<String> items = FXCollections.observableArrayList("Sí", "No");

        // Agrega la lista observable al ChoiceBox
        comprueba.setItems(items);
    }


}

