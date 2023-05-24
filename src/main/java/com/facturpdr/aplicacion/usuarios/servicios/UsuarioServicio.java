package com.facturpdr.aplicacion.usuarios.servicios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.auth.utilidades.HashUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

public class UsuarioServicio {
    UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public boolean cambiarContrasena(String contrasenaNueva, int id_usuario) {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) return false;

        String contrasenaHash = HashUtilidad.sha256(contrasenaNueva);
        if (usuario.getContrasena().equals(contrasenaHash)) return false;

        return usuarioRepositorio.cambiarContrasena(contrasenaHash, id_usuario);
    }
}
