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
    InferenceGraph ig = new InferenceGraph();
    InferenceGraphNode Deporte = BayesNetHelper.createNode(ig, "deporte", "si", "no");
    InferenceGraphNode Alimentacion = BayesNetHelper.createNode(ig, "alimentacion", "equilibrada", "noequilibrada");
    InferenceGraphNode Presion = BayesNetHelper.createNode(ig, "presion", "alta", "normal");
    InferenceGraphNode Fumador = BayesNetHelper.createNode(ig, "fumador", "si", "no");
    InferenceGraphNode Infarto = BayesNetHelper.createNode(ig, "infarto", "si", "no");

    public void infarto() {

        ig.create_arc(Deporte, Presion);
        ig.create_arc(Alimentacion, Presion);
        ig.create_arc(Presion, Infarto);
        ig.create_arc(Fumador, Infarto);

        BayesNetHelper.setProbabilityValues(Deporte, 0.1, 0.9);
        BayesNetHelper.setProbabilityValues(Alimentacion, 0.4, 0.6);
        BayesNetHelper.setProbabilityValues(Fumador, 0.4, 0.6);

        BayesNetHelper.setProbabilityValues(Presion, "equilibrada", "si", 0.01, 0.99);
        BayesNetHelper.setProbabilityValues(Presion, "noequilibrada", "si", 0.2, 0.8);
        BayesNetHelper.setProbabilityValues(Presion, "equilibrada", "no", 0.25, 0.75);
        BayesNetHelper.setProbabilityValues(Presion, "noequilibrada", "no", 0.7, 0.3);

        BayesNetHelper.setProbabilityValues(Infarto, "alta", "si", 0.8, 0.2);
        BayesNetHelper.setProbabilityValues(Infarto, "normal", "si", 0.6, 0.4);
        BayesNetHelper.setProbabilityValues(Infarto, "alta", "no", 0.7, 0.3);
        BayesNetHelper.setProbabilityValues(Infarto, "normal", "no", 0.3, 0.7);

    }

    public void setDeporteSi() {
        //     Deporte.set_observation_value("si");
        Deporte.set_observation_value("si");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println(" deporte: " + belief);

    }

    public void setDeporteNo() {
//        Deporte.set_observation_value("no");
        Deporte.set_observation_value("no");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println("no deporte: " + belief);
    }

    public void setAlimentacionEquilibrada() {
        //       Alimentacion.set_observation_value("equilibrada");
        Alimentacion.set_observation_value("equilibrada");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println("alimentacion equilibrada: " + belief);
    }

    public void setAlimentacionNoEquilibrada() {
        //      Alimentacion.set_observation_value("noequilibrada");
        Alimentacion.set_observation_value("noequilibrada");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println("alimentacion no equilibrada: " + belief);
    }

    public void setPresionNormal() {
        //     Presion.set_observation_value("normal");
        Presion.set_observation_value("normal");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println("normal : " + belief);
    }

    public void setPresionAlta() {
        //      Presion.set_observation_value("alta");
        Presion.set_observation_value("alta");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println("alta : " + belief);
    }

    public void setFumadorSi() {
        //       Fumador.set_observation_value("si");
        Fumador.set_observation_value("si");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println("fume : " + belief);
    }

    public void setFumadorNo() {
        //     Fumador.set_observation_value("no");
        Fumador.set_observation_value("no");
        belief = BayesNetHelper.getBelief(ig, Infarto);
        System.out.println(" nofume : " + belief);
    }

    public void alert() {

        belief = BayesNetHelper.getBelief(ig, Infarto);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Probabilidad de infarto");
        alert.setHeaderText((belief * 100) + "%");
        alert.setContentText("Esta es la probabilidad de que sufra un infarto \nSi tiene dudas o sintomas, consulte a su medico");
        alert.showAndWait();
        System.out.println("");
    }

}
