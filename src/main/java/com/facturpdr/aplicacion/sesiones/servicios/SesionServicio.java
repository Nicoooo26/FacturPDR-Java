package com.facturpdr.aplicacion.sesiones.servicios;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.sesiones.utilidades.ConfiguracionUtilidad;

public class SesionServicio {

    public void cerrarSesion() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ConfiguracionUtilidad.eliminarPrefencias();
        ventana.cerrar();
    }

    public void iniciarSesion(String token) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ConfiguracionUtilidad.eliminarPrefencias();
        ConfiguracionUtilidad.establecerJWT(token);

        ventana.cambiarEscena("inicio/inicio.fxml");
        ventana.cambiarTitulo("FacturPDR - Inicio");
    }
}
