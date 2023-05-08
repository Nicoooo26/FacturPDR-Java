package com.facturpdr.aplicacion.clientes.repositorios;

import com.facturpdr.aplicacion.auth.modelos.Cliente;
import com.facturpdr.aplicacion.general.configuraciones.BDConfiguracion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClienteRepositorio {
    public Cliente obtenerUsuarioCorreo(String correo) {
        try {
            String sql = "SELECT NOMBRE_COMPLETO,DNI,MOVIL,CUENTA FROM CLIENTES";
            ResultSet resultado = BDConfiguracion.selecionar(sql, correo);

            if (resultado != null && resultado.next()) {
                int id = resultado.getInt("id");
                String nombreUsuario = resultado.getString("nombre_usuario");
                String correoElectronico = resultado.getString("correo_electronico");
                String contrasena = resultado.getString("contrasena");
                boolean estaVerificado = resultado.getBoolean("esta_verificado");
                Date fechaCreacion = resultado.getDate("fecha_creacion");

                return new Usuario(id, nombreUsuario, correoElectronico, contrasena, estaVerificado, fechaCreacion);
            }

            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
