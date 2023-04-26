package com.facturpdr.aplicacion.auth.repositorios;


import com.facturpdr.aplicacion.general.configuraciones.BDConfiguracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthRepositorio {
    public static int crear(String correoElectronico, String contrasena) {
        String sql = "insert into usuarios (correo_electronico, contrasena) values (?, ?)";
        try {
            Connection conexion = BDConfiguracion.obtenerConexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setString(1, correoElectronico);
            sentencia.setString(2, contrasena);

            sentencia.executeUpdate();
            conexion.close();

            return 1;
        } catch (SQLException ex) {
            return 0;
        }
    }
}
