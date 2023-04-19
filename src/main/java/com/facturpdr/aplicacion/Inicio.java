package com.facturpdr.aplicacion;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.application.Application;
import javafx.stage.Stage;

public class Inicio extends Application {
    @Override
    public void start(Stage escenarioPrincipal) {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();

        ventana.cambiarEscena("auth/iniciar-sesion.fxml");
        ventana.cambiarTitulo("FacturPDR - Iniciar Sesion");
    }

    public static void main(String[] args) {
        launch();
    }
}