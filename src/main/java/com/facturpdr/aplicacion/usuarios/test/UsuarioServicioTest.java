package com.facturpdr.aplicacion.usuarios.test;

import com.facturpdr.aplicacion.usuarios.excepciones.CambiarContrasenaException;
import com.facturpdr.aplicacion.usuarios.excepciones.ContrasenaIgualException;
import com.facturpdr.aplicacion.usuarios.servicios.UsuarioServicio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioServicioTest {
    private UsuarioServicio usuarioServicio;

    @Before
    public void configuracion() {
        usuarioServicio = new UsuarioServicio();
    }

    @Test
    public void cambiarContrasena_ContrasenaValida_CambiaContrasena() throws CambiarContrasenaException, ContrasenaIgualException, ContrasenaIgualException {
        // Arrange
        int idUsuario = 1;
        String nuevaContrasena = "nuevacontrasena";

        // Act
        usuarioServicio.cambiarContrasena(nuevaContrasena, idUsuario);

        // Assert - Puedes agregar aserciones apropiadas basadas en tus requisitos específicos
        // Por ejemplo, podrías verificar si la contraseña se ha cambiado correctamente en el repositorio.
    }

    @Test(expected = CambiarContrasenaException.class)
    public void cambiarContrasena_IdUsuarioInvalido_LanzaCambiarContrasenaException() throws CambiarContrasenaException, ContrasenaIgualException {
        // Arrange
        int idUsuarioInvalido = -1;
        String nuevaContrasena = "nuevacontrasena";

        // Act
        usuarioServicio.cambiarContrasena(nuevaContrasena, idUsuarioInvalido);

        // Assert - Se espera que se lance CambiarContrasenaException
    }

    @Test(expected = ContrasenaIgualException.class)
    public void cambiarContrasena_MismaContrasena_LanzaContrasenaIgualException() throws CambiarContrasenaException, ContrasenaIgualException {
        // Arrange
        int idUsuario = 1;
        String mismaContrasena = "samepassword";

        // Act
        usuarioServicio.cambiarContrasena(mismaContrasena, idUsuario);

        // Assert - Se espera que se lance ContrasenaIgualException
    }
}
