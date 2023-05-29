/**
 * El paquete `com.facturpdr.aplicacion.general.utilidades` contiene clases que proporcionan utilidades generales para la aplicación.
 */
package com.facturpdr.aplicacion.general.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * La clase `ConfiguracionUtilidad` proporciona métodos para acceder a la configuración de la aplicación a través de un archivo de propiedades.
 */
public class ConfiguracionUtilidad {
    private static final Properties propiedades = new Properties();

    static {
        try (InputStream entrada = ConfiguracionUtilidad.class.getResourceAsStream("/com/facturpdr/aplicacion/configuracion.properties")) {
            propiedades.load(entrada);
        } catch (IOException ignore) { }
    }

    /**
     * Verifica si el archivo de configuración existe.
     *
     * @return `true` si el archivo de configuración existe, `false` en caso contrario.
     */
    public static boolean existeArchivo() {
        return ConfiguracionUtilidad.class.getResourceAsStream("/com/facturpdr/aplicacion/configuracion.properties") != null;
    }

    /**
     * Obtiene el valor de una propiedad de configuración.
     *
     * @param nombrePropiedad El nombre de la propiedad.
     * @return El valor de la propiedad especificada.
     */
    public static String obtenerValor(String nombrePropiedad) {
        return propiedades.getProperty(nombrePropiedad);
    }

}