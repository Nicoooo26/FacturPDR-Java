package com.facturpdr.aplicacion.facturas.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorFactura {

    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();
    @FXML public void añadir_factura(ActionEvent event) {
        ventana.ventanaEmergente("facturas/crear-factura");
    }
}
