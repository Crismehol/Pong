/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author 1DAW11
 */
public class Pong extends Application {
    
    final int WORLD_WIDTH = 500;
    final int WORLD_HEIGHT = 400;
    
    double direccionX = 0;
    
    double direcBolaY = 0;
    double direcJug1Y = 0;
    double direcJug2Y = 0;
    final double TAM_PALAS = 30;
    final double RADIO_B = 2.5;
           
    double ladoAlto = 50;
    double ladoBajo = 400; 
    double ladoDcho = 500;
    double ladoIzq = 0;
    int marcador_1 = 0;
    int marcador_2 = 0;

    public void start(Stage primaryStage) {
        
        // Create scene
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.BLACK);
        primaryStage.setTitle("Basic Invaders FX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // PELOTA
        Circle bola = new Circle(5);
        bola.setTranslateX(WORLD_WIDTH / 2);
        bola.setTranslateY(WORLD_HEIGHT / 2);
        bola.setFill(Color.WHITE);
        root.getChildren().add(bola);
        
        // JUGADORES
        Rectangle jugador1 = new Rectangle (5, 30);
        jugador1.setTranslateX(10);
        jugador1.setTranslateY(180);
        jugador1.setFill(Color.WHITE);
        root.getChildren().add(jugador1);
        
        Rectangle jugador2 = new Rectangle (5, 30);
        jugador2.setTranslateX(490);
        jugador2.setTranslateY(180);
        jugador2.setFill(Color.WHITE);
        root.getChildren().add(jugador2);
    
//         MARCADORES
        Label score1 = new Label(String.valueOf(marcador_1));
        score1.setTranslateX(125);
        score1.setTranslateY(20);
        score1.setTextFill(Color.WHITE);
        root.getChildren().add(score1);
        
        Label score2 = new Label(String.valueOf(marcador_2));
        score2.setTranslateX(350);
        score2.setTranslateY(20);
        score2.setTextFill(Color.WHITE);
        root.getChildren().add(score2);
        
        
    new AnimationTimer() {
            @Override
        public void handle(long now) {
            double posBX = bola.getTranslateX();
            posBX += direccionX;
            bola.setTranslateX(posBX);
                
            double posBY = bola.getTranslateY();
            posBY += direcBolaY;
            bola.setTranslateY(posBY);
                
            //JUGADOR 1                
            double jug1Y = jugador1.getTranslateY();
            jug1Y += direcJug1Y;
            jugador1.setTranslateY(jug1Y);
                
            //JUGADOR 2                
            double jug2Y = jugador2.getTranslateY();
            jug2Y += direcJug2Y;
            jugador2.setTranslateY(jug2Y);
              
            // Dirección de la bola               
            if(posBX == jugador2.getTranslateX()){
                direccionX = -2;
                if(posBY >= jug2Y && posBY <= jug2Y + TAM_PALAS){
                    direcJug2Y = -2;                                      
                }else{ 
                    direccionX = 2;
                }
            }
            if(posBX == jugador1.getTranslateX()){
                direccionX = 2;                     
                if(posBY >= jug1Y && posBY <= jug1Y + TAM_PALAS){
                   direcJug1Y = 2;                                      
                }else{ 
                   direccionX = -2;
                }          
            }
          
            if (posBY == ladoBajo){
                direcBolaY = -2;
            } 
            if (posBY == ladoAlto){
                direcBolaY = 2;
            }     
              
            // JUGADORES       
            if(jug1Y == ladoBajo){
                direcJug1Y = 0;
            }
            if (jug1Y == ladoAlto){
                direcJug1Y = 0;
            }                 
            if(jug2Y == ladoBajo){
                direcJug2Y = 0;
            }
            if (jug2Y == ladoAlto){
                direcJug2Y = 0;
            }
            
            // MARCADORES
            if(posBX == ladoIzq){
                marcador_2 ++;
                score2.setText(Integer.toString(marcador_2));
                
            }
            if(posBX == ladoDcho){
                marcador_1 ++;
                score1.setText(Integer.toString(marcador_1));    
            }
        }
            
    }.start(); 
     
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case S:
                    direcJug1Y = -2;
                    break;
                case X:
                    direcJug1Y = 2;
                    break;
                    
                case UP:
                    direcJug2Y = -2;
                    break;
                case DOWN:
                    direcJug2Y = 2;
                    break;
                
                case SPACE:
                    direccionX = 2;
                    direcBolaY = 2;
                    direcJug1Y = 2;
                    direcJug2Y = 2;
                    break;
            }   
            
        }
    });

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
// si posXbola = posXpala
//      si posYbola >= posYpala "y además"
//         posYbola<= pozYpala+tamYpala
//              ((TOCANDO LA PALA))
//      si no((NO TOCANDO LA BOLA))
//
// PARA LOS MARCADORES
// Label o text
// score1 = new label()