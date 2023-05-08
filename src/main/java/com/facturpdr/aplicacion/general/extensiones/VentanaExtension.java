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

    public void eliminar(String nombreEscena) {
        if (!escenas.containsKey(nombreEscena)) {
            return;
        }

        escenas.remove(nombreEscena);
    }

    public void cerrar() {
        if (ventanaActual != null) {
            ventanaActual.close();
        }
    }

    private void cambiarTitulo(String titulo) {
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

        String titulo = nombreEscena.substring(nombreEscena.indexOf("/") + 1, nombreEscena.lastIndexOf(".")).replace("-", " ");
        titulo = Character.toUpperCase(titulo.charAt(0)) + titulo.substring(1);
        this.cambiarTitulo("FacturPDR - " + titulo);

        ventanaActual.show();
    }


}
