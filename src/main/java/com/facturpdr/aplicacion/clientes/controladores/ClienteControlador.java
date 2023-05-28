package com.facturpdr.aplicacion.clientes.controladores;

import java.sql.*;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.fxml.*;
import javafx.scene.control.*;


public class ClienteControlador {

    @FXML
    public Label labelNombre;
    @FXML
    public Label labelApellidos;
    @FXML
    public Label labelDNI;
    @FXML
    public Label labelMovil;
    @FXML
    public Label labelCuenta;
    @FXML
    public Label labelEmail;
    @FXML
    public Label labelCiudad;
    @FXML
    public Label labelDireccion;
    @FXML
    public Label labelPais;
    @FXML
    public Label labelFijo;
    @FXML
    public Label labelCodigo;
    @FXML
    public Button btnBack;



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




    @FXML
   public void clickBack(){
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("clientes/clientes");

    }


}
