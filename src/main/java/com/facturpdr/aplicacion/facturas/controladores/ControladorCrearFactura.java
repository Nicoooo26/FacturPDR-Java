package com.facturpdr.aplicacion.facturas.controladores;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCrearFactura implements Initializable {

    @FXML ChoiceBox<String> tipoPieza = new ChoiceBox<>();
    @FXML ChoiceBox<Integer> tipoTamano = new ChoiceBox<>();
    @FXML ChoiceBox<String> listadoClientes = new ChoiceBox<>();

    @FXML TextField matriculaCoche , NBastidor;
    @FXML DatePicker Fecha ;



    private void  muestraclientes() {
        ObservableList<String> items = tipoPieza.getItems();

    }
    private void rellenapiezas() {
        // Obtener la lista de elementos del ChoiceBox
        ObservableList<String> items = tipoPieza.getItems();

        // Añadir las piezas al ChoiceBox
        items.addAll("ADI", "PDI", "PTI", "MI", "ATI", "ADD", "PDD", "PTD", "MD", "ATP", "T", "C", "M");
    }

    private void rellenatamano() {
        ObservableList<Integer> items = tipoTamano.getItems();

        // Añadir los valores de tamaño en secuencia de 10 en 10 hasta 80
        for (int i = 10; i <= 80; i += 10) {
            items.add(i);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rellenapiezas();
        rellenatamano();
    }
}