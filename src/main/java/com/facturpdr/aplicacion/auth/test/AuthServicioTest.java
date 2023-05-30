package com.facturpdr.aplicacion.auth.test;

import com.facturpdr.aplicacion.auth.excepciones.CorreoElectronicoExistenteException;
import com.facturpdr.aplicacion.auth.excepciones.CrearUsuarioException;
import com.facturpdr.aplicacion.auth.excepciones.NombreUsuarioExistenteException;
import com.facturpdr.aplicacion.auth.servicios.AuthServicio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthServicioTest {
    private AuthServicio authServicio;

    @Before
    public void configuracion() {
        authServicio = new AuthServicio();
    }

    @Test
    public void registrar_UsuarioValido_RegistraUsuario() throws NombreUsuarioExistenteException, CorreoElectronicoExistenteException, CrearUsuarioException {
        // Arrange
        String nombreUsuario = "nuevoUsuario";
        String correoElectronico = "nuevoUsuario@example.com";
        String contrasena = "password123";

        // Act
        authServicio.registrar(nombreUsuario, correoElectronico, contrasena);

        // Assert - Puedes agregar aserciones apropiadas basadas en tus requisitos específicos
        // Por ejemplo, puedes verificar si el usuario se ha registrado correctamente en el repositorio.
    }

    @Test(expected = NombreUsuarioExistenteException.class)
    public void registrar_NombreUsuarioExistente_LanzaNombreUsuarioExistenteException() throws NombreUsuarioExistenteException, CorreoElectronicoExistenteException, CrearUsuarioException {
        // Arrange
        String nombreUsuarioExistente = "usuarioExistente";
        String correoElectronico = "nuevoUsuario@example.com";
        String contrasena = "password123";

        // Act
        authServicio.registrar(nombreUsuarioExistente, correoElectronico, contrasena);

        // Assert - Se espera que se lance NombreUsuarioExistenteException
    }

    @Test(expected = CorreoElectronicoExistenteException.class)
    public void registrar_CorreoElectronicoExistente_LanzaCorreoElectronicoExistenteException() throws NombreUsuarioExistenteException, CorreoElectronicoExistenteException, CrearUsuarioException {
        // Arrange
        String nombreUsuario = "nuevoUsuario";
        String correoElectronicoExistente = "usuarioExistente@example.com";
        String contrasena = "password123";

        // Act
        authServicio.registrar(nombreUsuario, correoElectronicoExistente, contrasena);

        // Assert - Se espera que se lance CorreoElectronicoExistenteException
    }

    @Test(expected = CrearUsuarioException.class)
    public void registrar_ErrorCrearUsuario_LanzaCrearUsuarioException() throws NombreUsuarioExistenteException, CorreoElectronicoExistenteException, CrearUsuarioException {
        // Arrange
        String nombreUsuario = "nuevoUsuario";
        String correoElectronico = "nuevoUsuario@example.com";
        String contrasena = "password123";

        // Simula un error en la creación del usuario en el repositorio
        // Por ejemplo, puedes configurar el repositorio para que devuelva false al crear el usuario

        // Act
        authServicio.registrar(nombreUsuario, correoElectronico, contrasena);

        // Assert - Se espera que se lance CrearUsuarioException
    }

    @Test
    public void iniciarSesion_CredencialesValidas_IniciaSesionExitoso() {
        // Arrange
        String correoElectronico = "usuario@example.com";
        String contrasena = "password123";

        // Act
        int idUsuario = authServicio.iniciarSesion(correoElectronico, contrasena);

        // Assert - Puedes agregar aserciones apropiadas basadas en tus requisitos específicos
        // Por ejemplo, puedes verificar si el ID del usuario es válido.
        Assert.assertNotEquals(-1, idUsuario);
    }

    @Test
    public void iniciarSesion_CredencialesInvalidas_IniciaSesionFallido() {
        // Arrange
        String correoElectronico = "usuario@example.com";
        String contrasenaIncorrecta = "contrasenaIncorrecta";

        // Act
        int idUsuario = authServicio.iniciarSesion(correoElectronico, contrasenaIncorrecta);

        // Assert - Se espera que el inicio de sesión falle y devuelva -1 como ID del usuario
        Assert.assertEquals(-1, idUsuario);
    }
}
