package com.facturpdr.aplicacion;

import com.facturpdr.aplicacion.auth.repositorios.AuthRepositorio;
import com.facturpdr.aplicacion.general.configuraciones.BDConfiguracion;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.application.Application;
import javafx.stage.Stage;
import org.mariadb.jdbc.Connection;

public class Inicio extends Application {

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        String sistema = System.getProperty("os.name");
        if (sistema.startsWith("Windows")) {
            AlertaUtilidad.error("Error de sistema operativo", "La aplicación solo se puede ejecutar en un sistema operativo Windows");
            return;
        }

        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("auth/iniciar-sesion.fxml");
        ventana.cambiarTitulo("FacturPDR - Iniciar Sesion");

        AuthRepositorio.crear("raul.diego@varimadrid.es", "123456");
    }

    @Override
    public void stop() throws Exception {

    }

    public static void main(String[] args) {
        launch(Inicio.class, args);
    }
}