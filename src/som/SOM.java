/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package som;

import com.rav.util.Node;
import com.rav.util.NodeArray;
import com.rav.view.GUI;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author ravindu kaluarachchi
 */
public class SOM  extends Application{
    
    private final int NODE_ARRAY_WIDTH = 40;
    private final int NODE_ARRAY_HEIGHT = 40;
    private final int NO_OF_WEIGHTS = 3;
    
    private GUI gui;
    private NodeArray nodeArray;
    private ArrayList<Node> inputs = new ArrayList<>(Arrays.asList(
            Node.initialize(Color.RED.getRed() ,Color.RED.getGreen(),Color.RED.getBlue()),
            Node.initialize(Color.GREEN.getRed(),Color.GREEN.getGreen(),Color.GREEN.getBlue()),
            Node.initialize(Color.BLUE.getRed(),Color.BLUE.getGreen(),Color.BLUE.getBlue()),
            Node.initialize(Color.BLACK.getRed(),Color.BLACK.getGreen(),Color.BLACK.getBlue()),
            Node.initialize(Color.YELLOW.getRed(),Color.YELLOW.getGreen(),Color.YELLOW.getBlue()),
            Node.initialize(Color.WHITE.getRed(),Color.WHITE.getGreen(),Color.WHITE.getBlue())
                    
        ));
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        nodeArray = new NodeArray(NODE_ARRAY_WIDTH, NODE_ARRAY_HEIGHT, NO_OF_WEIGHTS);
        gui = new GUI(primaryStage,nodeArray,inputs);
    }
    
}
