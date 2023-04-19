module com.facturpdr.aplicacion {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;

    opens com.facturpdr.aplicacion to javafx.fxml;
    exports com.facturpdr.aplicacion;
    exports com.facturpdr.aplicacion.auth.controladores;
}