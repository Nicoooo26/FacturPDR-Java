package com.facturpdr.aplicacion.usuarios.repositorios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.general.configuraciones.BDConfiguracion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UsuarioRepositorio {

    public Usuario obtenerUsuarioCorreo(String correo) {
        try {
            String sql = "select * from usuarios where correo_electronico = ?";
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

    public Usuario obtenerUsuarioID(int id) {
        try {
            String sql = "select * from usuarios where id = ?";
            ResultSet resultado = BDConfiguracion.selecionar(sql, id);

            if (resultado != null && resultado.next()) {
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

    public boolean existeNombreUsuario(String nombreUsuario) {
        try {
            String sql = "select * from usuarios where nombre_usuario = ?";
            ResultSet resultado = BDConfiguracion.selecionar(sql, nombreUsuario);

            return resultado != null && resultado.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean existeCorreoElectronico(String correoElectronico) {
        try {
            String sql = "select * from usuarios where correo_electronico = ?";
            ResultSet resultado = BDConfiguracion.selecionar(sql, correoElectronico);

            return resultado != null && resultado.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean crearUsuario(String nombreUsuario, String correoElectronico, String contrasena) {
        String sql = "insert into usuarios (nombre_usuario, correo_electronico, contrasena) values (?, ?, ?)";
        return BDConfiguracion.actualizar(sql, nombreUsuario, correoElectronico, contrasena);
    }

    public boolean eliminarUsuario(int id) {
        String sql = "delete from usuarios where id = ?";
        return BDConfiguracion.actualizar(sql, id);
    }

    public boolean verificarUsuario(int id) {
        String sql = "update usuarios set esta_verificado = 1 where id = ?";
        return BDConfiguracion.actualizar(sql, id);
    }
}
