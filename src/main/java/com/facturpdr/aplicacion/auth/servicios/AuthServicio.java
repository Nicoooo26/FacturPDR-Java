package com.facturpdr.aplicacion.auth.servicios;

import com.facturpdr.aplicacion.auth.excepciones.CorreoElectronicoExistenteException;
import com.facturpdr.aplicacion.auth.excepciones.CrearUsuarioException;
import com.facturpdr.aplicacion.auth.excepciones.NombreUsuarioExistenteException;
import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.auth.utilidades.HashUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

public class AuthServicio {
    private final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void registrar(String nombreUsuario, String correoElectronico, String contrasena) throws NombreUsuarioExistenteException, CorreoElectronicoExistenteException, CrearUsuarioException {
        boolean existeCorreoElectronico = usuarioRepositorio.existeCorreoElectronico(correoElectronico);
        if (existeCorreoElectronico) throw new CorreoElectronicoExistenteException();

        boolean existeNombreUsuario = usuarioRepositorio.existeNombreUsuario(nombreUsuario);
        if (existeNombreUsuario) throw new NombreUsuarioExistenteException();

        String contrasenaHash = HashUtilidad.sha256(contrasena);

        boolean estaCreado = usuarioRepositorio.crearUsuario(nombreUsuario, correoElectronico, contrasenaHash);
        if (!estaCreado) throw new CrearUsuarioException();
    }

    public int iniciarSesion(String correoElectronico, String contrasena) {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioCorreo(correoElectronico);
        if (usuario == null) return -1;

        String contrasenaHash = HashUtilidad.sha256(contrasena);
        if (!usuario.getContrasena().equals(contrasenaHash)) return -1;

        return usuario.getId();
    }
}
