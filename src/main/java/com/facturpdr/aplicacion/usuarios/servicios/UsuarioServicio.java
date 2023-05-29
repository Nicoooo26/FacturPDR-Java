/**
 * El paquete `com.facturpdr.aplicacion.usuarios.servicios` contiene clases que proporcionan servicios relacionados con la gestión de usuarios.
 */
package com.facturpdr.aplicacion.usuarios.servicios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.auth.utilidades.HashUtilidad;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarContrasenaException;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarCorreoElectronicoException;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarNombreUsuarioException;
import com.facturpdr.aplicacion.usuarios.excepciones.ContrasenaIgualException;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

/**
 * La clase `UsuarioServicio` proporciona servicios relacionados con la gestión de usuarios.
 */
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param contrasenaNueva La nueva contraseña.
     * @param id_usuario      El ID del usuario.
     * @throws CambiarContrasenaException Si ocurre un error al cambiar la contraseña.
     * @throws ContrasenaIgualException   Si la nueva contraseña es igual a la contraseña actual del usuario.
     */
    public void cambiarContrasena(String contrasenaNueva, int id_usuario) throws CambiarContrasenaException, ContrasenaIgualException {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) throw new CambiarContrasenaException();

        String contrasenaNuevaHash = HashUtilidad.sha256(contrasenaNueva);
        if (usuario.getContrasena().equals(contrasenaNuevaHash)) throw new ContrasenaIgualException();

        boolean estaCambiada = usuarioRepositorio.cambiarContrasena(contrasenaNuevaHash, id_usuario);
        if (!estaCambiada) throw new CambiarContrasenaException();
    }

    /**
     * Cambia la dirección de correo electrónico de un usuario.
     *
     * @param correoElectronico La nueva dirección de correo electrónico.
     * @param id_usuario        El ID del usuario.
     * @throws CambiarCorreoElectronicoException Si ocurre un error al cambiar la dirección de correo electrónico.
     */
    public void cambiarCorreoElectronico(String correoElectronico, int id_usuario) throws CambiarCorreoElectronicoException {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) throw new CambiarCorreoElectronicoException();

        usuarioRepositorio.cambiarCorreoElectronico(correoElectronico, id_usuario);
    }

    /**
     * Cambia el nombre de usuario de un usuario.
     *
     * @param nombreUsuario El nuevo nombre de usuario.
     * @param id_usuario    El ID del usuario.
     * @throws CambiarNombreUsuarioException Si ocurre un error al cambiar el nombre de usuario.
     */
    public void cambiarNombreUsuario(String nombreUsuario, int id_usuario) throws CambiarNombreUsuarioException {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) throw new CambiarNombreUsuarioException();

        usuarioRepositorio.cambiarNombreUsuario(nombreUsuario, id_usuario);
    }
}
