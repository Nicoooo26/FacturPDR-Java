package com.facturpdr.aplicacion.facturas.controladores;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControladorCrearFactura {

    @FXML ChoiceBox<String> textTipoPieza = new ChoiceBox<>();
    @FXML ChoiceBox<Integer> textTipoTamanno = new ChoiceBox<>();
    @FXML ChoiceBox<String> listadoClientes ;

    @FXML TextField textMatricula, textBastidor;
    @FXML DatePicker textFecha ;

    @FXML
    public Button btnCancelar;

    @FXML
    public Button btnAnnadir;

    private void rellenapiezas() {
        // Obtener la lista de elementos del ChoiceBox
        ObservableList<String> items = textTipoPieza.getItems();

        // Añadir las piezas al ChoiceBox
        items.addAll("ADI", "PDI", "PTI", "MI", "ATI", "ADD", "PDD", "PTD", "MD", "ATP", "T", "C", "M");
    }

    private void rellenatamano() {
        ObservableList<Integer> items = textTipoTamanno.getItems();

        // Añadir los valores de tamaño en secuencia de 10 en 10 hasta 80
        for (int i = 10; i <= 80; i += 10) {
            items.add(i);
        }
    }

    public void initialize() throws SQLException {
        BDExtension.conectarse();
        Connection conn = BDExtension.conexion;
        rellenapiezas();
        rellenatamano();

        try {
            String query = "SELECT NOMBRE_COMPLETO FROM clientes";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            listadoClientes.getItems().clear();
            while (resultSet.next()) {
                String nombreCliente = resultSet.getString("NOMBRE_COMPLETO");
                listadoClientes.getItems().add(nombreCliente);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void clickCancelar(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    public void clickAnnadir(){
        Stage stage = (Stage) btnAnnadir.getScene().getWindow();
        stage.close();
    }
}