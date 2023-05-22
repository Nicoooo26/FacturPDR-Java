package com.facturpdr.aplicacion.sesiones.servicios;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.sesiones.utilidades.JWTUtilidad;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

public class SesionServicio {
    UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void init() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        if (obtenerID() != -1) {
            ventana.cambiarEscena("inicio/inicio");
        } else {
            cerrarSesion();
        }
    }

    public int obtenerID() {
        String token = PreferenciaUtilidad.obtenerJWT();
        if (token == null) return -1;

        DecodedJWT jwt = JWTUtilidad.verificar(token);
        if (jwt == null) return -1;

        int id_usuario = jwt.getClaim("id_usuario").asInt();

        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(id_usuario);
        if (usuario == null) return -1;

        return id_usuario;
    }

    public void cerrarSesion() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        PreferenciaUtilidad.eliminarPrefencias();
        ventana.cambiarEscena("auth/iniciar-sesion");
    }

    public void iniciarSesion(String token) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        PreferenciaUtilidad.eliminarPrefencias();
        PreferenciaUtilidad.establecerJWT(token);

        ventana.cambiarEscena("inicio/inicio");
    }
}
