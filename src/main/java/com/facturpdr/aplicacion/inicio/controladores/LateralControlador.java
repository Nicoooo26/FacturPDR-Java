package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.configuraciones.controladores.ControladorInformacionBasica;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import com.facturpdr.aplicacion.sesiones.servicios.SesionServicio;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

/**
 * Controlador para el panel lateral de la aplicación.
 */
public class LateralControlador {
    private final VentanaExtension ventana = VentanaExtension.obtenerInstancia();
    private final SesionServicio sesionServicio = new SesionServicio();

    /**
     * Maneja el evento para ir a la sección de Facturas.
     */
    @FXML
    public void ManejaFacturas() {
        ventana.cambiarEscena("facturas/facturas");
    }

    /**
     * Maneja el evento para ir a la sección de Inicio.
     */
    @FXML
    public void manejarInicio() {
        ventana.cambiarEscena("inicio/inicio");
    }

    /**
     * Maneja el evento para ir a la sección de Empleados.
     */
    @FXML
    public void manejaEmpleados() {
        ventana.cambiarEscena("empleados/empleados");
    }

    /**
     * Maneja el evento para cerrar la sesión.
     */
    @FXML
    public void manejarCerrarSesion() {
        if (confirmarCambioEscena()) {
            Optional<ButtonType> cerrarSesion = AlertaUtilidad.confirmacion("Cerrar Sesión", null, "¿Estás seguro de que deseas cerrar sesión?", ButtonType.YES, ButtonType.NO);
            if (cerrarSesion.isPresent() && cerrarSesion.get() == ButtonType.YES) {
                sesionServicio.cerrarSesion();
            }
        }
    }

    /**
     * Maneja el evento para ir a la sección de Configuración.
     */
    @FXML
    public void manejarConfiguracion() {
        ventana.cambiarEscena("configuracion/configuracion");
    }

    /**
     * Maneja el evento para ir a la sección de Clientes.
     */
    @FXML
    public void manejarClientes() {
        if (confirmarCambioEscena()) ventana.cambiarEscena("clientes/clientes");
    }

    /**
     * Verifica si hay cambios sin guardar y muestra un mensaje de confirmación si los hay.
     *
     * @return true si no hay cambios sin guardar o si el usuario confirma que desea cambiar de escena, false de lo contrario.
     */
    private boolean confirmarCambioEscena() {
        ControladorInformacionBasica info = ControladorInformacionBasica.obtenerInstancia();
        if (ControladorInformacionBasica.cambiosSinGuardar) {
            info.novisibles_nomodificables();
            return info.mostrarMensajeCambiosSinGuardar();
        }
        return true;
    }

    /**
     * Método de inicialización del controlador.
     * Se llama automáticamente al cargar el archivo FXML.
     *
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    public void initialize() throws IOException {
        // Aquí puedes realizar acciones de inicialización del controlador y la vista
    }
}