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

    InferenceGraph ig = new InferenceGraph();

    // crear los nodos (tablas) 
    InferenceGraphNode Robo = BayesNetHelper.createNode(ig, "robo", "robo", "norobo");
    InferenceGraphNode Terremoto = BayesNetHelper.createNode(ig, "terremoto", "terremoto", "noterremoto");
    InferenceGraphNode Alarma = BayesNetHelper.createNode(ig, "alarma", "alarma", "noalarma");
    InferenceGraphNode Juanllama = BayesNetHelper.createNode(ig, "juanllama", "alarma", "noalarma");
    InferenceGraphNode Mariallama = BayesNetHelper.createNode(ig, "mariallama", "alarma", "noalarma");

    public void alarma() {

        //crear relaciones entre los nodos
        ig.create_arc(Robo, Alarma);
        ig.create_arc(Terremoto, Alarma);
        ig.create_arc(Alarma, Juanllama);
        ig.create_arc(Alarma, Mariallama);

        //asignar los valores  de las elecciones
        BayesNetHelper.setProbabilityValues(Robo, 0.001, 0.999);
        BayesNetHelper.setProbabilityValues(Terremoto, 0.002, 0.998);

        BayesNetHelper.setProbabilityValues(Alarma, "robo", "terremoto", 0.95, 0.05);
        BayesNetHelper.setProbabilityValues(Alarma, "robo", "noterremoto", 0.94, 0.06);
        BayesNetHelper.setProbabilityValues(Alarma, "norobo", "terremoto", 0.29, 0.71);
        BayesNetHelper.setProbabilityValues(Alarma, "norobo", "noterremoto", 0.001, 0.999);

        BayesNetHelper.setProbabilityValues(Juanllama, "alarma", 0.90, 0.1);
        BayesNetHelper.setProbabilityValues(Juanllama, "noalarma", 0.05, 0.95);

        BayesNetHelper.setProbabilityValues(Mariallama, "alarma", 0.70, 0.30);
        BayesNetHelper.setProbabilityValues(Mariallama, "noalarma", 0.01, 0.99);


        /*
        BayesNetHelper.setProbabilityValues(Juanllama, 0.90, 0.05);
        BayesNetHelper.setProbabilityValues(Mariallama, 0.70, 0.01);
         */
        //guardar la operacion de la libreria en variable double
        /*
        belief = BayesNetHelper.getBelief(ig, Juanllama);
        belief1 = BayesNetHelper.getBelief(ig, Mariallama);
         */
    }

    public void setMariaSi() {
        Mariallama.set_observation_value("alarma");
    }

    public void setMariaNo() {
        Mariallama.set_observation_value("noalarma");

    }

    public void setJuanSi() {
        Juanllama.set_observation_value("alarma");
    }

    public void setJuanNo() {
        Juanllama.set_observation_value("noalarma");
    }

    public void alert() {
        belief = BayesNetHelper.getBelief(ig, Terremoto);
        belief1 = BayesNetHelper.getBelief(ig, Robo);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Probabilidad de Alarma");
        alert.setHeaderText((belief * 100) + "% de terremoto\n" + (belief1 * 100) + "% de robo");
        alert.setContentText("Estas son las posibilidades de los siniestros, \nsi crees necesario, ponte seguro y busca ayuda");
        alert.showAndWait();
    }

    public void declarateVaiables() {
        Alarma.set_observation_value("terremoto");
        Alarma.set_observation_value("robo");
        /*
        Juanllama.set_observation_value("alarma");
        Mariallama.set_observation_value("alarma");
         */
        
        System.out.println("nivel 1:");
        belief = BayesNetHelper.getBelief(ig, Terremoto);
        System.out.println("la probabilidad de que suceda terremoto:" + belief);
        belief1 = BayesNetHelper.getBelief(ig, Robo);
        System.out.println("la probabilidad de que suceda un robo:" + belief1);
        System.out.println("");
    }

}
