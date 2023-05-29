/**
 * El paquete `com.facturpdr.aplicacion.configuraciones.servicios` contiene clases relacionadas con los servicios de configuración de la aplicación.
 */
package com.facturpdr.aplicacion.configuraciones.servicios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;

/**
 * La clase `ConfiguracionServicio` proporciona métodos para obtener atributos de configuración del usuario.
 */
public class ConfiguracionServicio {

    private static final UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
    private static Usuario user;

    /**
     * Obtiene el valor de un atributo de configuración del usuario.
     *
     * @param atributo El nombre del atributo a obtener.
     * @return El valor del atributo especificado.
     * @throws IllegalArgumentException Si el atributo no es válido.
     */
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
