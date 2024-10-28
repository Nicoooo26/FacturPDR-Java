package com.facturpdr.aplicacion.usuarios.repositorios;

import com.facturpdr.aplicacion.auth.modelos.Usuario;
import com.facturpdr.aplicacion.usuarios.repositorios.UsuarioRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class UsuarioRepositorioTest {

    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void setUp() {
        usuarioRepositorio = new UsuarioRepositorio();
    }

    @Test
    public void obtenerUsuarios_DebeDevolverListaNoVacia() {
        List<Usuario> usuarios = usuarioRepositorio.obtenerUsuarios();
        Assertions.assertFalse(usuarios.isEmpty(), "La lista de usuarios no debe estar vacía");
    }

    @Test
    public void obtenerUsuarioCorreo_Existente_DebeDevolverUsuarioCorrecto() {
        String correo = "ejemplo@correo.com";
        Usuario usuario = usuarioRepositorio.obtenerUsuarioCorreo(correo);
        Assertions.assertNotNull(usuario, "El usuario no debe ser nulo");
        Assertions.assertEquals(correo, usuario.getCorreoElectronico(), "El correo electrónico debe coincidir");
    }

    @Test
    public void obtenerUsuarioCorreo_NoExistente_DebeDevolverNull() {
        String correo = "correo@inexistente.com";
        Usuario usuario = usuarioRepositorio.obtenerUsuarioCorreo(correo);
        Assertions.assertNull(usuario, "El usuario debe ser nulo");
    }

    // Agrega más pruebas para los demás métodos de la clase UsuarioRepositorio...

}
