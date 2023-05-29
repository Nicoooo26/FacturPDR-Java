package com.facturpdr.aplicacion.configuraciones.test;

import com.facturpdr.aplicacion.configuraciones.servicios.ConfiguracionServicio;
import org.junit.Assert;
import org.junit.Test;

public class ConfiguracionServicioTest {

    @Test
    public void testObtenerAtributo_nombreUsuario() {
        String atributo = "nombreUsuario";
        String expected = "john.doe";

        Object result = ConfiguracionServicio.obtenerAtributo(atributo);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testObtenerAtributo_correoElectronico() {
        String atributo = "correoElectronico";
        String expected = "john.doe@example.com";

        Object result = ConfiguracionServicio.obtenerAtributo(atributo);

        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testObtenerAtributo_atributoInvalido() {
        String atributo = "atributoInvalido";

        ConfiguracionServicio.obtenerAtributo(atributo);
    }
}
