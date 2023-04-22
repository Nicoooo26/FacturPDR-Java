package com.facturpdr.aplicacion.general.apariencia;

import com.facturpdr.aplicacion.inicio.controladores.lateralControlador;
import com.facturpdr.aplicacion.inicio.controladores.cabeceroControlador;
import com.facturpdr.aplicacion.inicio.utilidades.estableceInicio;

public class Temas {

    static private String temaActual;

    public static void Seleccion(String tema) {
        switch (tema) {
            case "Dark":
                temaActual = "Dark";
                Temas.Dark(lateralControlador.getControlador() , cabeceroControlador.getControlador() );
                break;
            case "Light":
                temaActual = "Light";
                Temas.Light(lateralControlador.getControlador() , cabeceroControlador.getControlador());
                break;
            default:
                temaActual = "Default";
                Temas.Default(lateralControlador.getControlador() ,  cabeceroControlador.getControlador());
                break;
        }
    }


    public static void setTemaActual(String temaActual) {
        Temas.temaActual = temaActual;
    }


    public static String getTemaActual() {
        return temaActual;
    }

    /* Colores por defecto del panel lateral*/
    public static void Default(lateralControlador pl , cabeceroControlador cabecero) {

        estableceInicio.setColorCabecero(cabecero.getCabecero(), Colores.getColorgreen() ) ;

        //Colores del Panel Lateral.
        estableceInicio.setColorPanelLateral(pl.getPanel_Lateral() , Colores.getColorclarogris() );

        estableceInicio.setColorBotones(pl.getBoton_Home() , Colores.getColorblanco() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoFacturas() , Colores.getColorverde() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoEmpleados() , Colores.getColorverde() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoClientes() , Colores.getColorverde() );
        estableceInicio.setColorBotones(pl.getBoton_Configuracion() , Colores.getColorverde() );

        //Color de los textos del panel lateral.
        estableceInicio.setColorTexto(pl.getTextGestionClientes() , Colores.getColorblanco());
        estableceInicio.setColorTexto(pl.getTextGestionEmpleados(), Colores.getColorblanco());
        estableceInicio.setColorTexto(pl.getTextGestionFacturas() , Colores.getColorblanco());
    }

    public static void Light(lateralControlador pl , cabeceroControlador cabecero) {
        // Colores del cabecero
        estableceInicio.setColorCabecero(cabecero.getCabecero(), Colores.getColorblanco() ) ;

        // Colores del Panel Lateral
        estableceInicio.setColorPanelLateral(pl.getPanel_Lateral() , Colores.getColorblanco() );
        estableceInicio.setColorBotones(pl.getBoton_Home() , Colores.getColoroscurogris() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoFacturas() , Colores.getColoroscurogris() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoEmpleados() , Colores.getColoroscurogris() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoClientes() , Colores.getColoroscurogris() );
        estableceInicio.setColorBotones(pl.getBoton_Configuracion() , Colores.getColoroscurogris() );

        // Color de los textos del panel lateral
        estableceInicio.setColorTexto(pl.getTextGestionClientes() , Colores.getColorclarogris());
        estableceInicio.setColorTexto(pl.getTextGestionEmpleados(), Colores.getColorclarogris());
        estableceInicio.setColorTexto(pl.getTextGestionFacturas() , Colores.getColorclarogris());
    }


    public static void Dark(lateralControlador pl , cabeceroControlador cabecero) {
        // Colores del cabecero
        estableceInicio.setColorCabecero(cabecero.getCabecero() , Colores.getColorgrey() );

        // Colores del panel lateral
        estableceInicio.setColorPanelLateral(pl.getPanel_Lateral() , Colores.getColorgrey());

        estableceInicio.setColorBotones(pl.getBoton_Home(), Colores.getColorgreen() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoFacturas(), Colores.getColorgreen() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoEmpleados(), Colores.getColorgreen() );
        estableceInicio.setColorBotones(pl.getBoton_ListadoClientes(), Colores.getColorgreen() );
        estableceInicio.setColorBotones(pl.getBoton_Configuracion(), Colores.getColorgreen() );

        // Color de los textos del panel lateral
        estableceInicio.setColorTexto(pl.getTextGestionClientes() , Colores.getColornegro());
        estableceInicio.setColorTexto(pl.getTextGestionEmpleados(), Colores.getColornegro());
        estableceInicio.setColorTexto(pl.getTextGestionFacturas() , Colores.getColornegro());

        //Convertimos todas las imagenes al color negro
        //Como tal lo que hacemos es establecer el brillo y saturacion a cero.

    }
}

