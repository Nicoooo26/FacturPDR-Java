package com.facturpdr.aplicacion.usuarios.repositorios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioRepositorio {
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "select * from usuarios";
            ResultSet resultado = BDExtension.selecionar(sql);

            if (resultado != null && resultado.next()) {
                int id = resultado.getInt("id");
                String nombreUsuario = resultado.getString("nombre_usuario");
                String correoElectronico = resultado.getString("correo_electronico");
                String contrasena = resultado.getString("contrasena");
                Date fechaCreacion = resultado.getDate("fecha_creacion");

                usuarios.add(new Usuario(id, nombreUsuario, correoElectronico, contrasena, fechaCreacion));
            }

            return usuarios;
        } catch (SQLException e) {
            return null;
        }
    }

    public Usuario obtenerUsuarioCorreo(String correo) {
        try {
            String sql = "select * from usuarios where correo_electronico = ?";
            ResultSet resultado = BDExtension.selecionar(sql, correo);

            if (resultado != null && resultado.next()) {
                int id = resultado.getInt("id");
                String nombreUsuario = resultado.getString("nombre_usuario");
                String correoElectronico = resultado.getString("correo_electronico");
                String contrasena = resultado.getString("contrasena");
                Date fechaCreacion = resultado.getDate("fecha_creacion");

                return new Usuario(id, nombreUsuario, correoElectronico, contrasena, fechaCreacion);
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
                Date fechaCreacion = resultado.getDate("fecha_creacion");

                return new Usuario(id, nombreUsuario, correoElectronico, contrasena, fechaCreacion);
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

    public boolean cambiarContrasena(String contrasena, int id_usuario) {
        String sql = "update usuarios set contrasena = ? where id = ?";
        return BDExtension.actualizar(sql, contrasena, id_usuario);
    }

    public boolean cambiarCorreoElectronico(String correoElectronico, int id_usuario) {
        String sql = "update usuarios set correo_electronico = ? where id = ?";
        return BDExtension.actualizar(sql, correoElectronico, id_usuario);
    }

    public boolean cambiarNombreUsuario(String nombre_usuario, int id_usuario) {
        String sql = "update usuarios set nombre_usuario = ? where id = ?";
        return BDExtension.actualizar(sql, nombre_usuario, id_usuario);
    }
}
