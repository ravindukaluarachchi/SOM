/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.algorithm;

import com.rav.util.Node;
import com.rav.util.NodeArray;
import java.util.ArrayList;

/**
 *
 * @author ravindu kaluarachchi
 */
public class SOMAlgorithm {
    private int NO_OF_ITERATIONS = 500;
    private double INITIAL_RADIUS;
    private double TIME_CONSTANT;
    private double INITIAL_LEARNING_RATE = 0.07;
    
    private NodeArray nodeArray;
    private ArrayList<Node> inputs;
    
    public void train(NodeArray nodeArray,ArrayList<Node> inputs){
        INITIAL_RADIUS = Math.max(nodeArray.getxLength(), nodeArray.getyLength()) / 2;
        TIME_CONSTANT = NO_OF_ITERATIONS / Math.log(INITIAL_RADIUS);
        double learningRate = INITIAL_LEARNING_RATE;
        double neighbourhoodRadius;
        for (int i = 0; i < NO_OF_ITERATIONS; i++) {
            neighbourhoodRadius = INITIAL_RADIUS * Math.exp(-i / TIME_CONSTANT);
            for (Node input : inputs) {
                
            }
        }
    }
    
    private Node getBMU(Node input){
        Node bmu = null;
        double distance = 0;
        for (int i = 0; i < nodeArray.getxLength(); i++) {
            for (int j = 0; j < nodeArray.getyLength(); j++) {
                //double euclidianD
            }
        }
        return bmu;
    }
}
