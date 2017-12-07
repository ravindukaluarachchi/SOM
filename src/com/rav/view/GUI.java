/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.view;

import com.rav.algorithm.SOMAlgorithm;
import com.rav.util.Node;
import com.rav.util.NodeArray;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import som.SOM;
import static som.SOM.NODE_ARRAY_HEIGHT;
import static som.SOM.NODE_ARRAY_WIDTH;
import static som.SOM.NO_OF_WEIGHTS;

/**
 *
 * @author ravindu kaluarachchi
 */
public class GUI {

    private GraphicsContext gc;
    private GraphicsContext gcInput;
    private NodeArray nodeArray;
    private Double nodeHeight;
    private Double nodeWidth;
    private ArrayList<Node> inputs;
    Stage primaryStage;
    public GUI(Stage primaryStage, NodeArray nodeArray, ArrayList<Node> inputs) throws Exception {
        this.nodeArray = nodeArray;
        this.inputs = inputs;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("SOM");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        Group root = new Group();

        BorderPane pane = new BorderPane();
        root.getChildren().add(pane);

        Canvas canvas = new Canvas(500, 500);
        gc = canvas.getGraphicsContext2D();
        pane.setCenter(canvas);

        
        Canvas inputCanvas = new Canvas(500, 100);
        gcInput = inputCanvas.getGraphicsContext2D();
        HBox hb = new HBox(new Label("Inputs : "), inputCanvas);
        pane.setBottom(hb);

        Button btnTrain = new Button("Train SOM");
        btnTrain.setMinWidth(200);
        btnTrain.setOnAction((event) -> {
            trainSOM();
            draw();
        });

        Button btnReset = new Button("Reset Nodes");
        btnReset.setMinWidth(200);
        btnReset.setOnAction((event) -> {
            this.nodeArray = new NodeArray(SOM.NODE_ARRAY_WIDTH, SOM.NODE_ARRAY_HEIGHT, SOM.NO_OF_WEIGHTS);
            draw();
        });
        
        Button btnQuit = new Button("Quit");
        btnQuit.setMinWidth(200);
        btnQuit.setOnAction((event) -> {
            System.exit(0);
        });

        VBox vb = new VBox(btnTrain, btnReset, btnQuit);
        vb.setSpacing(20d);
        pane.setRight(vb);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        nodeWidth = canvas.getWidth() / nodeArray.getxLength();
        nodeHeight = canvas.getHeight() / nodeArray.getyLength();
        draw();
        drawInputs(inputs);

    }

    public void refresh(Integer iteration) {
        try {
            Thread.sleep(10);
            Platform.runLater(() -> {
                draw();
                primaryStage.setTitle("SOM : " + iteration);
                
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void trainSOM() {
        new Thread(() -> {
            new SOMAlgorithm().train(nodeArray, inputs, this);
        }).start();

    }

    private void drawInputs(ArrayList<Node> inputs) {
        gc.setStroke(Color.BLACK);
        for (int j = 0; j < inputs.size(); j++) {
            Node n = inputs.get(j);            
            gcInput.setFill(Color.rgb(n.getWeight(0), n.getWeight(1), n.getWeight(2)));
            gcInput.fillRect(j * (gcInput.getCanvas().getWidth() / inputs.size()), 0, nodeWidth, nodeHeight);
            gcInput.strokeRect(j * (gcInput.getCanvas().getWidth() / inputs.size()), 0, nodeWidth, nodeHeight);
        }
    }

    private void draw() {
        for (int i = 0; i < nodeArray.getxLength(); i++) {
            for (int j = 0; j < nodeArray.getyLength(); j++) {
                Node n = nodeArray.get(i, j);
                gc.setFill(Color.rgb(n.getWeight(0), n.getWeight(1), n.getWeight(2)));
                gc.fillRect(i * nodeWidth, j * nodeHeight, nodeWidth, nodeHeight);
            }
        }
    }

}
