package com.facturpdr.aplicacion.inicio.utilidades;

import com.facturpdr.aplicacion.inicio.controladores.lateralControlador;
import com.facturpdr.aplicacion.inicio.controladores.cabeceroControlador;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class estableceInicio {

    /* Método que convierte un color en formato hexadecimal a RGB */
    public static String convertirHexadecimalA_RGB(ColorPicker colorPicker) {
        String rgb = "#" + colorPicker.getValue().toString().substring(2);
        return rgb;
    }

    /* Cambia el color de fondo del panel lateral según el color seleccionado en el ColorPicker */
    public static void cambiarColorLateral(lateralControlador lateralControlador, ColorPicker colorPicker) {
        String rgb = convertirHexadecimalA_RGB(colorPicker);
        lateralControlador.getPanel_Lateral().setStyle("-fx-background-color: " + rgb + ";");
    }

    /* Cambia el color de fondo del cabecero según el color seleccionado en el ColorPicker */
    public static void cambiarColorCabecero(cabeceroControlador cabeceroControlador, ColorPicker colorPicker) {
        String rgb = convertirHexadecimalA_RGB(colorPicker);
        cabeceroControlador.getCabecero().setStyle("-fx-background-color: " + rgb + ";");
    }

    /* Aplica un efecto de imagen oscura a un conjunto de ImageView */
    public static void aplicarEfectoImagenOscura(ImageView... imagen) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1.0);
        colorAdjust.setBrightness(-1.0);

        for (ImageView img : imagen) {
            img.setEffect(colorAdjust);
        }
    }

    /* Cambia el color de fondo de un HBox */
    public static void setColorCabecero(HBox hbox, String color) {
        hbox.setStyle("-fx-background-color: " + color + ";");
    }

    /* Cambia el color de fondo de un AnchorPane */
    public static void setColorPanelLateral(AnchorPane anchorPane, String color) {
        anchorPane.setStyle("-fx-background-color: " + color + ";");
    }

    /* Cambia el color de un Text */
    public static void setColorTexto(Text texto, String color) {
        Paint colorTexto = Paint.valueOf(color);
        texto.setFill(colorTexto);
    }

    /* Cambia el color de texto de un Button */
    public static void setColorBotones(Button boton, String color) {
        boton.setStyle("-fx-text-fill: " + color + ";");
    }

    public static void estableFuenteFamilia(String fuente , Object... object ) {
        for ( Object objecto : object) {
            if(objecto instanceof  Button) {
                Button fuentePersonalizado = (Button) objecto ;
                fuentePersonalizado.setStyle("-fx-font-family: '" + fuente + "'; ");
            }
            else {
                Text fuentePersonalizado = (Text) objecto ;
                fuentePersonalizado.setStyle("-fx-font-family: '" + fuente + "'; ");
            }
        }


    }

}
