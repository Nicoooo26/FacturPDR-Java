package com.facturpdr.aplicacion.facturas.controladores;

import java.sql.*;
import java.text.SimpleDateFormat;

import com.facturpdr.aplicacion.general.extensiones.BDExtension;
import com.facturpdr.aplicacion.general.extensiones.VentanaExtension;
import javafx.fxml.*;
import javafx.scene.control.*;

/**
 * Controlador para la muestra de información de cliente.
 */
public class ControladorFactura {

    @FXML
    private Label labelID;

    @FXML
    private Label labelFechaCreacion;

    @FXML
    private Label labelIDCliente;

    @FXML
    private Label labelTamanno;

    @FXML
    private Label labelCantidad;

    @FXML
    private Label labelPrecioUnitario;

    @FXML
    private Label labelPrecioTotal;

    @FXML
    private Label labelFechaVencimiento;

    @FXML
    private Label labelTipo;

    @FXML
    private Label labelMaterial;

    @FXML
    private Label labelPintura;

    @FXML
    private Label labelAluminio;

    @FXML
    private Label labelNotas;

    @FXML
    private Label labelMatricula;

    @FXML
    private Label labelNIF;

    @FXML
    private Label labelManoDeObra;
    /**
     * Botón para volver atrás.
     */
    @FXML
    public Button btnCancelar;

    /**
     * Método para cargar los datos de un cliente según su DNI.
     *
     * @param dni DNI del cliente.
     * @throws SQLException si ocurre un error en la consulta SQL.
     */
    public void cargarDatos(String dni) throws SQLException {
        BDExtension.conectarse();
        Connection conn = BDExtension.conexion;
        String query = "SELECT ID, ID_CLIENTE, NIF_EMPLEADO, MATRICULA, COSTE_MANO_OBRA, NOTA_EXTERNA, FECHA_CREACION, FECHA_VENCIMIENTO, TIPO, MATERIAL, ALUMINIO,PINTURA,TAMANO,CANTIDAD,PRECIO_UNITARIO,PRECIOTOTAL FROM FACTURAS WHERE NIF_EMPLEADO = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                labelID.setText(rs.getString("ID"));
                labelIDCliente.setText(rs.getString("ID_CLIENTE"));
                labelNIF.setText(rs.getString("NIF_EMPLEADO"));
                labelMatricula.setText(rs.getString("MATRICULA"));
                labelManoDeObra.setText(Integer.toString(rs.getInt("COSTE_MANO_OBRA")));
                labelNotas.setText(rs.getString("NOTA_EXTERNA"));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fechaCreacionFormateada = dateFormat.format(rs.getTimestamp("FECHA_CREACION"));
                String fechaVencimientoFormateada = dateFormat.format(rs.getTimestamp("FECHA_VENCIMIENTO"));
                labelFechaCreacion.setText(fechaCreacionFormateada);
                labelFechaVencimiento.setText(fechaVencimientoFormateada);
                labelTipo.setText(rs.getString("TIPO"));
                int material = rs.getInt("MATERIAL");
                labelMaterial.setText(material == 1 ? "SI" : "NO");

                int aluminio = rs.getInt("ALUMINIO");
                labelAluminio.setText(aluminio == 1 ? "SI" : "NO");

                int pintura = rs.getInt("PINTURA");
                labelPintura.setText(pintura == 1 ? "SI" : "NO");

                labelTamanno.setText(Integer.toString(rs.getInt("TAMANO")));
                labelCantidad.setText(Integer.toString(rs.getInt("CANTIDAD")));
                labelPrecioUnitario.setText(Integer.toString(rs.getInt("PRECIO_UNITARIO")));
                labelPrecioTotal.setText(Integer.toString(rs.getInt("PRECIOTOTAL")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que se ejecuta al hacer clic en el botón de volver atrás.
     */
    @FXML
    public void clickCancelar() {
        VentanaExtension ventana = VentanaExtension.obtenerInstancia();
        ventana.cambiarEscena("facturas/facturas");
    }
}