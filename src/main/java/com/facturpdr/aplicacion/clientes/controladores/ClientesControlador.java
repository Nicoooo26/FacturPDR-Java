package com.facturpdr.aplicacion.clientes.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.facturpdr.aplicacion.clientes.modelos.Cliente;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.*;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;

public class ClientesControlador {


    /**
     * TableView para mostrar la lista de clientes.
     */
    @FXML
    public TableView<Cliente> tablaClientes;

    /**
     * Columna para mostrar el DNI del cliente.
     */
    @FXML
    public TableColumn<Cliente, String> columnaDNI;

    /**
     * Columna para mostrar el nombre completo del cliente.
     */
    @FXML
    public TableColumn<Cliente, String> columnaNombreCompleto;

    /**
     * Columna para mostrar el teléfono del cliente.
     */
    @FXML
    public TableColumn<Cliente, Integer> columnaTelefono;

    /**
     * Columna para mostrar la cuenta del cliente.
     */
    @FXML
    public TableColumn<Cliente, String> columnaCuenta;

    /**
     * Campo de texto para buscar clientes.
     */
    @FXML
    public TextField textBuscar;

    /**
     * Botón para agregar un nuevo cliente.
     */
    @FXML
    public Button btnNuevo;

    /**
     * Botón para eliminar un cliente seleccionado.
     */
    @FXML
    public Button btnEliminar;

    /**
     * Botón para modificar un cliente seleccionado.
     */
    @FXML
    public Button btnModificar;

    /**
     * Lista observable de clientes.
     */
    ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    /**
     * Método que se ejecuta al inicializar el controlador.
     */
    public void initialize(){
        try {
            BDExtension.conectarse();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        columnaDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        columnaNombreCompleto.setCellValueFactory(new PropertyValueFactory<>("nombrecompleto"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("movil"));
        columnaCuenta.setCellValueFactory(new PropertyValueFactory<>("cuenta"));
        tablaClientes.setItems(clientes);

        FXMLLoader loader = new FXMLLoader((getClass().getResource("C:\\Users\\diurno\\Desktop\\PROJECT\\Aplicacion\\src\\main\\resources\\com\\facturpdr\\aplicacion\\escenas")));
        ClienteControlador controladora = loader.getController();

        tablaClientes.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
                if (clienteSeleccionado != null) {
                    String dni = clienteSeleccionado.getDNI();
                    try {
                        controladora.cargarDatos(dni);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    VentanaExtension ventana = VentanaExtension.obtenerInstancia();
                    ventana.cambiarEscena("clientes/cliente");
                }
            }
        });

        FilteredList<Cliente> clientesFiltrados = new FilteredList<>(clientes);

        textBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            clientesFiltrados.setPredicate(Busqueda -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Busqueda.getDNI().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Busqueda.getNombrecompleto().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Busqueda.getCuenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        tablaClientes.setItems(clientesFiltrados);

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Nuevo".
     */
    @FXML
    public void clickNuevo() {
       VentanaExtension ventana = VentanaExtension.obtenerInstancia();
       ventana.cambiarEscena("clientes/crear-cliente");

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Modificar".
     *
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    @FXML
    public void clickModificar() throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("com/facturpdr/aplicacion/escenas/clientes/modificar-cliente.fxml"));
        ModificarClienteControlador controladora = loader.getController();

        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String dni = columnaDNI.getCellData(selectedIndex);
            controladora.cargarDatos(dni);
            controladora.setDNI(dni);
            VentanaExtension ventana=VentanaExtension.obtenerInstancia();
            ventana.cambiarEscena("clientes/modificar-cliente");
        } else {
            AlertaUtilidad.advertencia("Cliente no seleccionado","Por favor, seleccione una fila en la tabla");
        }

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Eliminar".
     *
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    @FXML
    public void clickEliminar() throws SQLException {
        int selectedIndex = tablaClientes.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String dni = columnaDNI.getCellData(selectedIndex);
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;
            try {
                String query = "DELETE FROM CLIENTES WHERE DNI = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, dni);
                statement.executeUpdate();
                tablaClientes.refresh();
                Statement refreshStatement = conn.createStatement();
                refreshStatement.execute("COMMIT");
                actualizarDatos();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        else {
            AlertaUtilidad.advertencia("Cliente no seleccionado","Por favor, seleccione un cliente de la tabla");
        }

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Actualizar".
     */
    @FXML
    public void clickActualizar()  {
        actualizarDatos();
    }

    /**
     * Método para actualizar los datos en la tabla de clientes.
     */
    public void actualizarDatos() {
        clientes.clear();

        try {
            BDExtension.conectarse();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection conn = BDExtension.conexion;

        String selectSQL = "SELECT NOMBRE_COMPLETO, DNI, MOVIL, CUENTA FROM CLIENTES";

        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String DNI = rs.getString("DNI");
                String nombrecompleto = rs.getString("NOMBRE_COMPLETO");
                int movil = rs.getInt("MOVIL");
                String cuenta = rs.getString("CUENTA");
                Cliente cliente = new Cliente(nombrecompleto, DNI, cuenta, movil);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}