/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import model.*;

/**
 *
 * @author Jakob
 */
public class Laboration5 extends Application {
    
    private AnimationTimer timer;
    private final long FRAME_NS = 10_000_000;
    private GraphicsContext gc;
    private Canvas canvas;
    private Game game;
    private Image theMap,player1,player2,bullet;
    private FileChooser fileChooser;
    
    public static final double FOURBILLION = 4_000_000_000.0;
                                              
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
            
            double newTime = System.nanoTime();
            
            drawBackground();
            drawPlayers();
            drawBot();
            drawBullets();
            game.paintScoreboard();
            if(newTime-game.checkIfDead()>FOURBILLION)
            game.respawn();          
        }
    }
    
    private void drawBot(){
        game.followPlayer();
        WritableImage croppedImage = new WritableImage(player1.getPixelReader(),0,0,64,64);
        gc.drawImage(croppedImage, game.getBot().getPosX(), game.getBot().getPosY());
    }
    
    private void drawBackground(){
        gc.drawImage(theMap, 0, 0);
    }
    
    private void drawPlayers(){
        ArrayList<Player> thePlayers = game.getPlayers();
        for(Player p : thePlayers){
            if(p.getPlayerState() == PlayerState.ALIVE){
                p.tick();
                WritableImage croppedImage = new WritableImage(player1.getPixelReader(),p.getFrameX(),0,64,64);
                gc.drawImage(croppedImage, p.getX(), p.getY());
            }
            
        }
    }
    
    private void drawBullets(){
        game.detectHit();
        ArrayList<Player> thePlayers = game.getPlayers();
        for(Player p: thePlayers){
            ArrayList<Bullet> theBullets = p.getBullets();
            p.bulletsOutOfMap();
            for(Bullet b: theBullets){
                b.tick();
                gc.drawImage(bullet, b.getPosX(), b.getPosY());
            }
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Map example");
        
        Group root = new Group();
        Scene theScene = new Scene ( root );
        fileChooser = new FileChooser();
        primaryStage.setScene(theScene);
        MenuBar menuBar = initiateMenuBar(primaryStage);
        canvas = new Canvas(1024,768);
        root.getChildren().addAll(canvas,menuBar);
        
        gc = canvas.getGraphicsContext2D();
        
        game = new Game();
        
        theMap = new Image("images/karta.png");
        player1 = new Image("images/BigBlueGuy.png");
        player2 = new Image("images/BigRedGuy.png");
        bullet = new Image("images/BigBullet.png");
        
        timer = new GameTimer();
        timer.start();
        
        initiateKeys(theScene);
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
    
    private void saveFile(File file){
        FileOutputStream fout = null;  
                
            // Serialize to file
	    try {
	      fout = new FileOutputStream(file);
	      ObjectOutputStream ous = new ObjectOutputStream(fout); 
              //is used for communication with the file
	      ous.writeObject(game);
	      
	      System.out.println("Serializing successfully completed");
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }
	    finally {
	    	try {
	    		if(fout != null) fout.close();
	    	}
	    	catch(IOException e) {}
	    }
    }
    
    private void loadFile(File file){
        FileInputStream fin = null;
        
        try {
	    	
	      fin = new FileInputStream(file);
	      ObjectInputStream ois = new ObjectInputStream(fin);
	      
	      game = (Game) ois.readObject(); // Downcast from Object
	      
	      System.out.println("Deserializing successfully completed");
	      
	    }
	    catch (IOException e) {
	      System.out.println(e);
	    }
	    catch (ClassNotFoundException e) { // !!!
	      System.out.println("Class not found");
	    }
	    finally {
			try {
				if(fin != null) fin.close();
			}
			catch(IOException e) {}
	    }
    }
    private MenuBar initiateMenuBar(Stage primaryStage){
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem addSave = new MenuItem("Save game");
        addSave.setOnAction(new EventHandler<ActionEvent>() {
           public void handle (ActionEvent t) {
               File file = fileChooser.showSaveDialog(primaryStage);
               if(file != null){
                   saveFile(file);
               }
               file = null;
           } 
        });
        MenuItem addLoad = new MenuItem("Load game");
        addLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file!= null){
                    loadFile(file);
                }
                file = null;
            }
        });
        
        menuFile.getItems().addAll(addSave,addLoad);
        
        Menu menuGame = new Menu("Game");
        MenuItem addNew = new MenuItem("New game");
        MenuItem addPause = new MenuItem("Pause");
        MenuItem addResume = new MenuItem("Resume");
        menuGame.getItems().addAll(addNew,addPause,addResume);
        
        Menu menuHelp = new Menu("Help");
        MenuItem addControls = new MenuItem("Controls");
        MenuItem addAbout = new MenuItem("About");
        menuHelp.getItems().addAll(addControls,addAbout);
        
        menuBar.getMenus().addAll(menuFile,menuGame,menuHelp);
        
        return menuBar;
    }
    
    private void initiateKeys(Scene theScene){
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>(){
                    public void handle(KeyEvent e){
                        String code = e.getCode().toString();
                        switch(code){
                        case "A": game.getPlayer(0).setVelX(-2);
                                  game.getPlayer(0).setDirection(LookDirection.LEFT);
                                  break;
                        case "D": game.getPlayer(0).setVelX(2);
                                  game.getPlayer(0).setDirection(LookDirection.RIGHT);
                                  break;
                        case "S": game.getPlayer(0).setVelY(2);
                                  game.getPlayer(0).setDirection(LookDirection.DOWN);
                                  break;
                        case "W": game.getPlayer(0).setVelY(-2);
                                  game.getPlayer(0).setDirection(LookDirection.UP);
                                  break;
                        case "SPACE": game.getPlayer(0).shoot();
                                  break;
                   
                        case "LEFT": game.getPlayer(1).setVelX(-2);
                                    game.getPlayer(1).setDirection(LookDirection.LEFT);
                                    break;
                        case "DOWN": game.getPlayer(1).setVelY(2);
                                    game.getPlayer(1).setDirection(LookDirection.DOWN);
                                  break;
                        case "RIGHT": game.getPlayer(1).setVelX(2);
                                    game.getPlayer(1).setDirection(LookDirection.RIGHT);
                                  break;
                        case "UP": game.getPlayer(1).setVelY(-2);
                                   game.getPlayer(1).setDirection(LookDirection.UP);
                                   break;
                        case "ENTER": game.getPlayer(1).shoot(); break;
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
    }
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
}
