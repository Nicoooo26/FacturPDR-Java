package com.facturpdr.aplicacion.auth.servicios;

import com.facturpdr.aplicacion.auth.excepciones.CorreoElectronicoExistenteException;
import com.facturpdr.aplicacion.auth.excepciones.CrearUsuarioException;
import com.facturpdr.aplicacion.auth.excepciones.NombreUsuarioExistenteException;
import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.auth.utilidades.HashUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

/**
 * Servicio de autenticación y registro de usuarios.
 */
public class AuthServicio {
    private final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param nombreUsuario     El nombre de usuario del nuevo usuario.
     * @param correoElectronico El correo electrónico del nuevo usuario.
     * @param contrasena        La contraseña del nuevo usuario.
     * @throws NombreUsuarioExistenteException     Si el nombre de usuario ya está en uso.
     * @throws CorreoElectronicoExistenteException Si el correo electrónico ya está en uso.
     * @throws CrearUsuarioException               Si ocurre un error al crear el usuario en el repositorio.
     */
    public void registrar(String nombreUsuario, String correoElectronico, String contrasena) throws NombreUsuarioExistenteException, CorreoElectronicoExistenteException, CrearUsuarioException {
        boolean existeCorreoElectronico = usuarioRepositorio.existeCorreoElectronico(correoElectronico);
        if (existeCorreoElectronico) throw new CorreoElectronicoExistenteException();

        boolean existeNombreUsuario = usuarioRepositorio.existeNombreUsuario(nombreUsuario);
        if (existeNombreUsuario) throw new NombreUsuarioExistenteException();

        String contrasenaHash = HashUtilidad.sha256(contrasena);

        boolean estaCreado = usuarioRepositorio.crearUsuario(nombreUsuario, correoElectronico, contrasenaHash);
        if (!estaCreado) throw new CrearUsuarioException();
    }

    /**
     * Inicia sesión con las credenciales proporcionadas.
     *
     * @param correoElectronico El correo electrónico del usuario.
     * @param contrasena        La contraseña del usuario.
     * @return El ID del usuario si la autenticación es exitosa, -1 en caso contrario.
     */
    public int iniciarSesion(String correoElectronico, String contrasena) {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioCorreo(correoElectronico);
        if (usuario == null) return -1;

        String contrasenaHash = HashUtilidad.sha256(contrasena);
        if (!usuario.getContrasena().equals(contrasenaHash)) return -1;

        return usuario.getId();
    }
}
