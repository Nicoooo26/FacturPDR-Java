package com.facturpdr.aplicacion.usuarios.servicios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.auth.utilidades.HashUtilidad;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarContrasenaException;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarCorreoElectronicoException;
import com.facturpdr.aplicacion.usuarios.excepciones.CambiarNombreUsuarioException;
import com.facturpdr.aplicacion.usuarios.excepciones.ContrasenaIgualException;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void cambiarContrasena(String contrasenaNueva, int id_usuario) throws CambiarContrasenaException, ContrasenaIgualException {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) throw new CambiarContrasenaException();

        String contrasenaNuevaHash = HashUtilidad.sha256(contrasenaNueva);
        if (usuario.getContrasena().equals(contrasenaNuevaHash)) throw new ContrasenaIgualException();

        boolean estaCambiada = usuarioRepositorio.cambiarContrasena(contrasenaNuevaHash, id_usuario);
        if (!estaCambiada) throw new CambiarContrasenaException();
    }

    public void cambiarCorreoElectronico(String correoElectronico, int id_usuario) throws CambiarCorreoElectronicoException {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) throw new CambiarCorreoElectronicoException();

        usuarioRepositorio.cambiarCorreoElectronico(correoElectronico, id_usuario);
    }

    public void cambiarNombreUsuario(String nombreUsuario, int id_usuario) throws CambiarNombreUsuarioException {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) throw new CambiarNombreUsuarioException();

        usuarioRepositorio.cambiarNombreUsuario(nombreUsuario, id_usuario);
    }
}
