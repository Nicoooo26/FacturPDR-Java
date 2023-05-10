package com.facturpdr.aplicacion.general.extensiones;

import com.facturpdr.aplicacion.general.utilidades.ConfiguracionUtilidad;
import org.mariadb.jdbc.Connection;

import java.sql.*;

public class BDExtension {
    private static final String url = ConfiguracionUtilidad.obtenerValor("bd.url");
    private static final String usuario = ConfiguracionUtilidad.obtenerValor("bd.usuario");
    private static final String contrasena = ConfiguracionUtilidad.obtenerValor("bd.contrasena");

    private static Connection conexion = null;
    private static PreparedStatement sentencia = null;

    private static void conectarse() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = (Connection) DriverManager.getConnection(url, usuario, contrasena);
        }
    }

    private static void desconectarse() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
        if (sentencia != null) {
            sentencia.close();
        }
    }

    public static ResultSet selecionar(String consulta, Object... parametros) {
        try {
            conectarse();

            sentencia = conexion.prepareStatement(consulta);
            for (int i = 0; i < parametros.length; i++) {
                sentencia.setObject(i + 1, parametros[i]);
            }

            return sentencia.executeQuery();
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                desconectarse();
            } catch (SQLException ignored) { }
        }
    }

    public static boolean actualizar(String consulta, Object... parametros) {
        try {
            conectarse();

            sentencia = conexion.prepareStatement(consulta);
            for (int i = 0; i < parametros.length; i++) {
                sentencia.setObject(i + 1, parametros[i]);
            }

            sentencia.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                desconectarse();
            } catch (SQLException ignored) { }
        }
    }
}
