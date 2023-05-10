package com.facturpdr.aplicacion.general.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracionUtilidad {
    private static final Properties propiedades = new Properties();

    static {
        try (InputStream entrada = ConfiguracionUtilidad.class.getResourceAsStream("/com/facturpdr/aplicacion/configuracion.properties")) {
            propiedades.load(entrada);
        } catch (IOException ignore) { }
    }

    public static boolean existeArchivo() {
        return ConfiguracionUtilidad.class.getResourceAsStream("/com/facturpdr/aplicacion/configuracion.properties") != null;
    }

    public static String obtenerValor(String nombrePropiedad) {
        return propiedades.getProperty(nombrePropiedad);
    }

}
