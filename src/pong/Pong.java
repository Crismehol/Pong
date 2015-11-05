/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author 1DAW11
 */
public class Pong extends Application {
    
    final int WORLD_WIDTH = 500;
    final int WORLD_HEIGHT = 400;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Create scene
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.BLACK);
        primaryStage.setTitle("Basic Invaders FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // import javafx.scene.shape.Rectangle
        Circle bola = new Circle (200, 10, 5);
        bola.setFill(Color.WHITE);
        root.getChildren().add(bola);
        
        
//        Rectangle jugador1 = new Rectangle (50, 10, 10, 40);
//        jugador1.setFill(Color.WHITE);
//        root.getChildren().add(jugador1);
//        
//        Rectangle jugador2 = new Rectangle (450, 10, 10, 40);
//        jugador2.setFill(Color.WHITE);
//        root.getChildren().add(jugador2);
    
        
     new AnimationTimer() {
            @Override
            public void handle(long now) {
                double posX = bola.getTranslateX();
                posX--;
                bola.setTranslateX(posX);
                
                
            }   
     }.start(); 
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
