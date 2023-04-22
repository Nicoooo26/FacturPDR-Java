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
import javafx.scene.text.Text;

public class estableceInicio {

    /*ColorPicker te devuelve los colores en formato hexadecimal
     * Con este metodo , convertimos el color hexadecimal en RGB */

    public static String ConvertirRGB(ColorPicker color) {
        String rgb = "#" + color.getValue().toString().substring(2);
        return rgb ;
    }

    public static void CambiarColorLateral(lateralControlador pl ,ColorPicker colorpiker) {
        String rgb = ConvertirRGB(colorpiker);
        pl.getPanel_Lateral().setStyle("-fx-background-color: " + rgb + ";");
    }

    public static void CambiarColorCabecero(cabeceroControlador cabecero , ColorPicker color) {
        String rgb = ConvertirRGB(color);
        cabecero.getCabecero().setStyle("-fx-background-color: " + rgb + ";");
    }

    /* Imagenes oscuras */
    public static void ImagenesOscuras(ImageView imageView) {
        // Crea un objeto ColorAdjust y establece la saturación y brillo a cero
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1.0); // Establece la saturación a cero
        colorAdjust.setBrightness(-1.0); // Establece el brillo a cero

        // Establece el efecto ColorAdjust en el ImageView
        imageView.setEffect(colorAdjust);
    }

    public static void setColorCabecero(HBox superior , String color) {
        superior.setStyle("-fx-background-color: " + color + ";");

    }

    public static void setColorPanelLateral(AnchorPane Lateral , String color) {
        Lateral.setStyle("-fx-background-color: " + color + ";");

    }
    public static void setColorTexto(Text texto , String color) {
        Paint Color = Paint.valueOf(color);
        texto.setFill(Color);
    }

    public static void setColorBotones(Button boton , String color ) {
        boton.setStyle("-fx-text-fill: " + color + ";");
    }
}
