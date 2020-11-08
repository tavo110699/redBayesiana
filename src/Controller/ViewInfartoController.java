/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.redInfarto;

/**
 * FXML Controller class
 *
 * @author gustavo
 */
public class ViewInfartoController implements Initializable {

    public redInfarto ri;

    @FXML
    private ToggleGroup Deportes;
    @FXML
    private ToggleGroup Alimentacion;
    @FXML
    private ToggleGroup Presion;
    @FXML
    private ToggleGroup Fumador;
    @FXML
    private RadioButton rbSiDeporte;
    @FXML
    private RadioButton rbNoDeporte;
    @FXML
    private RadioButton rbEquilibradaAlimentacion;
    @FXML
    private RadioButton rbNoEquilibradaAlimentacion;
    @FXML
    private RadioButton rbAltaPresion;
    @FXML
    private RadioButton rbBajaPresion;
    @FXML
    private RadioButton rbSiFuma;
    @FXML
    private RadioButton rbNoFuma;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnMin;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ri = new redInfarto();
    }

    @FXML
    private void btnCalcularInfartoAction(ActionEvent event) {
        ri = new redInfarto();
        ri.infarto();

        if (rbSiDeporte.isSelected() == true) {
            ri.setDeporteSi();
        } else {
            ri.setDeporteNo();
        }

        if (rbEquilibradaAlimentacion.isSelected() == true) {
            ri.setAlimentacionEquilibrada();
        } else {
            ri.setAlimentacionNoEquilibrada();
        }

        if (rbSiFuma.isSelected() == true) {
            ri.setFumadorSi();
        } else {
            ri.setFumadorNo();
        }

        if (rbAltaPresion.isSelected() == true) {
            ri.setPresionAlta();
        } else {
            ri.setPresionNormal();
        }

        ri.alert();
    }
    @FXML
    private void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minAction(ActionEvent event) {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setIconified(true);
    }
}
