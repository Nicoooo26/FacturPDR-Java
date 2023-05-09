package com.facturpdr.aplicacion.clientes.controladores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.facturpdr.aplicacion.auth.modelos.Cliente;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class clientesControlador implements Initializable{
    @FXML
    public TableView<Cliente> tablaClientes;
    @FXML
    public TableColumn<Cliente, String> columnaDNI;
    @FXML
    public TableColumn <Cliente,String>columnaNombreCompleto;
    @FXML
    public TableColumn <Cliente,Integer>columnaTelefono;
    @FXML
    public TableColumn <Cliente,String>columnaCuenta;

    @FXML
    public TextField textBuscar;
    @FXML
    public Button btnNuevo;
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnModificar;

    ObservableList<Cliente> clientes =FXCollections.observableArrayList();
    List<Cliente> listaEliminar = new ArrayList<>(clientes);
    @Override
    public void initialize(URL location, ResourceBundle resources){

        // Conexión a la base de datos y consulta SQL
        BDConfiguracion
        Connection conn = SQLConected.conn;

        String selectSQL="SELECT NOMBRE_COMPLETO,DNI,MOVIL,CUENTA FROM CLIENTES";

        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String DNI = rs.getString("DNI");
                String nombrecompleto = rs.getString("NOMBRE_COMPLETO");
                int movil = rs.getInt("MOVIL");
                String cuenta = rs.getString("CUENTA");
                Cliente cliente = new Cliente(nombrecompleto,DNI,cuenta,movil);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Configurar el TableView y asignar la lista de clientes
        columnaDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        columnaNombreCompleto.setCellValueFactory(new PropertyValueFactory<>("nombrecompleto"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("movil"));
        columnaCuenta.setCellValueFactory(new PropertyValueFactory<>("cuenta"));
        tablaClientes.setItems(clientes);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/cliente.fxml"));
            Parent root = loader.load();
            clienteControlador controladora = loader.getController();


            tablaClientes.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) {
                    Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
                    if (clienteSeleccionado != null) {
                        String dni = clienteSeleccionado.getDNI();
                        controladora.cargarDatos(dni);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        Stage currentStage = (Stage) tablaClientes.getScene().getWindow();
                        currentStage.close();
                    }
                }
            });

            // código para cargar los datos en la tabla
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Crear FilteredList a partir de la lista observable de productos
        FilteredList<Cliente> clientesFiltrados = new FilteredList<>(clientes);

        textBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            clientesFiltrados.setPredicate(tuObjeto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (tuObjeto.getDNI().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (tuObjeto.getNombrecompleto().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (tuObjeto.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        // Establecer FilteredList como el items del TableView
        tablaClientes.setItems(clientesFiltrados);

    }

    @FXML
    public void clickNuevo(ActionEvent event) throws IOException{
        // Cargar la nueva pantalla FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/crear_cliente.fxml"));
        Parent root = loader.load();

        // Crear una nueva escena y asignarla al escenario
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        // Mostrar la nueva escena encima de la escena actual
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }
    // Event Listener on Button.onAction
    @FXML
    public void clickModificar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/modificar_cliente.fxml"));
        Parent root = loader.load();
        modificarClienteControlador controladora = loader.getController();

        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Obtener el valor de DNI correspondiente a la fila seleccionada
            String dni = columnaDNI.getCellData(selectedIndex);
            controladora.cargarDatos(dni);
            controladora.setDNI(dni);
            // Abrir la pantalla de modificación

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) tablaClientes.getScene().getWindow();
            currentStage.close();
        } else {
            // Mostrar un mensaje de error si no se selecciona ninguna fila en la TableView
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se ha seleccionado ninguna fila");
            alert.setContentText("Por favor, seleccione una fila en la tabla");
            alert.showAndWait();
        }

    }
    // Event Listener on Button.onAction
    @FXML
    public void clickEliminar(ActionEvent event) {
        // Obtener el índice de la fila seleccionada
        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) { // Se ha seleccionado una fila
            // Obtener el DNI de la fila seleccionada (suponiendo que el DNI está en la primera columna)
            String dni = columnaDNI.getCellData(selectedIndex);
            SQLConected.conectar();
            Connection conn = SQLConected.conn;
            // Ejecutar la consulta DELETE
            try {
                String query = "DELETE FROM CLIENTES WHERE DNI = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, dni);
                statement.executeUpdate();


                // Actualizar la tabla manualmente
                tablaClientes.setItems(FXCollections.observableArrayList(listaEliminar));
                tablaClientes.refresh();
                // Actualizar la tabla en SQL Developer
                Statement refreshStatement = conn.createStatement();
                refreshStatement.execute("COMMIT");
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        else {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Cliente no seleccionado");
            alerta.setContentText("Por favor, seleccione un cliente de la tabla");
            alerta.showAndWait();
        }

    }
    @FXML
    public void clickActualizar(ActionEvent event) throws IOException, SQLException {
        // Reiniciar la pantalla
        Stage stage = (Stage) tablaClientes.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../vistas/clientes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Método que se encarga de capturar el evento de doble clic en una fila del tableview


}
