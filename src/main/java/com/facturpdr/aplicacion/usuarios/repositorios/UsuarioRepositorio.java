package com.facturpdr.aplicacion.usuarios.repositorios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UsuarioRepositorio {

    public Usuario obtenerUsuarioCorreo(String correo) {
        try {
            String sql = "select * from usuarios where correo_electronico = ?";
            ResultSet resultado = BDExtension.selecionar(sql, correo);

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
            ResultSet resultado = BDExtension.selecionar(sql, id);

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
            ResultSet resultado = BDExtension.selecionar(sql, nombreUsuario);

            return resultado != null && resultado.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean existeCorreoElectronico(String correoElectronico) {
        try {
            String sql = "select * from usuarios where correo_electronico = ?";
            ResultSet resultado = BDExtension.selecionar(sql, correoElectronico);

            return resultado != null && resultado.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean crearUsuario(String nombreUsuario, String correoElectronico, String contrasena) {
        String sql = "insert into usuarios (nombre_usuario, correo_electronico, contrasena) values (?, ?, ?)";
        return BDExtension.actualizar(sql, nombreUsuario, correoElectronico, contrasena);
    }

    public boolean eliminarUsuario(int id_usuario) {
        String sql = "delete from usuarios where id = ?";
        return BDExtension.actualizar(sql, id_usuario);
    }

    public boolean verificarUsuario(int id_usuario) {
        String sql = "update usuarios set esta_verificado = 1 where id = ?";
        return BDExtension.actualizar(sql, id_usuario);
    }

    public boolean cammbiarContrasena(String contrasena, int id_usuario) {
        String sql = "update usuarios set contrasena = ? where id = ?";
        return BDExtension.actualizar(sql, contrasena, id_usuario);
    }
}
