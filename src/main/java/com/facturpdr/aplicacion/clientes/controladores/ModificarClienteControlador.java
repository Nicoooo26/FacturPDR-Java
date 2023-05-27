package com.facturpdr.aplicacion.clientes.controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ModificarClienteControlador {

    @FXML
    public TextField textNombre;
    @FXML
    public TextField textDNI;
    @FXML
    public TextField textMovil;
    @FXML
    public TextField textApellidos;
    @FXML
    public TextField textEmail;
    @FXML
    public TextField textCuenta;
    @FXML
    public TextField textCiudad;
    @FXML
    public TextField textDireccion;
    @FXML
    public TextField textPais;
    @FXML
    public TextField textFijo;
    @FXML
    public TextField textCodigo;

    @FXML
    public Button btnCancelar;
    @FXML
    public Button btnGuardar;

    private String dniAntiguo;

    public void setDNI(String dni) {
        this.dniAntiguo=dni;
    }

    @FXML
    public void clickCancelar() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("clientes/clientes");
    }
    @FXML
    public void clickGuardar() throws IOException, NumberFormatException, SQLException {

        BDExtension.conectarse();
        Connection conn=BDExtension.conexion;
        String consulta = "UPDATE CLIENTES SET DNI = ?, MOVIL = ?, NOMBRE = ?, APELLIDOS = ?, CUENTA = ?, EMAIL = ?, CIUDAD = ?, DIRECCION = ?, PAIS = ?, FIJO = ?, CODIGOPOSTAL = ?,NOMBRE_COMPLETO= ? WHERE DNI = ?";
        PreparedStatement ps = null;

        if(datosValidos()) {
            String nuevoNombre = textNombre.getText();
            String nuevoApellidos = textApellidos.getText();
            int nuevoMovil = Integer.parseInt(textMovil.getText());
            String nuevoDNI = textDNI.getText();
            String nuevoEmail = textEmail.getText();
            String nuevaCuenta = textCuenta.getText();
            String nuevaCiudad = textCiudad.getText();
            String nuevaDireccion = textDireccion.getText();
            String nuevoPais = textPais.getText();
            String nuevoNombreCompleto = nuevoNombre +" "+nuevoApellidos;
            ps = conn.prepareStatement(consulta);
            ps.setString(1, nuevoDNI);
            ps.setInt(2, nuevoMovil);
            ps.setString(3, nuevoNombre);
            ps.setString(4, nuevoApellidos);
            ps.setString(5, nuevaCuenta);
            ps.setString(6, nuevoEmail);
            ps.setString(7, nuevaCiudad);
            ps.setString(8, nuevaDireccion);
            ps.setString(9, nuevoPais);
            if(!textFijo.getText().isEmpty()) {
                int nuevoFijo = Integer.parseInt(textFijo.getText());
                ps.setInt(10, nuevoFijo);

            }
            else  {
                ps.setNull(10, java.sql.Types.INTEGER);
            }
            if(!textCodigo.getText().isEmpty()){
                int nuevoCodigo = Integer.parseInt(textCodigo.getText());
                ps.setInt(11, nuevoCodigo);

            }
            else  {
                ps.setNull(11, java.sql.Types.INTEGER);
            }
            ps.setString(12, nuevoNombreCompleto);
            ps.setString(13, dniAntiguo);

            ps.executeUpdate();
            ps.close();
            VentanaExtension ventana = VentanaExtension.obtenerInstancia();
            ventana.cambiarEscena("clientes/clientes");
        }

    }

    public void cargarDatos(String dni) throws SQLException {
        BDExtension.conectarse();
        Connection conn=BDExtension.conexion;
        // Realizar la consulta SQL con el DNI almacenado en la variable 'dni'
        String query = "SELECT NOMBRE,APELLIDOS,DNI,MOVIL,CUENTA,EMAIL,CIUDAD,DIRECCION,PAIS,FIJO,CODIGOPOSTAL FROM CLIENTES WHERE DNI = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Asignar los datos del cliente a los labels correspondientes
                textDNI.setText(rs.getString("DNI"));
                textApellidos.setText(rs.getString("APELLIDOS"));
                textNombre.setText(rs.getString("NOMBRE"));
                textMovil.setText(Integer.toString(rs.getInt("MOVIL")));
                textEmail.setText(rs.getString("EMAIL"));
                textCuenta.setText(rs.getString("CUENTA"));
                textCiudad.setText(rs.getString("CIUDAD"));
                textDireccion.setText(rs.getString("DIRECCION"));
                textPais.setText(rs.getString("PAIS"));
                String codigo=rs.getString("CODIGOPOSTAL");
                String fijo=rs.getString("FIJO");
                if (fijo != null && !fijo.isEmpty()) {
                    textFijo.setText(Integer.toString(rs.getInt("FIJO")));
                } else {
                    textFijo.setText("");
                }
                if (codigo != null && !codigo.isEmpty()) {
                    textCodigo.setText(Integer.toString(rs.getInt("CODIGOPOSTAL")));
                } else {
                    textCodigo.setText("");
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
    private boolean datosValidos() {

        //Inicializo string paramensajes
        String mensajeError = "";

        //Compruebo los campos
        if (textNombre.getText().isEmpty()) {
            mensajeError += "El campo 'nombre' es obligatorio.\n";
        }
        if (textApellidos.getText().isEmpty()) {
            mensajeError += "El campo 'apellidos' es obligatorio.\n";
        }
        if (textCuenta.getText().isEmpty()) {
            mensajeError += "El campo 'cuenta' es obligatorio.\n";
        }
        else if(!textCuenta.getText().isEmpty() && !textCuenta.getText().matches("[A-Z]{2}\\d{22}")) {
            mensajeError += "El formato 'cuenta' no es válido.\n";
        }
        if (textDNI.getText().isEmpty()) {
            mensajeError += "El campo 'DNI' es obligatorio.\n";
        }
        else if(!textDNI.getText().isEmpty() && !textDNI.getText().matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            mensajeError += "El formato 'DNI' no es válido.\n";
        }

        if (textMovil.getText().isEmpty()) {
            mensajeError += "El campo 'Movil' es obligatorio.\n";
        }
        else if(!textMovil.getText().isEmpty() && !textMovil.getText().matches("^6\\d{8}$")) {
            mensajeError += "El formato 'movil' no es válido.\n";
        }


        if(!textCodigo.getText().isEmpty() && !textCodigo.getText().matches("^\\d{5}$")) {
            mensajeError += "El formato 'codigo postal' no es válido.\n";
        }

        if(!textFijo.getText().isEmpty() && !textFijo.getText().matches("^9\\d{8}$")) {
            mensajeError += "El formato 'fijo' no es válido.\n";
        }


        //Si no hay errores devuelvo true, si no, una alerta con los errores y false
        if (mensajeError.length() == 0) {
            return true;
        } else {
            //Muestro alerta y devuelvo false
            AlertaUtilidad.error("Datos no válidos",mensajeError);
            return false;
        }

    }

}
