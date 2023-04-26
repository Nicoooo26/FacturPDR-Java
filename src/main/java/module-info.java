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
<<<<<<< HEAD
=======
    opens com.facturpdr.aplicacion.auth.controladores to javafx.fxml;
>>>>>>> 2d1052c1cc68a6b089416f0efce6a540f4271a05
    exports com.facturpdr.aplicacion;
    exports com.facturpdr.aplicacion.auth.controladores;
    opens com.facturpdr.aplicacion.general.configuraciones.controladores to javafx.fxml;
}