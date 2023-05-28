package com.facturpdr.aplicacion.clientes.controladores;

import java.sql.*;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.fxml.*;
import javafx.scene.control.*;

/**
 * Controlador para la muestra de información de cliente.
 */
public class ClienteControlador {

    /**
     * Etiqueta para mostrar el nombre del cliente.
     */
    @FXML
    public Label labelNombre;

    /**
     * Etiqueta para mostrar los apellidos del cliente.
     */
    @FXML
    public Label labelApellidos;

    /**
     * Etiqueta para mostrar el DNI del cliente.
     */
    @FXML
    public Label labelDNI;

    /**
     * Etiqueta para mostrar el número de teléfono móvil del cliente.
     */
    @FXML
    public Label labelMovil;

    /**
     * Etiqueta para mostrar el número de cuenta del cliente.
     */
    @FXML
    public Label labelCuenta;

    /**
     * Etiqueta para mostrar el correo electrónico del cliente.
     */
    @FXML
    public Label labelEmail;

    /**
     * Etiqueta para mostrar la ciudad del cliente.
     */
    @FXML
    public Label labelCiudad;

    /**
     * Etiqueta para mostrar la dirección del cliente.
     */
    @FXML
    public Label labelDireccion;

    /**
     * Etiqueta para mostrar el país del cliente.
     */
    @FXML
    public Label labelPais;

    /**
     * Etiqueta para mostrar el número de teléfono fijo del cliente.
     */
    @FXML
    public Label labelFijo;

    /**
     * Etiqueta para mostrar el código postal del cliente.
     */
    @FXML
    public Label labelCodigo;

    /**
     * Botón para volver atrás.
     */
    @FXML
    public Button btnBack;

    /**
     * Método para cargar los datos de un cliente según su DNI.
     *
     * @param dni DNI del cliente.
     * @throws SQLException si ocurre un error en la consulta SQL.
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
                labelDNI.setText(rs.getString("DNI"));
                labelApellidos.setText(rs.getString("APELLIDOS"));
                labelNombre.setText(rs.getString("NOMBRE"));
                labelMovil.setText(Integer.toString(rs.getInt("MOVIL")));
                labelEmail.setText(rs.getString("EMAIL"));
                labelCuenta.setText(rs.getString("CUENTA"));
                labelCiudad.setText(rs.getString("CIUDAD"));
                labelDireccion.setText(rs.getString("DIRECCION"));
                labelPais.setText(rs.getString("PAIS"));
                String codigo=rs.getString("CODIGOPOSTAL");
                String fijo=rs.getString("FIJO");
                if (fijo != null && !fijo.isEmpty()) {
                    labelFijo.setText(Integer.toString(rs.getInt("FIJO")));
                } else {
                    labelFijo.setText("");
                }
                if (codigo != null && !codigo.isEmpty()) {
                    labelCodigo.setText(Integer.toString(rs.getInt("CODIGOPOSTAL")));
                } else {
                    labelCodigo.setText("");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    /**
     * Método que se ejecuta al hacer clic en el botón de volver atrás.
     */
    @FXML
   public void clickBack(){
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("clientes/clientes");

    }


}
