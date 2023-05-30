/**
 * El paquete `com.facturpdr.aplicacion.inicio.controladores` contiene clases que son controladores para la pantalla de inicio de la aplicación.
 */
package com.facturpdr.aplicacion.inicio.controladores;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

/**
 * La clase `InicioControlador` es un controlador para la pantalla de inicio de la aplicación.
 */
public class InicioControlador implements Initializable {
    @FXML
    private FontAwesomeIconView IconoHelp;
    @FXML
    private Label cantidadFacturas;
    @FXML
    private Label cantidadEmpleados;
    @FXML
    private Label cantidadClientes;

    public int count(String tabla) throws SQLException {
        BDExtension.conectarse();
        Connection conn = BDExtension.conexion;
        String query = "SELECT COUNT(*) FROM "+tabla;
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;
    }

    /**
     * Inicializa el controlador después de que se haya cargado el archivo FXML.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas para el objeto raíz o `null` si no está disponible.
     * @param resources Los recursos utilizados para localizar el objeto raíz o `null` si no está disponible.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cantidadClientes.setText(Integer.toString(count("CLIENTES")));
            cantidadEmpleados.setText(Integer.toString(count("EMPLEADOS")));
            cantidadFacturas.setText(Integer.toString(count("FACTURAS")));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Tooltip informacion = new Tooltip("Nombre del proyecto: FacturPDR\n" +
                "Desarrollado en: JavaFX\n" +
                "Compilador: Gradle\n" +
                "Base de datos: Oracle\n" +
                "Fecha de lanzamiento: 30/05/2023\n" +
                "Versión actual: 1.0");

        Tooltip.install(IconoHelp, informacion);

        IconoHelp.setOnMouseClicked(event -> {
            if (!informacion.isShowing()) {
                informacion.show(IconoHelp, event.getScreenX() + 30, event.getScreenY() + 30);
            } else {
                informacion.hide();
            }
        });
    }
}
