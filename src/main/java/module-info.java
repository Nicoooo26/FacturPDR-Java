module com.facturpdr.aplicacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.mariadb.jdbc;


    opens com.facturpdr.aplicacion to javafx.graphics,javafx.fxml;
    opens com.facturpdr.aplicacion.inicio.controladores to javafx.fxml;
    opens com.facturpdr.aplicacion.auth.controladores to javafx.fxml;
    exports com.facturpdr.aplicacion;
    exports com.facturpdr.aplicacion.auth.controladores;
    opens com.facturpdr.aplicacion.general.configuraciones.controladores to javafx.fxml;
}