package com.facturpdr.aplicacion.auth.servicios;

import com.facturpdr.aplicacion.auth.excepciones.CorreoElectronicoExistenteException;
import com.facturpdr.aplicacion.auth.excepciones.CrearUsuarioException;
import com.facturpdr.aplicacion.auth.excepciones.NoVerificadoException;
import com.facturpdr.aplicacion.auth.excepciones.NombreUsuarioExistenteException;
import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.auth.utilidades.HashUtilidad;
import com.facturpdr.aplicacion.sesiones.utilidades.JWTUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

public class AuthServicio {
    UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void registrar(String nombreUsuario, String correoElectronico, String contrasena) throws NombreUsuarioExistenteException, CorreoElectronicoExistenteException, CrearUsuarioException {
        boolean existeCorreoElectronico = usuarioRepositorio.existeCorreoElectronico(correoElectronico);
        if (existeCorreoElectronico) throw new CorreoElectronicoExistenteException();

        boolean existeNombreUsuario = usuarioRepositorio.existeNombreUsuario(nombreUsuario);
        if (existeNombreUsuario) throw new NombreUsuarioExistenteException();

        String contrasenaHash = HashUtilidad.sha256(contrasena);

        boolean estaCreado = usuarioRepositorio.crearUsuario(nombreUsuario, correoElectronico, contrasenaHash);
        if (!estaCreado) throw new CrearUsuarioException();
    }

    public String iniciarSesion(String correoElectronico, String contrasena) throws NoVerificadoException {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioCorreo(correoElectronico);
        if (usuario == null) return null;

        String contrasenaHash = HashUtilidad.sha256(contrasena);
        if (!usuario.getContrasena().equals(contrasenaHash)) return null;

        if (!usuario.getEstaVerificado()) throw new NoVerificadoException();

        return JWTUtilidad.generar("auth", 3600);
    }

    public boolean estaVerificado(int id_usuario) {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) return false;

        return usuario.getEstaVerificado();
    }

    public boolean cambiarContrasena(String contrasenaNueva, int id_usuario) {
        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) return false;

        String contrasenaHash = HashUtilidad.sha256(contrasenaNueva);
        if (usuario.getContrasena().equals(contrasenaHash)) return false;

        return usuarioRepositorio.cambiarContrasena(contrasenaHash, id_usuario);
    }
}
