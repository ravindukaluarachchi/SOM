/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.util;

/**
 *
 * @author ravindu kaluarachchi
 */
public class NodeArray {
    private Node[][] nodes;
    private Integer xLength;
    private Integer yLength;
    
    public NodeArray(int x,int y,int weightCount){
        xLength = x;
        yLength = y;
        nodes = new Node[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {                
                nodes[i][j] = Node.initializeRandom(weightCount,i,j);
            }
        }
    }

    public Integer getxLength() {
        return xLength;
    }

    public void setxLength(Integer xLength) {
        this.xLength = xLength;
    }

    public Integer getyLength() {
        return yLength;
    }

    public void setyLength(Integer yLength) {
        this.yLength = yLength;
    }
    
    public Node get(int x,int y){
        return nodes[x][y];
    }
}
