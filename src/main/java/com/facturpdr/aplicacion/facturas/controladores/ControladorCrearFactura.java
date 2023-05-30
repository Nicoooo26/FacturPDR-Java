package com.facturpdr.aplicacion.facturas.controladores;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
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

    @FXML
    private ChoiceBox<String> textTipoPieza;
    @FXML
    private ChoiceBox<Integer> textTipoTamanno;
    @FXML
    private ChoiceBox<String> listadoClientes;

    @FXML
    private ChoiceBox<String> listadoEmpleados;
    @FXML
    private TextField textTipoMaterial;
    @FXML
    private TextField textTipoPintura;
    @FXML
    private TextField textTipoAluminio;



    @FXML
    private TextField textMatricula;
    @FXML
    private TextField textPrecio;
    @FXML
    private TextField textNotaExterna;
    @FXML
    private TextField textNotaInterna;
    @FXML
    private TextField textCantidad;
    @FXML
    private TextField textManoDeObra;

    @FXML
    private DatePicker textFecha;

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAnnadir;

    private void rellenapiezas() {
        // Obtener la lista de elementos del ChoiceBox
        ObservableList<String> items = textTipoPieza.getItems();

        // Añadir las piezas al ChoiceBox
        items.addAll("ADI", "PDI", "PTI", "MI", "ATI", "ADD", "PDD", "PTD", "MD", "ATP", "T", "C", "M");
    }

    private void rellenatamano() {
        ObservableList<Integer> items = textTipoTamanno.getItems();

        // Añadir los valores de tamaño en secuencia de 10 en 10 hasta 80
        for (int i = 10; i <= 40; i += 10) {
            items.add(i);
        }
    }

    public void initialize() {
        try {
            BDExtension.conectarse();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @FXML
    public void clickCancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void clickAnnadir() throws SQLException {
        if (datosValidos()) {
            String tipoPieza = textTipoPieza.getValue();
            Integer tipoTamanno = textTipoTamanno.getValue();
            String tipoMaterial = textTipoMaterial.getText();
            String tipoPintura = textTipoPintura.getText();
            String tipoAluminio = textTipoAluminio.getText();
            String nombreCliente = listadoClientes.getValue();
            String nifEmpleado = listadoEmpleados.getValue();
            String matricula = textMatricula.getText();
            String precio = textPrecio.getText();
            String cantidad = textCantidad.getText();
            String manoDeObra = textManoDeObra.getText();
            String notaInterna = textNotaInterna.getText();
            String notaExterna = textNotaExterna.getText();
            LocalDate localDate = textFecha.getValue();
            java.util.Date fechaActual = new java.util.Date();
            java.sql.Date fechaCreacion = new java.sql.Date(fechaActual.getTime());
            int valorPintura = tipoPintura.equalsIgnoreCase("SI") ? 1 : 0;
            int valorAluminio = tipoAluminio.equalsIgnoreCase("SI") ? 1 : 0;
            int valorMaterial = tipoMaterial.equalsIgnoreCase("SI") ? 1 : 0;
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;

            // Realizar la consulta para obtener el ID del cliente
            String query = "SELECT id FROM clientes WHERE nombre_completo = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreCliente);
            ResultSet rs = stmt.executeQuery();

            // Obtener el ID del cliente
            int idCliente = 0;
            if (rs.next()) {
                idCliente = rs.getInt("id");
            } else {
                System.out.println("No se encontró ningún cliente con el nombre completo especificado.");
                return;
            }

            String insertSQL = "INSERT INTO FACTURAS (id_cliente, NIF_empleado,matricula, coste_mano_obra, nota_interna, nota_externa, fecha_creacion, fecha_vencimiento, tipo, material, aluminio, pintura, tamano, cantidad, precio_unitario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            java.sql.Date fecha = null;
            if (localDate != null) {
                fecha = java.sql.Date.valueOf(localDate);
            }

            stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, idCliente);
            stmt.setString(2, nifEmpleado);
            stmt.setString(3, matricula);
            stmt.setInt(4, Integer.parseInt(manoDeObra));
            stmt.setString(5, notaInterna);
            stmt.setString(6, notaExterna);
            stmt.setDate(7, fechaCreacion);
            stmt.setDate(8, fecha); // Aquí debes proporcionar la fecha de vencimiento correspondiente
            stmt.setString(9, tipoPieza);
            stmt.setInt(10, valorMaterial);
            stmt.setInt(11, valorAluminio);
            stmt.setInt(12, valorPintura);
            stmt.setInt(13, tipoTamanno);
            stmt.setString(14, cantidad);
            stmt.setString(15, precio);

            try {
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " filas insertadas.");
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            } finally {
                try {
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar los objetos ResultSet, PreparedStatement o Connection: " + e.getMessage());
                }
            }

            Stage stage = (Stage) btnAnnadir.getScene().getWindow();
            stage.close();
        }
    }
    private boolean datosValidos() throws SQLException {
        String mensajeError = "";

        String tipoPieza = textTipoPieza.getValue();
        Integer tipoTamanno = textTipoTamanno.getValue();
        String cliente = listadoClientes.getValue();
        String empleado = listadoEmpleados.getValue();
        String tipoMaterial = textTipoMaterial.getText();
        String tipoPintura = textTipoPintura.getText();
        String tipoAluminio = textTipoAluminio.getText();
        String matricula = textMatricula.getText();
        String precio = textPrecio.getText();
        String notaExterna = textNotaExterna.getText();
        String notaInterna = textNotaInterna.getText();
        String cantidad = textCantidad.getText();
        String manoDeObra = textManoDeObra.getText();
        LocalDate fecha = textFecha.getValue();

        // Verificar campo tipoPieza
        if (tipoPieza == null) {
            mensajeError += "El campo Tipo de Pieza es obligatorio.\n";
        }

        // Verificar campo tipoTamanno
        if (tipoTamanno == null) {
            mensajeError += "El campo Tipo de Tamaño es obligatorio.\n";
        }

        // Verificar campo cliente
        if (cliente == null) {
            mensajeError += "El campo Cliente es obligatorio.\n";
        }

        // Verificar campo empleado
        if (empleado == null) {
            mensajeError += "El campo Empleado es obligatorio.\n";
        }

        // Verificar campo tipoMaterial
        if (tipoMaterial.isEmpty()) {
            mensajeError += "El campo Tipo de Material es obligatorio.\n";
        } else if (!tipoMaterial.matches("(?i)^(SI|NO)$")) {
            mensajeError += "El campo Tipo de Material solo acepta 'SI' o 'NO'.\n";
        }

        // Verificar campo tipoPintura
        if (tipoPintura.isEmpty()) {
            mensajeError += "El campo Tipo de Pintura es obligatorio.\n";
        } else if (!tipoPintura.matches("(?i)^(SI|NO)$")) {
            mensajeError += "El campo Tipo de Pintura solo acepta 'SI' o 'NO'.\n";
        }

        // Verificar campo tipoAluminio
        if (tipoAluminio.isEmpty()) {
            mensajeError += "El campo Tipo de Aluminio es obligatorio.\n";
        } else if (!tipoAluminio.matches("(?i)^(SI|NO)$")) {
            mensajeError += "El campo Tipo de Aluminio solo acepta 'SI' o 'NO'.\n";
        }

        // Verificar campo matricula
        if (matricula.isEmpty()) {
            mensajeError += "El campo Matrícula es obligatorio.\n";
        } else if (!matricula.matches("^\\d{4}[A-Za-z]{3}$")) {
            mensajeError += "El formato de la Matrícula no es válido (formato de matrícula española).\n";
        }

        // Verificar campo precio
        if (precio.isEmpty()) {
            mensajeError += "El campo Precio es obligatorio.\n";
        }

        // Verificar campo notaExterna
        if (notaExterna.isEmpty()) {
            mensajeError += "El campo Nota Externa es obligatorio.\n";
        }

        // Verificar campo notaInterna
        if (notaInterna.isEmpty()) {
            mensajeError += "El campo Nota Interna es obligatorio.\n";
        }

        // Verificar campo cantidad
        if (cantidad.isEmpty()) {
            mensajeError += "El campo Cantidad es obligatorio.\n";
        }

        // Verificar campo manoDeObra
        if (manoDeObra.isEmpty()) {
            mensajeError += "El campo Mano de Obra es obligatorio.\n";
        }

        // Verificar campo fecha
        if (fecha == null) {
            mensajeError += "El campo Fecha es obligatorio.\n";
        }

        if (mensajeError.isEmpty()) {
            return true;
        } else {
            AlertaUtilidad.error("Datos no válidos", mensajeError);
            return false;
        }
    }
}
