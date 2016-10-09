
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import model.*;

/**
 *
 * @author Jakob
 */
public class Labb5GTA extends Application {
    
    private AnimationTimer timer;
    private final long FRAME_NS = 1_000_000;
    private GraphicsContext gc;
    private Canvas canvas;
    private Game game;
    
    private class GameTimer extends AnimationTimer{
        private long previousNs = 0;
        
        @Override
        public void handle(long nowNs) {
            if (previousNs == 0) {
                previousNs = nowNs;
            }
            
            if(nowNs - previousNs < FRAME_NS){  
                return;
            } else {  
                previousNs = nowNs;
            }
<<<<<<< HEAD
            
            if(game.getState() == GameState.GAMEOVER){
                showAlert("Game over!");
                stop();
            }       
=======
            drawBackground();
            drawPlayers();
                  
        
>>>>>>> 6300099f07609b6479099612cb003c272e09cdef
        }
    }
    
    private void drawBackground(){
        gc.drawImage(game.getBackground(), 0, 0);
    }
    
    private void drawPlayers(){
        ArrayList<Player> thePlayers = game.getPlayers();
        for(Player p : thePlayers){
            p.tick();
            WritableImage croppedImage = new WritableImage(p.getSprite().getPixelReader(),p.getFrameX(),0,64,64);
            gc.drawImage(croppedImage, p.getX(), p.getY());
        }
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Map example");
        
        Group root = new Group();
        Scene theScene = new Scene ( root );
        primaryStage.setScene(theScene);
        
        canvas = new Canvas(1024,768);
        root.getChildren().add(canvas);
        
        gc = canvas.getGraphicsContext2D();
        
        game = new Game();
        
        timer = new GameTimer();
        timer.start();
        
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>(){
                    public void handle(KeyEvent e){
                        String code = e.getCode().toString();
                        switch(code){
                        case "A": game.getPlayer(0).setVelX(-5);
                                  game.getPlayer(0).setDirection(LookDirection.LEFT);
                                  break;
                        case "D": game.getPlayer(0).setVelX(5);
                                  game.getPlayer(0).setDirection(LookDirection.RIGHT);
                                  break;
                        case "S": game.getPlayer(0).setVelY(5);
                                  game.getPlayer(0).setDirection(LookDirection.DOWN);
                                  break;
                        case "W": game.getPlayer(0).setVelY(-5);
                                  game.getPlayer(0).setDirection(LookDirection.UP);
                                  break;
                   
                        case "LEFT": game.getPlayer(1).setVelX(-5);
                                    game.getPlayer(1).setDirection(LookDirection.LEFT);
                                    break;
                        case "DOWN": game.getPlayer(1).setVelY(5);
                                    game.getPlayer(1).setDirection(LookDirection.DOWN);
                                  break;
                        case "RIGHT": game.getPlayer(1).setVelX(5);
                                    game.getPlayer(1).setDirection(LookDirection.RIGHT);
                                  break;
                        case "UP": game.getPlayer(1).setVelY(-5);
                                   game.getPlayer(1).setDirection(LookDirection.UP);
                                   break;
                     }
                            
                        
                    }
                }
        
        
        );
        
        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>(){
                    public void handle(KeyEvent e){
                        String code = e.getCode().toString();
                        switch(code){
                        case "A": game.getPlayer(0).setVelX(0);break;
                        case "D": game.getPlayer(0).setVelX(0);break;
                        case "S": game.getPlayer(0).setVelY(0);break;
                        case "W": game.getPlayer(0).setVelY(0);break;
                   
                        case "LEFT": game.getPlayer(1).setVelX(0);break;
                        case "DOWN": game.getPlayer(1).setVelY(0);break;
                        case "RIGHT": game.getPlayer(1).setVelX(0);break;
                        case "UP": game.getPlayer(1).setVelY(0);break;
            }
                            
                        
                    }
                }
        
        
        );
        
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    public void showAlert(String message){
        if(!alert.isShowing()){
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText(message);
        alert.show();           
        }        
    }
    
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
}
