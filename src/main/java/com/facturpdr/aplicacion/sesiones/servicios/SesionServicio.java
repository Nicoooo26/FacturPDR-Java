package com.facturpdr.aplicacion.sesiones.servicios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

public class SesionServicio {
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();
    private final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

    public void inicio() {
        int IDUsuario = PreferenciaUtilidad.obtenerIDUsuario();
        if (IDUsuario == -1) {
            ventana.cambiarEscena("auth/iniciar-sesion");
            return;
        }

        Usuario usuario = usuarioRepositorio.obtenerUsuarioID(IDUsuario);
        if (usuario == null) {
            PreferenciaUtilidad.eliminarPrefencias();
            ventana.cambiarEscena("auth/iniciar-sesion");
            return;
        }

        ventana.cambiarEscena("inicio/inicio");
    }

    public void cerrarSesion() {
        PreferenciaUtilidad.eliminarPrefencias();
        ventana.cambiarEscena("auth/iniciar-sesion");
    }

    public void iniciarSesion(int IDUsuario) {
        PreferenciaUtilidad.eliminarPrefencias();
        PreferenciaUtilidad.establecerIDUsuario(IDUsuario);

        ventana.cambiarEscena("inicio/inicio");
    }
}
