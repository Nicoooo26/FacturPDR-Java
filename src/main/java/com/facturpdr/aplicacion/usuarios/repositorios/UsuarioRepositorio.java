/**
 * El paquete `com.facturpdr.aplicacion.usuarios.repositorios` contiene clases que proporcionan métodos para acceder y gestionar la información de los usuarios en la base de datos.
 */
package com.facturpdr.aplicacion.usuarios.repositorios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.general.extensiones.BDExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La clase `UsuarioRepositorio` proporciona métodos para acceder y gestionar la información de los usuarios en la base de datos.
 */
public class UsuarioRepositorio {

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Una lista de usuarios.
     */
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuarios";
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

    /**
     * Obtiene un usuario por su dirección de correo electrónico.
     *
     * @param correo La dirección de correo electrónico del usuario.
     * @return El objeto Usuario correspondiente, o null si no se encuentra.
     */
    public Usuario obtenerUsuarioCorreo(String correo) {
        try {
            String sql = "SELECT * FROM usuarios WHERE correo_electronico = ?";
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

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario.
     * @return El objeto Usuario correspondiente, o null si no se encuentra.
     */
    public Usuario obtenerUsuarioID(int id) {
        try {
            String sql = "SELECT * FROM usuarios WHERE id = ?";
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

    /**
     * Verifica si existe un nombre de usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario a verificar.
     * @return true si el nombre de usuario existe, false de lo contrario.
     */
    public boolean existeNombreUsuario(String nombreUsuario) {
        try {
            String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
            ResultSet resultado = BDExtension.selecionar(sql, nombreUsuario);

            return resultado != null && resultado.next();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Verifica si existe una dirección de correo electrónico en la base de datos.
     *
     * @param correoElectronico La dirección de correo electrónico a verificar.
     * @return true si la dirección de correo electrónico existe, false de lo contrario.
     */
    public boolean existeCorreoElectronico(String correoElectronico) {
        try {
            String sql = "SELECT * FROM usuarios WHERE correo_electronico = ?";
            ResultSet resultado = BDExtension.selecionar(sql, correoElectronico);

            return resultado != null && resultado.next();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param nombreUsuario      El nombre de usuario del nuevo usuario.
     * @param correoElectronico  La dirección de correo electrónico del nuevo usuario.
     * @param contrasena         La contraseña del nuevo usuario.
     * @return true si se crea el usuario correctamente, false de lo contrario.
     */
    public boolean crearUsuario(String nombreUsuario, String correoElectronico, String contrasena) {
        String sql = "INSERT INTO usuarios (nombre_usuario, correo_electronico, contrasena) VALUES (?, ?, ?)";
        return BDExtension.actualizar(sql, nombreUsuario, correoElectronico, contrasena);
    }

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param contrasena  La nueva contraseña.
     * @param id_usuario  El ID del usuario.
     * @return true si se cambia la contraseña correctamente, false de lo contrario.
     */
    public boolean cambiarContrasena(String contrasena, int id_usuario) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id = ?";
        return BDExtension.actualizar(sql, contrasena, id_usuario);
    }

    /**
     * Cambia la dirección de correo electrónico de un usuario.
     *
     * @param correoElectronico  La nueva dirección de correo electrónico.
     * @param id_usuario         El ID del usuario.
     * @return true si se cambia la dirección de correo electrónico correctamente, false de lo contrario.
     */
    public boolean cambiarCorreoElectronico(String correoElectronico, int id_usuario) {
        String sql = "UPDATE usuarios SET correo_electronico = ? WHERE id = ?";
        return BDExtension.actualizar(sql, correoElectronico, id_usuario);
    }

    /**
     * Cambia el nombre de usuario de un usuario.
     *
     * @param nombre_usuario  El nuevo nombre de usuario.
     * @param id_usuario      El ID del usuario.
     * @return true si se cambia el nombre de usuario correctamente, false de lo contrario.
     */
    public boolean cambiarNombreUsuario(String nombre_usuario, int id_usuario) {
        String sql = "UPDATE usuarios SET nombre_usuario = ? WHERE id = ?";
        return BDExtension.actualizar(sql, nombre_usuario, id_usuario);
    }
}
