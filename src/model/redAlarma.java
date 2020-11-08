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
public class redAlarma {

    public double belief;
    public double belief1;

    InferenceGraphNode Robo;
    InferenceGraphNode Terremoto;
    InferenceGraphNode Alarma;
    InferenceGraphNode Juanllama;
    InferenceGraphNode Mariallama;

    InferenceGraph ig = new InferenceGraph();

    public void alarma() {

        // crear los nodos (tablas) 
        Robo = BayesNetHelper.createNode(ig, "Robo", "si", "no");
        Terremoto = BayesNetHelper.createNode(ig, "terremoto", "si", "no");
        Alarma = BayesNetHelper.createNode(ig, "alarma", "siAlarma", "noAlarma");
        Juanllama = BayesNetHelper.createNode(ig, "juanllama", "siAlarmaJuan", "noAlarmajuan");
        Mariallama = BayesNetHelper.createNode(ig, "mariallama", "siAlarmaMaria", "noAlarmaMaria");

        //crear relaciones entre los nodos
        ig.create_arc(Robo, Alarma);
        ig.create_arc(Terremoto, Alarma);
        ig.create_arc(Alarma, Juanllama);
        ig.create_arc(Alarma, Mariallama);

        //asignar los valores  de las elecciones
        BayesNetHelper.setProbabilityValues(Terremoto, 0.001, 0.999);
        BayesNetHelper.setProbabilityValues(Robo, 0.002, 0.998);

        BayesNetHelper.setProbabilityValues(Alarma, "robo", "terremoto", 0.95, 0.05);
        BayesNetHelper.setProbabilityValues(Alarma, "robo", "noTerremoto", 0.94, 0.06);
        BayesNetHelper.setProbabilityValues(Alarma, "noRobo", "terremoto", 0.29, 0.71);
        BayesNetHelper.setProbabilityValues(Alarma, "noRobo", "noTerremoto", 0.001, 0.998);

        BayesNetHelper.setProbabilityValues(Juanllama, "siAlarmaJuan", 0.90, 0.1);
        BayesNetHelper.setProbabilityValues(Juanllama, "noAlarmajuan", 0.05, 0.95);

        BayesNetHelper.setProbabilityValues(Mariallama, "siAlarmaMaria", 0.70, 0.30);
        BayesNetHelper.setProbabilityValues(Mariallama, "noAlarmaMaria", 0.01, 0.99);

        //guardar la operacion de la libreria en variable double
        belief = BayesNetHelper.getBelief(ig, Juanllama);
        belief1 = BayesNetHelper.getBelief(ig, Mariallama);

    }
//resultados aun erroneos
    public void setMariaSi() {
        Mariallama.set_observation_value("siAlarmaMaria");
        belief = BayesNetHelper.getBelief(ig, Juanllama);
        belief1 = BayesNetHelper.getBelief(ig, Mariallama);
    }

    public void setMariaNo() {
        Mariallama.set_observation_value("noAlarmaMaria");
        belief = BayesNetHelper.getBelief(ig, Juanllama);
        belief1 = BayesNetHelper.getBelief(ig, Mariallama);
    }

    public void setJuanSi() {
        Juanllama.set_observation_value("siAlarmaJuan");
        belief = BayesNetHelper.getBelief(ig, Juanllama);
        belief1 = BayesNetHelper.getBelief(ig, Mariallama);
    }

    public void setJuanNo() {
        Juanllama.set_observation_value("noAlarmaJuan");
        belief = BayesNetHelper.getBelief(ig, Juanllama);
        belief1 = BayesNetHelper.getBelief(ig, Mariallama);
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Probabilidad de Alarma");
        alert.setHeaderText((belief * 100) + "% de terremoto\n" + (belief1 * 100) + "% de robo");
        alert.setContentText("Estas son las posibilidades de los siniestros, \nsi crees necesario, ponte seguro y busca ayuda");
        alert.showAndWait();
    }

}
