/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javabayes.Helpers.BayesNetHelper;
import javabayes.InferenceGraphs.InferenceGraph;
import javabayes.InferenceGraphs.InferenceGraphNode;
import javafx.scene.control.Alert;

/**
 *
 * @author gustavo
 */
public class redInfarto {

    public double belief;

    InferenceGraphNode Deporte;
    InferenceGraphNode Alimentacion;
    InferenceGraphNode Presion;
    InferenceGraphNode Fumador;
    InferenceGraphNode Infarto;

    InferenceGraph ig = new InferenceGraph();

    public void infarto() {
        Deporte = BayesNetHelper.createNode(ig, "deporte", "si", "no");
        Alimentacion = BayesNetHelper.createNode(ig, "alimentacion", "equilibrada", "noEquilibrada");
        Presion = BayesNetHelper.createNode(ig, "presion", "alta", "normal");
        Fumador = BayesNetHelper.createNode(ig, "Fumador", "si", "no");
        Infarto = BayesNetHelper.createNode(ig, "infarto", "si", "no");

        ig.create_arc(Deporte, Presion);
        ig.create_arc(Alimentacion, Presion);
        ig.create_arc(Presion, Infarto);
        ig.create_arc(Fumador, Infarto);

        BayesNetHelper.setProbabilityValues(Deporte, 0.1, 0.9);
        BayesNetHelper.setProbabilityValues(Alimentacion, 0.4, 0.6);
        BayesNetHelper.setProbabilityValues(Fumador, 0.4, 0.6);

        BayesNetHelper.setProbabilityValues(Presion, "equilibrada", "si", 0.01, 0.99);
        BayesNetHelper.setProbabilityValues(Presion, "noEquilibrada", "si", 0.2, 0.8);
        BayesNetHelper.setProbabilityValues(Presion, "equilibrada", "no", 0.25, 0.75);
        BayesNetHelper.setProbabilityValues(Presion, "noEquilibrada", "no", 0.7, 0.3);

        BayesNetHelper.setProbabilityValues(Infarto, "alta", "si", 0.8, 0.2);
        BayesNetHelper.setProbabilityValues(Infarto, "normal", "si", 0.6, 0.4);
        BayesNetHelper.setProbabilityValues(Infarto, "alta", "no", 0.7, 0.3);
        BayesNetHelper.setProbabilityValues(Infarto, "normal", "no", 0.3, 0.7);

        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setDeporteSi() {
        Deporte.set_observation_value("si");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setDeporteNo() {
        Deporte.set_observation_value("no");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setAlimentacionEquilibrada() {
        Alimentacion.set_observation_value("equilibrada");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setAlimentacionNoEquilibrada() {
        Alimentacion.set_observation_value("noEquilibrada");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setPresionNormal() {
        Presion.set_observation_value("normal");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setPresionAlta() {
        Presion.set_observation_value("alta");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setFumadorSi() {
        Fumador.set_observation_value("si");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void setFumadorNo() {
        Fumador.set_observation_value("no");
        belief = BayesNetHelper.getBelief(ig, Infarto);
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Probabilidad de infarto");
        alert.setHeaderText((belief * 100) + "%");
        alert.setContentText("Esta es la probabilidad de que sufra un infarto \nSi tiene dudas o sintomas, consulte a su medico");
        alert.showAndWait();
    }

}
