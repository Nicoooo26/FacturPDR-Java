package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.apariencia.Temas;
import com.facturpdr.aplicacion.general.apariencia.Colores;

import com.facturpdr.aplicacion.inicio.utilidades.estableceInicio;
import javafx.fxml.FXML;

public class inicioControlador {



    @FXML
    public void initialize() {
        Temas.Seleccion("Dark");
    }
}
