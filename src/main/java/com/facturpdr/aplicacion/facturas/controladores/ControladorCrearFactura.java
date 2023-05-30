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
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ControladorCrearFactura {

    @FXML ChoiceBox<String> textTipoPieza;
    @FXML ChoiceBox<Integer> textTipoTamanno;
    @FXML ChoiceBox<String> listadoClientes ;
    @FXML ChoiceBox<String> textTipoMaterial;
    @FXML ChoiceBox<String> textTipoPintura;
    @FXML ChoiceBox<String> textTipoAluminio;
    @FXML ChoiceBox<String> listadoEmpleados;

    @FXML TextField textMatricula,textBastidor,textCantidad,textManoDeObra,textNotaInterna,textNotaExterna;
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
            String query2 = "SELECT NIF FROM EMPLEADOS";
            Statement statement2 = conn.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(query2);
            listadoEmpleados.getItems().clear();
            while (resultSet2.next()) {
                String nombreEmpleado = resultSet2.getString("NIF");
                listadoEmpleados.getItems().add(nombreEmpleado);
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
        String tipoPieza = textTipoPieza.getValue();
        Integer tipoTamanno = textTipoTamanno.getValue();
        String tipoMaterial = textTipoMaterial.getValue();
        String tipoPintura = textTipoPintura.getValue();
        String tipoAluminio = textTipoAluminio.getValue();
        String nombreCliente = listadoClientes.getValue();
        String nifEmpleado = listadoEmpleados.getValue();
        // Obtener la información de los TextField
        String matricula = textMatricula.getText();
        String bastidor = textBastidor.getText();
        String cantidad = textCantidad.getText();
        String manoDeObra = textManoDeObra.getText();
        String notaInterna = textNotaInterna.getText();
        String notaExterna = textNotaExterna.getText();

        // Obtener la información de DatePicker
        LocalDate localDate = textFecha.getValue();
        try {
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;

            // Obtener el id_cliente mediante una consulta a la tabla "clientes"
            String queryCliente = "SELECT id FROM clientes WHERE NOMBRE_COMPLETO = ?";
            PreparedStatement clienteStatement = conn.prepareStatement(queryCliente);
            clienteStatement.setString(1, nombreCliente);
            ResultSet clienteResult = clienteStatement.executeQuery();

            if (clienteResult.next()) {
                int idCliente = clienteResult.getInt("id");

                    java.sql.Date fecha = null;
                    if (localDate != null) {
                        fecha = java.sql.Date.valueOf(localDate);
                    }
                    // Insertar los datos en la tabla "FACTURAS"
                    String insertQuery = "INSERT INTO FACTURAS (id_cliente, NIF_empleado, coste_mano_obra, nota_interna, nota_externa, fecha_creacion, fecha_vencimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                    insertStatement.setInt(1, idCliente);
                    insertStatement.setString(2, nifEmpleado);
                    insertStatement.setDouble(3, Double.parseDouble(manoDeObra));
                    insertStatement.setString(4, notaInterna);
                    insertStatement.setString(5, notaExterna);
                    insertStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                    insertStatement.setObject(7, fecha);

                    insertStatement.executeUpdate();
                    insertStatement.close();

            }

            clienteResult.close();
            clienteStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) btnAnnadir.getScene().getWindow();
        stage.close();
    }
}