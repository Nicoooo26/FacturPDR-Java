package com.facturpdr.aplicacion.facturas.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.fxml.FXML;

public class ControladorFacturas {

    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();
    @FXML public void añadir_factura() {
        ventana.ventanaEmergente("facturas/crear-factura");
    }
}
