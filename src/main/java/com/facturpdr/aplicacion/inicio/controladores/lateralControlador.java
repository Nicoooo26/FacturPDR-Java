package com.facturpdr.aplicacion.inicio.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import javafx.fxml.Initializable;

public class lateralControlador implements Initializable {

    private static lateralControlador instancia = null;
    private static lateralControlador controlador = null;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerPane;

    /* Elementos del Panel Lateral.*/
    @FXML
    private AnchorPane Panel_Lateral ;

    /* Botones del panel lateral.*/
    @FXML
    private Button Boton_Configuracion ;

    @FXML
    private Button Boton_ListadoFacturas ;

    @FXML
    private Button Boton_ListadoEmpleados ;

    @FXML
    private Button Boton_ListadoClientes ;

    @FXML
    private Button Boton_GestionVehiculos ;

    @FXML
    private Button Boton_Home ;

    /* Texto del panel lateral. */
    @FXML
    private Text TextGestionClientes ;

    @FXML
    private Text TextGestionEmpleados ;

    @FXML
    private Text TextGestionFacturas ;

    @FXML
    private Text TextGestionVehiculos ;


    public BorderPane getBorderPane() {
        return borderPane;
    }

    public  AnchorPane getCenterPane() {
        return centerPane;
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

    public Button getBoton_GestionVehiculos() {
        return Boton_GestionVehiculos;
    }
    /* Getters de los textos. */
    public Text getTextGestionVehiculos() {
        return TextGestionVehiculos;
    }

    public Text getTextGestionClientes() {
        return TextGestionClientes;
    }

    public Text getTextGestionEmpleados() {
        return TextGestionEmpleados;
    }

    public Text getTextGestionFacturas() {
        return TextGestionFacturas;
    }

    public void setControlador(lateralControlador Controlador) {
        this.controlador = Controlador;
    }

    public static lateralControlador getControlador() {
        getInstancia();
        return controlador;
    }

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

