
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import com.facturpdr.aplicacion.clientes.modelo.Cliente;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;


public class ClientesControlador implements Initializable{
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
        BDExtension.conectarse();
        Connection conn = BDExtension.conexion;

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
            ClienteControlador controladora = loader.getController();


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
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("clientes/crear-cliente");
    }

    @FXML
    public void clickModificar(ActionEvent event)  {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("clientes/modificar-cliente");
    }

    @FXML
    public void clickEliminar(ActionEvent event) {
        // Obtener el índice de la fila seleccionada
        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) { // Se ha seleccionado una fila
            // Obtener el DNI de la fila seleccionada (suponiendo que el DNI está en la primera columna)
            String dni = columnaDNI.getCellData(selectedIndex);
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;
            // Ejecutar la consulta DELETE
            try {
                String query = "DELETE FROM CLIENTES WHERE DNI = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, dni);
                statement.executeUpdate();

                // Eliminar la fila de la TableView

                // Eliminar el elemento de la lista
                listaEliminar.remove(selectedIndex);

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
    public void clickActualizar(ActionEvent event)  {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("clientes/clientes");
    }

}