package com.facturpdr.aplicacion.clientes.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.sql.*;
import java.util.Random;


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



    /**
         * Maneja el evento de clic en el botón "Cancelar".
         * Cambia a la escena de clientes.
         *
         */
        @FXML
        public void clickCancelar() {
            VentanaExtension ventana = VentanaExtension.obtenerInstancia();
            ventana.cambiarEscena("clientes/clientes");
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
            VentanaExtension ventana = VentanaExtension.obtenerInstancia();
            ventana.cambiarEscena("clientes/clientes");
        }

        /**
         * Verifica si los datos ingresados son válidos.
         *
         * @return true si los datos son válidos, false de lo contrario.
         * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
         */



        private boolean datosValidos() throws SQLException{

            String mensajeError = "";


            String nombre = textNombre.getText();
            String apellidos = textApellidos.getText();
            String cuenta = textCuenta.getText();
            String dni = textDNI.getText();
            String movil = textMovil.getText();
            String email = textEmail.getText();
            String codigoPostal = textCodigo.getText();
            String fijo = textFijo.getText();

            if (nombre.isEmpty()) {
                mensajeError += "El campo 'nombre' es obligatorio.\n";
            }
            if (apellidos.isEmpty()) {
                mensajeError += "El campo 'apellidos' es obligatorio.\n";
            }
            if (cuenta.isEmpty()) {
                mensajeError += "El campo 'cuenta' es obligatorio.\n";
            } else if (!cuenta.matches("[A-Z]{2}\\d{22}")) {
                mensajeError += "El formato 'cuenta' no es válido.\n";
            }
            if (dni.isEmpty()) {
                mensajeError += "El campo 'DNI' es obligatorio.\n";
            } else if (!dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
                mensajeError += "El formato 'DNI' no es válido.\n";
            }
            if (existeDNI(dni)) {
                mensajeError += "El DNI introducido ya existe en el sistema.\n";
            }
            if (!movil.isEmpty() && !movil.matches("[67]\\d{8}")) {
                mensajeError += "El formato del 'movil' no es válido.\n";
            }
            if (movil.isEmpty()) {
                mensajeError += "El campo 'movil' es obligatorio.\n";
            } else if (existeMovil(Integer.parseInt(movil))) {
                mensajeError += "El telefono movil introducido ya existe en el sistema.\n";
            }
            if (!email.matches("[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}") && !email.isEmpty()) {
                mensajeError += "El formato 'email' no es válido.\n";
            }
            if (!codigoPostal.isEmpty() && !codigoPostal.matches("^\\d{5}$")) {
                mensajeError += "El formato 'codigo postal' no es válido.\n";
            }
            if (!fijo.isEmpty() && !fijo.matches("^9\\d{8}$")) {
                mensajeError += "El formato 'fijo' no es válido.\n";
            }
            if (mensajeError.isEmpty()) {
                return true;
            } else {
                AlertaUtilidad.error("Datos no válidos", mensajeError);
                return false;
            }

        }

        /**
         * Genera un ID aleatorio para el nuevo cliente.
         *
         * @return El ID generado.
         * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
         */
        private int generarID() throws SQLException {
            Random random = new Random();
            int id;

            do {
                id = random.nextInt(90000) + 10000;
            } while (existeID(id));

            return id;
        }

        /**
         * Verifica si un ID ya existe en la base de datos.
         *
         * @param id El ID a verificar.
         * @return true si el ID existe, false de lo contrario.
         * @throws SQLException Excepción de SQL si ocurre algún error en la consulta.
         */
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


