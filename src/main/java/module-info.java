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

    opens com.facturpdr.aplicacion.clientes.modelos to javafx.base;
    opens com.facturpdr.aplicacion to javafx.graphics,javafx.fxml;
    opens com.facturpdr.aplicacion.inicio.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.configuraciones.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.facturas.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.clientes.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.auth.controladores to javafx.fxml;
    exports com.facturpdr.aplicacion;
    exports com.facturpdr.aplicacion.auth.controladores;
}