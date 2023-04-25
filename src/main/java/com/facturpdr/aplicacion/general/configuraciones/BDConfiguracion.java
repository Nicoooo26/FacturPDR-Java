package com.facturpdr.aplicacion.general.configuraciones;

import org.mariadb.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfiguracion {
    private static final String url = "jdbc:mariadb://83.171.249.116:3306/facturpdr";
    private static final String usuario = "facturpdr";
    private static final String contrasena = "iw%4@H9AR35#";
    private static final String driver = "org.mariadb.jdbc.Driver";

    private static Connection conexion = null;

    private Connection obtenerConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName(driver);
                conexion = (Connection) DriverManager.getConnection(url, usuario, contrasena);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectarse a la base de datos: " + ex.getMessage());
        }

        return conexion;
    }
}
