/**
 * El módulo `com.facturpdr.aplicacion` contiene la configuración de dependencias del proyecto.
 */
module com.facturpdr.aplicacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.mariadb.jdbc;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.prefs;
    requires com.jfoenix;
    requires java.mail;
    requires com.oracle.database.jdbc;
    requires junit;

    opens com.facturpdr.aplicacion.empleados.modelos to javafx.base;
    opens com.facturpdr.aplicacion.clientes.modelos to javafx.base;
    opens com.facturpdr.aplicacion to javafx.graphics, javafx.fxml;
    opens com.facturpdr.aplicacion.inicio.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.configuraciones.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.facturas.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.empleados.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.clientes.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.auth.controladores to javafx.fxml;

    /**
     * Exporta los paquetes para que estén disponibles para otros módulos o aplicaciones.
     */
    exports com.facturpdr.aplicacion;
    exports com.facturpdr.aplicacion.auth.controladores;
    exports com.facturpdr.aplicacion.auth.excepciones;
    exports com.facturpdr.aplicacion.auth.modelos;
    exports com.facturpdr.aplicacion.auth.servicios;
    exports com.facturpdr.aplicacion.auth.utilidades;

    exports com.facturpdr.aplicacion.clientes.controladores;
    exports com.facturpdr.aplicacion.clientes.modelos;

    exports com.facturpdr.aplicacion.configuraciones.controladores;
    exports com.facturpdr.aplicacion.configuraciones.servicios;


    exports com.facturpdr.aplicacion.general.utilidades;
    exports com.facturpdr.aplicacion.general.extensiones;

    exports com.facturpdr.aplicacion.inicio.controladores;

    exports com.facturpdr.aplicacion.sesiones.servicios;
    exports com.facturpdr.aplicacion.sesiones.utilidades;

    exports com.facturpdr.aplicacion.usuarios.excepciones;
    exports com.facturpdr.aplicacion.usuarios.repositorios;

    exports com.facturpdr.aplicacion.usuarios.servicios;
    exports com.facturpdr.aplicacion.configuraciones.test;

}
