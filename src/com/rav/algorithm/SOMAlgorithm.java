/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.algorithm;

import com.rav.util.Node;
import com.rav.util.NodeArray;
import com.rav.view.GUI;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author ravindu kaluarachchi
 */
public class SOMAlgorithm {

    private int NO_OF_ITERATIONS = 500;
    private double INITIAL_RADIUS;
    private double TIME_CONSTANT;
    private double INITIAL_LEARNING_RATE = 1;

    private NodeArray nodeArray;
    //private ArrayList<Node> inputs;

    public void train(NodeArray nodeArray, ArrayList<Node> inputs,GUI gui) {
        this.nodeArray = nodeArray;
        INITIAL_RADIUS = Math.max(nodeArray.getxLength(), nodeArray.getyLength()) / 2;
        TIME_CONSTANT = NO_OF_ITERATIONS / Math.log(INITIAL_RADIUS);
        double learningRate = INITIAL_LEARNING_RATE;
        double neighbourhoodRadius;
      //  System.out.println("TIME_CONSTANT : " + TIME_CONSTANT);
        for (int i = 0; i < NO_OF_ITERATIONS; i++) {
            neighbourhoodRadius = INITIAL_RADIUS * Math.exp(-i / TIME_CONSTANT);
       //     System.out.println("neighbourhoodRadius : " + neighbourhoodRadius);
            for (Node input : inputs) {
                Node bmu = getBMU(input);
               // System.out.println( input +" bmu: " + bmu);
                for (int j = 0; j < nodeArray.getxLength(); j++) {
                    for (int k = 0; k < nodeArray.getyLength(); k++) {
                        Node neighbourNode = nodeArray.get(j, k);
                        double distanceFromBMU = calculateDistance(bmu, neighbourNode);
                        if (distanceFromBMU <= neighbourhoodRadius) {
                      //  System.out.println(distanceFromBMU + " <=  " +neighbourhoodRadius);
                            updateWeights(input, neighbourNode, learningRate,distanceFromBMU);
                        }
                    }
                }
            }
           gui.refresh(i + 1);
           learningRate = INITIAL_LEARNING_RATE * Math.exp(- i /NO_OF_ITERATIONS);
        }
    }

    private void updateWeights(Node input,Node node,double learningRate,double distanceFromBMU ){
        Double influence = Math.exp( - Math.pow(distanceFromBMU, 2)/(2 * Math.pow(learningRate, 2))) ;
        //System.out.println(influence + " : " + influence.intValue());
        for (int i = 0; i < node.getWeightCount(); i++) {
            
            Double newWeight = node.getWeight(i) + influence *  learningRate * (input.getWeight(i) - node.getWeight(i));            
           // System.out.println(newWeight +  " > " + newWeight.intValue() );
            node.setWeight(i, newWeight.intValue());
        }
    }
    
    private Node getBMU(Node input) {
        Node bmu = null;
        double distance = 9999999;
        for (int i = 0; i < nodeArray.getxLength(); i++) {
            for (int j = 0; j < nodeArray.getyLength(); j++) {
                double euclideanDistance = calculateEuclideanDistance(input, nodeArray.get(i, j));
                if (euclideanDistance < distance) {
                    distance = euclideanDistance;
                    bmu = nodeArray.get(i, j);
                }
            }
        }
        return bmu;
    }

    private double calculateEuclideanDistance(Node node1, Node node2) {
        double distance = 0;
        for (int i = 0; i < node1.getWeightCount(); i++) {
            distance += Math.pow(node1.getWeight(i) - node2.getWeight(i), 2);
        }
        distance = Math.sqrt(distance);
        return distance;
    }
    
    private double calculateDistance(Node node1, Node node2) {
        
        return Math.sqrt(Math.pow(node1.getX() - node2.getX(), 2) + Math.pow(node1.getY() - node2.getY(), 2));
    }
}
