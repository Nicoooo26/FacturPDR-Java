package com.facturpdr.aplicacion.facturas.controladores;

import com.facturpdr.aplicacion.empleados.modelos.Empleado;
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
    public TableView<Empleado> tablaEmpleados;

    /**
     * Columna para mostrar el DNI del cliente.
     */
    @FXML
    public TableColumn<Empleado, String> columnaNIF;

    /**
     * Columna para mostrar el nombre completo del cliente.
     */
    @FXML
    public TableColumn<Empleado, String> columnaNombre;

    /**
     * Columna para mostrar el teléfono del cliente.
     */
    @FXML
    public TableColumn<Empleado, Integer> columnaTelefono;

    /**
     * Columna para mostrar la cuenta del cliente.
     */
    @FXML
    public TableColumn<Empleado, String> columnaApellidos;

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
     * Botón para modificar un cliente seleccionado.
     */
    @FXML
    public Button btnModificar;

    /**
     * Lista observable de clientes.
     */
    ObservableList<Empleado> empleados = FXCollections.observableArrayList();

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

        String selectSQL = "SELECT NIF,NOMBRE,APELLIDOS,TELEFONO FROM EMPLEADOS";
        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String NIF = rs.getString("NIF");
                String nombre = rs.getString("NOMBRE");
                String apellidos = rs.getString("APELLIDOS");
                int telefono = rs.getInt("TELEFONO");
                Empleado empleado = new Empleado(NIF, nombre, apellidos, telefono);
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        columnaNIF.setCellValueFactory(new PropertyValueFactory<>("NIF"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tablaEmpleados.setItems(empleados);

        FilteredList<Empleado> empleadosFiltrados = new FilteredList<>(empleados);

        buscador.textProperty().addListener((observable, oldValue, newValue) -> {
            empleadosFiltrados.setPredicate(Busqueda -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Busqueda.getNIF().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Busqueda.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Busqueda.getApellidos().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        tablaEmpleados.setItems(empleadosFiltrados);

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Nuevo".
     */
    @FXML
    public void clickAnnadir() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.ventanaEmergente("empleados/crear-empleado");

    }

    /**
     * Método que se ejecuta al hacer clic en el botón "Eliminar".
     *
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    @FXML
    public void clickEliminar() throws SQLException {
        int selectedIndex = tablaEmpleados.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String dni = columnaNIF.getCellData(selectedIndex);
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;
            try {
                String query = "DELETE FROM EMPLEADOS WHERE NIF = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, dni);
                statement.executeUpdate();
                tablaEmpleados.refresh();
                Statement refreshStatement = conn.createStatement();
                refreshStatement.execute("COMMIT");
                actualizarDatos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            AlertaUtilidad.advertencia("Empleado no seleccionado", "Por favor, seleccione un empleado de la tabla");
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
        empleados.clear();

        try {
            BDExtension.conectarse();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection conn = BDExtension.conexion;

        String selectSQL = "SELECT NIF,NOMBRE,APELLIDOS,TELEFONO FROM EMPLEADOS";

        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String NIF = rs.getString("NIF");
                String nombre = rs.getString("NOMBRE");
                String apellidos = rs.getString("APELLIDOS");
                int telefono = rs.getInt("TELEFONO");
                Empleado empleado = new Empleado(NIF, nombre, apellidos, telefono);
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}