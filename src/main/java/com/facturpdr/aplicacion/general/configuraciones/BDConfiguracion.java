package com.facturpdr.aplicacion.general.configuraciones;

import org.mariadb.jdbc.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BDConfiguracion {
    private static final String url = "jdbc:mariadb://83.171.249.116:3306/facturpdr";
    private static final String usuario = "facturpdr";
    private static final String contrasena = "iw%4@H9AR35#";

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

    public static List<Map<String, Object>> selecionar(String consulta, Object... parametros) {
        try {
            conectarse();

            sentencia = conexion.prepareStatement(consulta);
            for (int i = 0; i < parametros.length; i++) {
                sentencia.setObject(i + 1, parametros[i]);
            }

            ResultSet resultado = sentencia.executeQuery();
            ResultSetMetaData metadatos = resultado.getMetaData();

            int columnas = metadatos.getColumnCount();
            List<Map<String, Object>> datos = new ArrayList<Map<String, Object>>();

            while (resultado.next()) {
                Map<String, Object> fila = new HashMap<String, Object>();
                for (int i = 1; i <= columnas; i++) {
                    String nombreColumna = metadatos.getColumnLabel(i).toLowerCase();
                    Object valorColumna = resultado.getObject(i);
                    fila.put(nombreColumna, valorColumna);
                }
                datos.add(fila);
            }

            return datos;
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
