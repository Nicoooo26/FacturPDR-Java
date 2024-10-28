/**
 * El paquete `com.facturpdr.aplicacion.general.extensiones` contiene clases que proporcionan extensiones y utilidades generales.
 */
package com.facturpdr.aplicacion.general.extensiones;

import com.facturpdr.aplicacion.general.utilidades.ConfiguracionUtilidad;

import java.sql.*;

/**
 * La clase `BDExtension` proporciona métodos para interactuar con una base de datos.
 */
public class BDExtension {
    private static final String url = ("jdbc:oracle:thin:@localhost:1521:xe");
    private static final String usuario = ("facturpdr");
    private static final String contrasena = ("123456");

    public static Connection conexion = null;
    private static PreparedStatement sentencia = null;

    /**
     * Establece una conexión con la base de datos.
     *
     * @throws SQLException Si ocurre un error al conectarse a la base de datos.
     */
    public static void conectarse() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException ignore) { }
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        }
    }

    /**
     * Cierra la conexión y la sentencia a la base de datos.
     *
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    private static void desconectarse() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
        if (sentencia != null) {
            sentencia.close();
        }
    }

    /**
     * Ejecuta una consulta de selección en la base de datos.
     *
     * @param consulta La consulta SQL a ejecutar.
     * @param parametros Los parámetros para la consulta.
     * @return El resultado de la consulta como un objeto ResultSet.
     */
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
        }
    }

    /**
     * Ejecuta una consulta de actualización en la base de datos.
     *
     * @param consulta La consulta SQL a ejecutar.
     * @param parametros Los parámetros para la consulta.
     * @return `true` si la consulta se ejecutó correctamente, `false` si ocurrió un error.
     */
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
        }
    }
}
