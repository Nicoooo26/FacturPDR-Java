package com.facturpdr.aplicacion.sesiones.servicios;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.sesiones.utilidades.PreferenciaUtilidad;

public class SesionServicio {
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
