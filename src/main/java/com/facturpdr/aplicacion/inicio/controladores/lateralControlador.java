package com.facturpdr.aplicacion.inicio.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class lateralControlador implements Initializable {

    private static lateralControlador instancia = null;
    private static lateralControlador controlador = null;

    @FXML private BorderPane borderPane;

    @FXML private AnchorPane Panel_Lateral ;

    @FXML private Button Boton_Configuracion , Boton_ListadoFacturas , Boton_ListadoEmpleados , Boton_ListadoClientes , Boton_Home;

    @FXML private Text TextGestionClientes , TextGestionEmpleados , TextGestionFacturas;

    @FXML private ImageView ImagenHome , ImagenConfiguracion , ImagenEmpleados , ImagenFactura , ImagenCliente;

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public AnchorPane getPanel_Lateral() {
        return Panel_Lateral;
    }

    /* Getters de los botones */

    public Button getBoton_Configuracion() {
        return Boton_Configuracion;
    }

    public Button getBoton_ListadoFacturas() {
        return Boton_ListadoFacturas;
    }

    public Button getBoton_ListadoEmpleados() {
        return Boton_ListadoEmpleados;
    }

    public Button getBoton_ListadoClientes() {
        return Boton_ListadoClientes;
    }

    public Button getBoton_Home() {
        return Boton_Home;
    }

    /* Getters de los textos */
    public Text getTextGestionClientes() {return TextGestionClientes;}
    public Text getTextGestionEmpleados() {return TextGestionEmpleados;}
    public Text getTextGestionFacturas() {return TextGestionFacturas;}

    /* Getters de las imagenes*/
    public ImageView getImagenHome(){ return ImagenHome;}
    public ImageView getImagenCliente(){ return ImagenCliente;}
    public ImageView getImagenEmpleados(){ return ImagenEmpleados;}
    public ImageView getImagenConfiguracion(){ return ImagenConfiguracion;}
    public ImageView getImagenFactura(){ return ImagenFactura;}


    public void setControlador(lateralControlador Controlador) {this.controlador = Controlador;}

    public static lateralControlador getControlador() { getInstancia(); return controlador; }

    public static lateralControlador getInstancia() {
        if (instancia == null) {
            instancia = new lateralControlador();
        }
        return instancia;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setControlador(this);
    }
}