package com.facturpdr.aplicacion.empleados.controladores;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class ControladorCrearEmpleado {

    @FXML
    public TextField textDNI;

    @FXML
    public TextField textNombre;

    @FXML
    public TextField textApellidos;

    @FXML
    public TextField textTelefono;

    @FXML
    private DatePicker textNacimiento;

    @FXML
    public Button btnCancelar;


    @FXML
    public Button btnAnnadir;



    /**
     * Maneja el evento de clic en el botón "Cancelar".
     * Cambia a la escena de clientes.
     *
     */
    @FXML
    public void clickCancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el evento de clic en el botón "Añadir".
     * Realiza la inserción de un nuevo cliente en la base de datos.
     *
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    @FXML
    public void clickAnnadir() throws SQLException {


        BDExtension.conectarse();
        Connection conn = BDExtension.conexion;

        String insertSQL="INSERT INTO EMPLEADOS (NIF,ID_USUARIO,NOMBRE,APELLIDOS,TELEFONO,FECHA_NACIMIENTO) VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        if(datosValidos()) {
            String DNI=textDNI.getText();
            int telefono=Integer.parseInt(textTelefono.getText());
            String nombre=textNombre.getText().toUpperCase();
            String apellidos=textApellidos.getText().toUpperCase();
            LocalDate localDate = textNacimiento.getValue();
            java.sql.Date fecha_nacimiento = null;
            if (localDate != null) {
                fecha_nacimiento = java.sql.Date.valueOf(localDate);
            }
            stmt = conn.prepareStatement(insertSQL);
            int ID= PreferenciaUtilidad.obtenerIDUsuario();
            stmt.setString(1,DNI);
            stmt.setInt(2,ID);
            stmt.setString(3,nombre );
            stmt.setString(4, apellidos);
            stmt.setInt(5, telefono);
            stmt.setObject(6, fecha_nacimiento);

        }else {
            throw new SQLException("Error:Los datos no son válidos.");
        }
        try {
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " filas insertadas.");
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }

        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar el objeto PreparedStatement: " + e.getMessage());
        }
        Stage stage = (Stage) btnAnnadir.getScene().getWindow();
        stage.close();
        textDNI.clear();
        textNombre.clear();
        textApellidos.clear();
        textTelefono.clear();
        textNacimiento.setValue(null);
    }

    /**
     * Verifica si los datos ingresados son válidos.
     *
     * @return true si los datos son válidos, false de lo contrario.
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    private boolean datosValidos() throws SQLException {
        String mensajeError = "";
        String dni = textDNI.getText();
        String nombre = textNombre.getText();
        String apellidos = textApellidos.getText();
        String telefono = textTelefono.getText();

        if (dni.isEmpty()) {
            mensajeError += "El campo DNI es obligatorio.\n";
        } else if (!dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            mensajeError += "El formato del DNI no es válido.\n";
        }

        if (nombre.isEmpty()) {
            mensajeError += "El campo Nombre es obligatorio.\n";
        }

        if (apellidos.isEmpty()) {
            mensajeError += "El campo Apellidos es obligatorio.\n";
        }

        if (!telefono.isEmpty() && !telefono.matches("[67]\\d{8}")) {
            mensajeError += "El formato del Teléfono no es válido.\n";
        }

        if (existeDNI(textDNI.getText())) {
            mensajeError += "El DNI introducido ya existe en el sistema.\n";
        }

        if (telefono.isEmpty()) {
            mensajeError += "El campo Teléfono es obligatorio.\n";
        } else if (existeMovil(Integer.parseInt(textTelefono.getText()))) {
            mensajeError += "El telefono movil introducido ya existe en el sistema.\n";
        }

        if (mensajeError.isEmpty()) {
            return true;
        } else {
            AlertaUtilidad.error("Datos no válidos", mensajeError);
            return false;
        }
    }


    /**
     * Verifica si un DNI ya existe en la base de datos.
     *
     * @param dni El DNI a verificar.
     * @return true si el DNI existe, false de lo contrario.
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    private boolean existeDNI(String dni) throws SQLException {
        BDExtension.conectarse();
        Connection conn = BDExtension.conexion;
        String selectSQL = "SELECT COUNT(*) FROM EMPLEADOS WHERE NIF = ?";
        PreparedStatement stmt = conn.prepareStatement(selectSQL);
        stmt.setString(1, dni);
        ResultSet rs = stmt.executeQuery();

        boolean existe = false;
        if (rs.next()) {
            int count = rs.getInt(1);
            existe = count > 0;
        }

        rs.close();
        stmt.close();
        return existe;
    }

    /**
     * Verifica si un número de móvil ya existe en la base de datos.
     *
     * @param movil El número de móvil a verificar.
     * @return true si el número de móvil existe, false de lo contrario.
     * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
     */
    private boolean existeMovil(int movil) throws SQLException {
        BDExtension.conectarse();
        Connection conn = BDExtension.conexion;
        String selectSQL = "SELECT COUNT(*) FROM EMPLEADOS WHERE TELEFONO = ?";
        PreparedStatement stmt = conn.prepareStatement(selectSQL);
        stmt.setInt(1, movil);
        ResultSet rs = stmt.executeQuery();

        boolean existe = false;
        if (rs.next()) {
            int count = rs.getInt(1);
            existe = count > 0;
        }

        rs.close();
        stmt.close();
        return existe;
    }
}
