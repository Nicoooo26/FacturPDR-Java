package com.facturpdr.aplicacion.facturas.controladores;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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



    private void  muestraclientes() {
        ObservableList<String> items = textTipoPieza.getItems();

    }
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
            // Consulta SQL para obtener los nombres de los clientes
            String query = "SELECT NOMBRE_COMPLETO FROM clientes";

            // Crear una declaración
            Statement statement = conn.createStatement();

            // Ejecutar la consulta y obtener los resultados
            ResultSet resultSet = statement.executeQuery(query);

            // Limpiar el ChoiceBox
            listadoClientes.getItems().clear();

            // Agregar los nombres de los clientes al ChoiceBox
            while (resultSet.next()) {
                String nombreCliente = resultSet.getString("NOMBRE_COMPLETO");
                listadoClientes.getItems().add(nombreCliente);
            }

            // Cerrar los recursos
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
}