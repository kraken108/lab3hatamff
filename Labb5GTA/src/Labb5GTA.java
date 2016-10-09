
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
import model.*;

/**
 *
 * @author Jakob
 */
public class Labb5GTA extends Application {
    
    private AnimationTimer timer;
    private final long FRAME_NS = 25_000_000;
    
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
            
            if(game.getState() == GameState.GAMEOVER){
                showAlert("Game over!");
                stop();
            }       
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
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        game = new Game();
        
        gc.drawImage(game.getBackground(), 0, 0);
        ArrayList<Player> thePlayers = game.getPlayers();
        for(Player p : thePlayers){
            WritableImage croppedImage = new WritableImage(p.getSprite().getPixelReader(),0,0,64,64);
            gc.drawImage(croppedImage, p.getX(), p.getY());
        }
        
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
