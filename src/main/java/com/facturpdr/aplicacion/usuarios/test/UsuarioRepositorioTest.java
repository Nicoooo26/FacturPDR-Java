package com.facturpdr.aplicacion.usuarios.test;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UsuarioRepositorioTest {

    private UsuarioRepositorio usuarioRepositorio;

    @Before
    public void setUp() {
        usuarioRepositorio = new UsuarioRepositorio();
    }

    @Test
    public void obtenerUsuarios_DebeDevolverListaNoVacia() {
        List<Usuario> usuarios = usuarioRepositorio.obtenerUsuarios();
        Assert.assertFalse("La lista de usuarios no debe estar vacía", usuarios.isEmpty());
    }

    @Test
    public void obtenerUsuarioCorreo_Existente_DebeDevolverUsuarioCorrecto() {
        String correo = "ejemplo@correo.com";
        Usuario usuario = usuarioRepositorio.obtenerUsuarioCorreo(correo);
        Assert.assertNotNull("El usuario no debe ser nulo", usuario);
        Assert.assertEquals("El correo electrónico debe coincidir", correo, usuario.getCorreoElectronico());
    }

    @Test
    public void obtenerUsuarioCorreo_NoExistente_DebeDevolverNull() {
        String correo = "correo@inexistente.com";
        Usuario usuario = usuarioRepositorio.obtenerUsuarioCorreo(correo);
        Assert.assertNull("El usuario debe ser nulo", usuario);
    }

    // Agrega más pruebas para los demás métodos de la clase UsuarioRepositorio...

}
