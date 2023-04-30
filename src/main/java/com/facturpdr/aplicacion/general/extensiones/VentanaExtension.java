package com.facturpdr.aplicacion.general.extensiones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VentanaExtension {
    private Map<String, Scene> escenas = new HashMap<>();
    private Stage ventanaActual;

    private static VentanaExtension instancia;

    private VentanaExtension() {}

    public static VentanaExtension obtenerInstancia() {
        if (instancia == null) {
            instancia = new VentanaExtension();
        }
        return instancia;
    }

    private void crear(String nombreEscena) {
        if (escenas.containsKey(nombreEscena)) {
            return;
        }

        String path = getClass().getResource("").toString().replace("general/extensiones/", "escenas/");

        Parent cargar = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new URL(path + nombreEscena));
            cargar = fxmlLoader.load();
        } catch (IOException e) {
            return;
        }

        Scene escena = new Scene(cargar, 1000, 600);
        escenas.put(nombreEscena, escena);
    }


    public Stage obtener() {
        return ventanaActual;
    }

    public void cerrar(String nombreEscena) {
        if (!escenas.containsKey(nombreEscena)) {
            return;
        }

        escenas.remove(nombreEscena);
    }

    public void cambiarTitulo(String titulo) {
        if (ventanaActual != null) {
            ventanaActual.setTitle(titulo);
        }
    }

    public void cambiarEscena(String nombreEscena) {
        if (!escenas.containsKey(nombreEscena)) {
            this.crear(nombreEscena);
        }

        ventanaActual = ventanaActual == null ? new Stage() : ventanaActual;
        Scene nuevaEscena = escenas.get(nombreEscena);

        if (nuevaEscena == null) {
            return;
        }

        ventanaActual.setScene(nuevaEscena);
        ventanaActual.setResizable(false);
        ventanaActual.show();
    }

    public void cambiaInicio(String nombreEscena) {
        try {
            String path = getClass().getResource("").toString().replace("general/extensiones/", "escenas/");
            FXMLLoader loader = new FXMLLoader(new URL(path + nombreEscena));
            Parent root = loader.load();
            lateralControlador.getControlador().getBorderPane().setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
