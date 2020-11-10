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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.redAlarma;

/**
 * FXML Controller class
 *
 * @author gustavo
 */
public class ViewAlarmaController implements Initializable {

    redAlarma ra;
    private TextArea txtProbabilidad;
    @FXML
    private RadioButton rbSiJuan;
    @FXML
    private ToggleGroup llamaJuangroup;
    @FXML
    private RadioButton rbNoJuan;
    @FXML
    private RadioButton rbSiMaria;
    @FXML
    private ToggleGroup llamaMariagroup;
    @FXML
    private RadioButton rbNoMaria;
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
    }

    @FXML
    private void btnCalcularAction(ActionEvent event) {
        ra = new redAlarma();
        ra.alarma();
        ra.declarateVaiables();
// Ceder opcion a belief
        if (rbSiJuan.isSelected() == true) {
            ra.setJuanSi();
        }

        if (rbNoJuan.isSelected() == true) {
            ra.setJuanNo();
        }

        // opciones Maria
// Ceder opcion a belief
        if (rbSiMaria.isSelected() == true) {
            ra.setMariaSi();
        }
        if (rbNoMaria.isSelected() == true) {
            ra.setMariaNo();
        }

           ra.alert();
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
