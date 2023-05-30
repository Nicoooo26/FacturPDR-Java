package com.facturpdr.aplicacion.facturas.controladores;

import com.facturpdr.aplicacion.facturas.modelo.Factura;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class ControladorFacturas {
    /**
     * TableView para mostrar la lista de clientes.
     */
    @FXML
    public TableView<Factura> tablaFacturas;

    /**
     * Columna para mostrar el DNI del cliente.
     */
    @FXML
    public TableColumn<Factura, String> columnaNIF;

    /**
     * Columna para mostrar el nombre completo del cliente.
     */
    @FXML
    public TableColumn<Factura, String> columnaMatricula;

    /**
     * Columna para mostrar el teléfono del cliente.
     */
    @FXML
    public TableColumn<Factura, Integer> columnaPrecio;

    /**
     * Columna para mostrar la cuenta del cliente.
     */
    @FXML
    public TableColumn<Factura, Integer> columnaIDCliente;

    /**
     * Campo de texto para buscar clientes.
     */
    @FXML
    public TextField buscador;

    /**
     * Botón para agregar un nuevo cliente.
     */
    @FXML
    public Button btnAnnadir;

    /**
     * Botón para eliminar un cliente seleccionado.
     */
    @FXML
    public Button btnEliminar;


    /**
     * Lista observable de clientes.
     */
    ObservableList<Factura> facturas = FXCollections.observableArrayList();

    /**
     * Método que se ejecuta al inicializar el controlador.
     */
    public void initialize() {
        try {
            BDExtension.conectarse();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection conn = BDExtension.conexion;

        String selectSQL = "SELECT NIF_EMPLEADO,PRECIOTOTAL,ID_CLIENTE,MATRICULA FROM FACTURAS";
        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String NIF = rs.getString("NIF_EMPLEADO");
                int preciototal = rs.getInt("PRECIOTOTAL");
                int idCliente = rs.getInt("ID_CLIENTE");
                String matricula = rs.getString("MATRICULA");
                Factura factura = new Factura(NIF,preciototal,idCliente,matricula);
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        columnaNIF.setCellValueFactory(new PropertyValueFactory<>("nifEmpleado"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioTotal"));
        columnaIDCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tablaFacturas.setItems(facturas);

        FilteredList<Factura> facturasFiltrados = new FilteredList<>(facturas);

        buscador.textProperty().addListener((observable, oldValue, newValue) -> {
            facturasFiltrados.setPredicate(Busqueda -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Busqueda.getMatricula().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Busqueda.getNifEmpleado().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        tablaFacturas.setItems(facturasFiltrados);

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Nuevo".
     */
    @FXML
    public void clickAnnadir() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.ventanaEmergente("facturas/crear-factura");

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Eliminar".
     *
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    @FXML
    public void clickEliminar() throws SQLException {
        int selectedIndex = tablaFacturas.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String dni = columnaNIF.getCellData(selectedIndex);
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;
            try {
                String query = "DELETE FROM FACTURAS WHERE NIF_EMPLEADO = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, dni);
                statement.executeUpdate();
                tablaFacturas.refresh();
                Statement refreshStatement = conn.createStatement();
                refreshStatement.execute("COMMIT");
                actualizarDatos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            AlertaUtilidad.advertencia("Factura no seleccionado", "Por favor, seleccione una factura de la tabla");
        }

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Actualizar".
     */
    @FXML
    public void clickActualizar() {
        actualizarDatos();
    }

    /**
     * Método para actualizar los datos en la tabla de clientes.
     */
    public void actualizarDatos() {
        facturas.clear();
        try {
            BDExtension.conectarse();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection conn = BDExtension.conexion;

        String selectSQL = "SELECT NIF_EMPLEADO,PRECIOTOTAL,ID_CLIENTE,MATRICULA FROM FACTURAS";
        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String NIF = rs.getString("NIF_EMPLEADO");
                int preciototal = rs.getInt("PRECIOTOTAL");
                int idCliente = rs.getInt("ID_CLIENTE");
                String matricula = rs.getString("MATRICULA");
                Factura factura = new Factura(NIF, preciototal, idCliente, matricula);
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}