package com.facturpdr.aplicacion.empleados.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorEmpleado {
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();

    @FXML public void manejaAñadirEmpleado(ActionEvent event) {
        ventana.ventanaEmergente("empleados/crear-empleado");
    }
}
