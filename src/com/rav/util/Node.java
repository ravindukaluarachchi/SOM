/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.util;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ravindu kaluarachchi
 */
public class Node {
    ArrayList<Integer> weights;
    Integer x;
    Integer y;
    
    private Node(){
        weights = new ArrayList<>();
    }
    
    public static Node initializeRandom(int weightCount){
        Node n = new Node();
        Random r = new Random();
        for (int i = 0; i < weightCount; i++) {
            n.weights.add(r.nextInt(256) );
        }
        return n;
    }
    
    public static Node initialize(int... weight){
        Node n = new Node();
        Random r = new Random();
        for (int i = 0; i < weight.length; i++) {
            n.weights.add(weight[i]);
        }
        return n;
    }
    
    public static Node initialize(double... weight){
        Node n = new Node();        
        for (int i = 0; i < weight.length; i++) {
            n.weights.add(new Double(weight[i] * 255).intValue());
        }
        return n;
    }
    
    public Integer getWeight(int i){
        return weights.get(i);
    }
    
    public Integer getWeightCount(){
        return weights.size();
    }
}
