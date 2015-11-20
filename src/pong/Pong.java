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
    final double TAM_PALAS = 40;
    final double RADIO_B = 2.5;
    
    double direccionX = 0;    
    double direcBolaY = 0;
    double direcJug1Y = 0;
    double direcJug2Y = 0;
              
    double ladoAlto = 50;
    double ladoBajo = 350; 
    double ladoDcho = 500;
    double ladoIzq = 0;
        
    double jug1Y = 0;
    double jug2Y = 0;
    
    double posBX = 0;
    double posBY = 0;
    
    int marcador_1 = 0;
    int marcador_2 = 0;
    
    int zona_1 = (int)(posBY - jug1Y) / 10;
    int zona_2 = (int)(posBY - jug2Y) / 10;
   
    
    public void start(Stage primaryStage) {
        
        // Create scene
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.BLACK);
        primaryStage.setTitle("Basic Invaders FX");
        primaryStage.setScene(scene);
        primaryStage.show();       
        // Creación de la bola
        Circle bola = new Circle(6);
        bola.setTranslateX(WORLD_WIDTH / 2);
        bola.setTranslateY(WORLD_HEIGHT / 2);
        bola.setFill(Color.WHITE);
        root.getChildren().add(bola);
        // Creación de los jugadores
        Rectangle jugador1 = new Rectangle (5, TAM_PALAS);
        jugador1.setTranslateX(10);
        jugador1.setTranslateY(180);
        jugador1.setFill(Color.WHITE);
        root.getChildren().add(jugador1);
        
        Rectangle jugador2 = new Rectangle (5, TAM_PALAS);
        jugador2.setTranslateX(490);
        jugador2.setTranslateY(180);
        jugador2.setFill(Color.WHITE);
        root.getChildren().add(jugador2);       
        // Creación marcadores
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
        public void handle(long now) {
            // Posición X inicial de la bola
            posBX = bola.getTranslateX();
            posBX += direccionX;
            bola.setTranslateX(posBX);
            // Posición Y inicial de la bola
            posBY = bola.getTranslateY();
            posBY += direcBolaY;
            bola.setTranslateY(posBY);               
            // Posición inicial JUGADOR 1                
            jug1Y = jugador1.getTranslateY();
            jug1Y += direcJug1Y;
            jugador1.setTranslateY(jug1Y);
            //  Posición inicial JUGADOR 2                
            jug2Y = jugador2.getTranslateY();
            jug2Y += direcJug2Y;
            jugador2.setTranslateY(jug2Y);
            
            // Dirección de la bola al chocar con JUGADOR 2              
            if(posBX == jugador2.getTranslateX()){
                direccionX = -2;
                if(posBY >= jug2Y && posBY <= jug2Y + TAM_PALAS){
                    direcJug2Y = -2;  
                    // Zonas
                    if(posBY == zona_1){
                        direcJug2Y = -2;
                    }
//                    if(posBY >= jug2Y && zona_1 == 1){
//                        direcJug2Y = -2;
//                    }
//                    if(posBY >= jug2Y && zona_1 == 2){
//                        direcJug2Y = -2;
//                    }
//                    if(posBY >= jug2Y && zona_1 == 3){
//                        direcJug2Y = -2;
//                    }
                }else{ 
                    bola.setTranslateX(WORLD_WIDTH / 2);
                    bola.setTranslateY(WORLD_HEIGHT / 2);
                    // Posición inicial de la bola
                    direccionX = 0;
                    direcBolaY= 0;
                    // Parada de las palas
                    direcJug1Y = 0;
                    direcJug2Y = 0;
                    // Incremento de marcador al fallar
                    marcador_1 ++;
                    score1.setText(Integer.toString(marcador_1));
                }
            }
            // Dirección de la bola al chocar con JUGADOR 1
            if(posBX == jugador1.getTranslateX()){
                direccionX = 2;                     
                if(posBY >= jug1Y && posBY <= jug1Y + TAM_PALAS){
                   direcJug1Y = 2;  
//                   if(posBY == jug1Y && zona_2 == 0){
//                        direcJug1Y = 3;
//                    }
//                    if(posBY >= jug1Y && zona_2 == 1){
//                        direcJug2Y = 2;
//                    }
//                    if(posBY >= jug1Y && zona_2 == 2){
//                        direcJug1Y = 2;
//                    }
//                    if(posBY >= jug1Y && zona_2 == 3){
//                        direcJug1Y = 3;
//                    }
                }else{ 
                    bola.setTranslateX(WORLD_WIDTH / 2);
                    bola.setTranslateY(WORLD_HEIGHT / 2);
                    direccionX = 0;
                    direcBolaY= 0;
                    direcJug1Y = 0;
                    direcJug2Y = 0;
                    // Incremento de marcador al fallar
                    marcador_2 ++;
                    score2.setText(Integer.toString(marcador_2));
                }          
            }
            // Choque de bola con las pared superior e inferior del juego
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
        }
            
    }.start(); 
     
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case S:
                    if(jug1Y == ladoAlto){
                       direcJug1Y = 0;
                    }else{
                        direcJug1Y = -2;
                    }
                    break;                    
                case X:                   
                    if(jug1Y == ladoBajo){
                       direcJug1Y = 0;                        
                    }else{ 
                       direcJug1Y = 2;
                    }
                    break;                   
                case UP:
                    if(jug2Y == ladoAlto){
                       direcJug2Y = 0;
                    }else{
                        direcJug2Y = -2;
                    }                    
                    break;                    
                case DOWN:
                     if(jug2Y == ladoBajo){
                       direcJug2Y = 0;                        
                    }else{ 
                       direcJug2Y = 2;
                    }
                    break;                
                case SPACE:
                    if (posBX == WORLD_WIDTH / 2 && posBY == WORLD_HEIGHT / 2){
                        direccionX = 2;
                        direcBolaY = 2;
                    }                   
                    break;
            }   
            
        }
    });

    }
    
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