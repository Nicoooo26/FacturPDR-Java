package com.facturpdr.aplicacion;

import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.general.utilidades.ConfiguracionUtilidad;
import com.facturpdr.aplicacion.sesiones.servicios.SesionServicio;
import javafx.application.Application;
import javafx.stage.Stage;

public class Inicio extends Application {
    @Override
    public void start(Stage escenarioPrincipal) {
        SesionServicio sesionServicio = new SesionServicio();

        String sistema = System.getProperty("os.name");
        if (!sistema.startsWith("Windows")) {
            AlertaUtilidad.error("Error de sistema operativo", "La aplicación solo se puede ejecutar en un sistema operativo Windows");
            return;
        }

        if (!ConfiguracionUtilidad.existeArchivo()) {
            AlertaUtilidad.error("Error de archivo de configuración", "Lo sentimos, no se ha encontrado el archivo de configuración necesario para ejecutar esta aplicación");
            return;
        }

        sesionServicio.inicio();
    }

    public static void main(String[] args) {
        launch(Inicio.class, args);
    }
}