/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rav.view;

import com.rav.util.Node;
import com.rav.util.NodeArray;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

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

    public GUI(Stage primaryStage, NodeArray nodeArray, ArrayList<Node> inputs) throws Exception {
        this.nodeArray = nodeArray;
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
        pane.setBottom(inputCanvas);

        Button btnTrain = new Button("Train SOM");
        btnTrain.setOnAction((event) -> {
            trainSOM();
        });

        Button btnQuit = new Button("Quit");

        btnQuit.setOnAction((event) -> {
            System.exit(0);
        });

        VBox vb = new VBox(btnTrain, btnQuit);
        vb.setSpacing(20d);
        pane.setRight(vb);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        nodeWidth = canvas.getWidth() / nodeArray.getxLength();
        nodeHeight = canvas.getHeight() / nodeArray.getyLength();
        draw();
        drawInputs(inputs);
    }

    private void trainSOM() {
    }

    private void drawInputs(ArrayList<Node> inputs) {
        for (int j = 0; j < inputs.size(); j++) {
            Node n = inputs.get(j);
            System.out.println(n.getWeight(0) +  " " + n.getWeight(1)+  " " + n.getWeight(2));
            gcInput.setFill(Color.rgb(n.getWeight(0), n.getWeight(1), n.getWeight(2)));
            gcInput.fillRect(j * (gcInput.getCanvas().getWidth() / inputs.size()) , 0, nodeWidth, nodeHeight);
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