package com.facturpdr.aplicacion.clientes.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Random;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;

import java.io.IOException;

import javafx.event.ActionEvent;

    public class CrearClienteControlador{

        @FXML
        public TextField textNombre;

        @FXML
        public TextField textCiudad;

        @FXML
        public TextField textDNI;

        @FXML
        public TextField textApellidos;
        @FXML
        public TextField textMovil;
        @FXML
        public TextField textFijo;
        @FXML
        public TextField textEmail;
        @FXML
        public TextField textCodigo;
        @FXML
        public TextField textPais;
        @FXML
        public TextField textCuenta;
        @FXML
        public TextField textDireccion;
        @FXML
        public Button btnCancelar;
        @FXML
        public Button btnAnnadir;





        @FXML
        public void clickCancelar(ActionEvent event) throws IOException {
            VentanaExtension ventana = VentanaExtension.obtenerInstancia();
            ventana.cambiarEscena("clientes/clientes");
        }

        @FXML
        public void clickAnnadir(ActionEvent event) throws IOException,SQLException {


            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;

            String insertSQL="INSERT INTO CLIENTES (ID,DNI,MOVIL,NOMBRE,APELLIDOS,CUENTA,EMAIL,CIUDAD,DIRECCION,PAIS,FIJO,CODIGOPOSTAL,NOMBRE_COMPLETO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = null;



            if(datosValidos()) {
                String DNI=textDNI.getText();
                int movil=Integer.parseInt(textMovil.getText());
                String nombre=textNombre.getText().toUpperCase();
                String apellidos=textApellidos.getText().toUpperCase();
                String cuenta=textCuenta.getText();
                String email=textEmail.getText().toUpperCase();
                String ciudad=textCiudad.getText().toUpperCase();
                String direccion=textDireccion.getText().toUpperCase();
                String pais=textPais.getText().toUpperCase();
                String nombrecompleto=nombre+' '+apellidos;
                stmt = conn.prepareStatement(insertSQL);
                int id=generarID();
                stmt.setInt(1, id);
                stmt.setString(2,DNI );
                stmt.setInt(3, movil);
                stmt.setString(4, nombre);
                stmt.setString(5, apellidos);
                stmt.setString(6, cuenta);
                stmt.setString(7, email);
                stmt.setString(8, ciudad);
                stmt.setString(9, direccion);
                stmt.setString(10, pais);
                if (!textFijo.getText().isEmpty()) {
                    int fijo = Integer.parseInt(textFijo.getText());
                    stmt.setInt(11, fijo);
                } else {
                    stmt.setNull(11, java.sql.Types.INTEGER);
                }
                if (!textCodigo.getText().isEmpty()) {
                    int codigoPostal = Integer.parseInt(textCodigo.getText());
                    stmt.setInt(12, codigoPostal);
                } else {
                    stmt.setNull(12, java.sql.Types.INTEGER);
                }
                stmt.setString(13,nombrecompleto );
            }else {
                throw new SQLException("Error:Los datos no son válidos.");
            }

            // Ejecuta la consulta
            try {
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " filas insertadas.");
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            }

            // Cierra el objeto PreparedStatement
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el objeto PreparedStatement: " + e.getMessage());
            }
            VentanaExtension ventana = VentanaExtension.obtenerInstancia();
            ventana.cambiarEscena("clientes/clientes");
        }
        private boolean datosValidos() throws SQLException{

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
            if(existeDNI(textDNI.getText())) {
                mensajeError +="El DNI introducido ya existe en el sistema.\n";
            }
            if (textMovil.getText().isEmpty()) {
                mensajeError += "El campo 'Movil' es obligatorio.\n";
            }
            else if(!textMovil.getText().isEmpty() && !textMovil.getText().matches("^6\\d{8}$")) {
                mensajeError += "El formato 'movil' no es válido.\n";
            }
            if(existeMovil(Integer.parseInt(textMovil.getText()))) {
                mensajeError +="El telefono movil introducido ya existe en el sistema.\n";
            }
            if(!textEmail.getText().matches("[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}") && !textEmail.getText().isEmpty()) {
                mensajeError += "El formato 'email' no es válido.\n";
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
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Datos no válidos");
                alerta.setContentText("Por favor, corrige los errores");
                alerta.setContentText(mensajeError);
                alerta.showAndWait();
                return false;
            }

        }
        private int generarID() throws SQLException {
            Random random = new Random();
            int id;

            do {
                id = random.nextInt(90000) + 10000; // Genera un número aleatorio de 5 dígitos
            } while (existeID(id)); // Comprueba si el ID generado ya existe en la base de datos

            return id;
        }
        private boolean existeID(int id) throws SQLException {
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;
            String selectSQL = "SELECT COUNT(*) FROM CLIENTES WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            stmt.setInt(1, id);
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
        private boolean existeDNI(String dni) throws SQLException {
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;
            String selectSQL = "SELECT COUNT(*) FROM CLIENTES WHERE DNI = ?";
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
        private boolean existeMovil(int movil) throws SQLException {
            BDExtension.conectarse();
            Connection conn = BDExtension.conexion;
            String selectSQL = "SELECT COUNT(*) FROM CLIENTES WHERE MOVIL = ?";
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


