package com.facturpdr.aplicacion.general.configuraciones.controladores;

import com.facturpdr.aplicacion.general.apariencia.Temas;
import com.facturpdr.aplicacion.general.configuraciones.utilidades.choiceBoxUtil;
import com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.Optional;

import static com.facturpdr.aplicacion.general.utilidades.AlertaUtilidad.mostrarAlertaConfirmacion;

public class ControladorApariencia {
    private boolean cambios = false ;
    @FXML private ChoiceBox<String> fuenteSelecionado = new ChoiceBox<>(), theme = new ChoiceBox<>(), cursivaSelecionado = new ChoiceBox<>();
    @FXML private ChoiceBox<String> tamanoSelecionado = new ChoiceBox<>(), negritaSelecionado = new ChoiceBox<>();


    private void inicioChoiceBox() {
        choiceBoxUtil.obtieneTemas(theme);
        choiceBoxUtil.obtieneFuentes(fuenteSelecionado);
        choiceBoxUtil.obtieneSize(tamanoSelecionado);
        choiceBoxUtil.istrue(cursivaSelecionado);
        choiceBoxUtil.istrue(negritaSelecionado);
    }
    @FXML public void controlTema(ActionEvent event) {

        String seleccionado = theme.getValue();
        theme.setValue(seleccionado);

        if(seleccionado != Temas.getTemaActual()) {
            cambios = true ;
        } else  {
            cambios = false ;
        }

    }
    @FXML public void restableceValoresDefault(ActionEvent event) {
        Optional<ButtonType> result = mostrarAlertaConfirmacion("Confirmación", "Restablecer valores por defecto",
                "¡Atención! Se perderán todos los cambios realizados hasta el momento. ¿Desea continuar?",
                new ButtonType("Sí"),
                new ButtonType("No"));

        if(result.isPresent()) {
            if (result.get().getText().equals("Sí")) {
                Temas.Seleccion("Default");
            }
        }
    }


    @FXML public void initialize() {
        inicioChoiceBox();
    }
}
