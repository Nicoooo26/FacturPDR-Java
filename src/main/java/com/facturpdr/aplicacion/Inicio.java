package com.facturpdr.aplicacion;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.application.Application;
import javafx.stage.Stage;

public class Inicio extends Application {

    @Override
    public void init() {

    }

    @Override
    public void start(Stage escenarioPrincipal) {
        String sistema = System.getProperty("os.name");
        if (sistema.startsWith("Windows")) {
            AlertaUtilidad.error("Error de sistema operativo", "La aplicación solo se puede ejecutar en un sistema operativo Windows");
            return;
        }

        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("auth/iniciar-sesion.fxml");
        ventana.cambiarTitulo("FacturPDR - Iniciar Sesion");
    }

    @Override
    public void stop() {

    }

    public static void main(String[] args) {
        launch(Inicio.class, args);
    }
}