package com.facturpdr.aplicacion.configuraciones.servicios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

public class ConfiguracionServicio {

    private static final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
    private static Usuario user ;
    public static Object obtenerAtributo(String atributo) {
        user = usuarioRepositorio.obtenerUsuarioID(PreferenciaUtilidad.obtenerIDUsuario());
        switch (atributo) {
            case "nombreUsuario":
                return user.getNombreUsuario();
            case "correoElectronico":
                return user.getCorreoElectronico();
            case "contrasena":
                return user.getContrasena();
            case "fechaCreacion":
                return user.getFechaCreacion();
            default:
                throw new IllegalArgumentException("Atributo no válido: " + atributo);
        }
    }
}
