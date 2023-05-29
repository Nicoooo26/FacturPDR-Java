package com.facturpdr.aplicacion.clientes.controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Controlador para modificar los datos de un cliente.
 */
public class ModificarClienteControlador {

    /**
     * Campo de texto para el nombre del cliente.
     */
    @FXML
    public TextField textNombre;

    /**
     * Campo de texto para el DNI del cliente.
     */
    @FXML
    public TextField textDNI;

    /**
     * Campo de texto para el número de teléfono móvil del cliente.
     */
    @FXML
    public TextField textMovil;

    /**
     * Campo de texto para los apellidos del cliente.
     */
    @FXML
    public TextField textApellidos;

    /**
     * Campo de texto para el correo electrónico del cliente.
     */
    @FXML
    public TextField textEmail;

    /**
     * Campo de texto para el número de cuenta del cliente.
     */
    @FXML
    public TextField textCuenta;

    /**
     * Campo de texto para la ciudad del cliente.
     */
    @FXML
    public TextField textCiudad;

    /**
     * Campo de texto para la dirección del cliente.
     */
    @FXML
    public TextField textDireccion;

    /**
     * Campo de texto para el país del cliente.
     */
    @FXML
    public TextField textPais;

    /**
     * Campo de texto para el número de teléfono fijo del cliente.
     */
    @FXML
    public TextField textFijo;

    /**
     * Campo de texto para el código postal del cliente.
     */
    @FXML
    public TextField textCodigo;

    /**
     * Botón de cancelar.
     */
    @FXML
    public Button btnCancelar;

    /**
     * Botón de guardar.
     */
    @FXML
    public Button btnGuardar;

    /**
     * DNI anterior del cliente.
     */
    private String dniAntiguo;

    /**
     * Establece el DNI anterior del cliente.
     *
     * @param dni El DNI anterior del cliente.
     */
    public void setDNI(String dni) {
        this.dniAntiguo = dni;
    }

    /**
     * Maneja el evento de hacer clic en el botón Cancelar.
     * Cambia la escena a la vista de clientes.
     */
    @FXML
    public void clickCancelar()  {
        VentanaExtension ventanaExtension = VentanaExtension.obtenerInstancia();
        ventanaExtension.cambiarEscena("clientes/clientes");

    }

    /**
     * Maneja el evento de hacer clic en el botón Guardar.
     *
     * @throws NumberFormatException    Si ocurre un error de formato de número.
     * @throws SQLException             Si ocurre un error de base de datos.
     */
    @FXML
    public void clickGuardar() throws NumberFormatException, SQLException, IOException {

        BDExtension.conectarse();
        Connection conn=BDExtension.conexion;
        String consulta = "UPDATE CLIENTES SET DNI = ?, MOVIL = ?, NOMBRE = ?, APELLIDOS = ?, CUENTA = ?, EMAIL = ?, CIUDAD = ?, DIRECCION = ?, PAIS = ?, FIJO = ?, CODIGOPOSTAL = ?,NOMBRE_COMPLETO= ? WHERE DNI = ?";
        PreparedStatement ps = null;

        if(datosValidos()) {
            String nuevoNombre = textNombre.getText().toUpperCase();
            String nuevoApellidos = textApellidos.getText().toUpperCase();
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
            VentanaExtension ventanaExtension = VentanaExtension.obtenerInstancia();
            ventanaExtension.cambiarEscena("clientes/clientes");
        }

    }

    /**
     * Carga los datos del cliente con el DNI especificado.
     *
     * @param dni El DNI del cliente.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void cargarDatos(String dni) throws SQLException {
        BDExtension.conectarse();
        Connection conn=BDExtension.conexion;
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

    /**
     * Verifica si los datos ingresados por el usuario son válidos.
     *
     * @return true si los datos son válidos, false de lo contrario.
     */
    private boolean datosValidos() {

        String mensajeError = "";

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


        if (mensajeError.length() == 0) {
            return true;
        } else {

            AlertaUtilidad.error("Datos no válidos",mensajeError);
            return false;
        }
    }
}
