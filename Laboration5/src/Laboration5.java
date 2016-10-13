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
import View.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
    private NewGame newGameWindow;
    private FileHandler fileHandler;
    private Text player1score,player2score,pausedText;
    private Group root;
    
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
            
            
            drawBackground();
            drawPlayers();
            drawBot();
            drawBullets();
            drawScoreboard();
            
            game.paintScoreboard();

        }
    }
    
    private void drawScoreboard(){
        ArrayList<Player> thePlayers = game.getPlayers();
        Score s1 = thePlayers.get(0).getScore();
        player1score.setText(thePlayers.get(0).getName()+" / "
                               +s1.getKills()+" / "+s1.getDeaths());
        Score s2 = thePlayers.get(1).getScore();
        player2score.setText(thePlayers.get(1).getName()+" / "
                               +s2.getKills()+" / "+s2.getDeaths());

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
    
    private void gamePause(){
        timer.stop();
        root.getChildren().add(pausedText);
        
    }
    
    private void gameResume(){
        timer.start();
        root.getChildren().remove(pausedText);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Map example");
        
        root = new Group();
        Scene theScene = new Scene ( root );
        fileChooser = new FileChooser();
        primaryStage.setScene(theScene);
        MenuBar menuBar = initiateMenuBar(primaryStage);
        canvas = new Canvas(1024,768);
        root.getChildren().addAll(canvas,menuBar);
        
        fileHandler = new FileHandler();
        newGameWindow = new NewGame();
        newGameWindow.initOwner(primaryStage);
        
        gc = canvas.getGraphicsContext2D();
        
        game = new Game("Player 1","Player 2");
        
        theMap = new Image("images/karta2.png");
        player1 = new Image("images/BigBlueGuy.png");
        player2 = new Image("images/BigRedGuy.png");
        bullet = new Image("images/BigBullet.png");
        initiateScoreboard();
        
        
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

    private MenuBar initiateMenuBar(Stage primaryStage){
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem addSave = new MenuItem("Save game");
        addSave.setOnAction(new EventHandler<ActionEvent>() {
           public void handle (ActionEvent t) {
               File file = fileChooser.showSaveDialog(primaryStage);
               if(file != null){
                   fileHandler.saveFile(file,game);
               }
               file = null;
           } 
        });
        MenuItem addLoad = new MenuItem("Load game");
        addLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file!= null){
                    if(fileHandler.loadFile(file)!=null){
                        game = fileHandler.loadFile(file);
                    }
                }
                file = null;
            }
        });
        
        menuFile.getItems().addAll(addSave,addLoad);
        
        Menu menuGame = new Menu("Game");
        MenuItem addNew = new MenuItem("New game");
        addNew.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent t) {
               gamePause();
               newGameWindow.showAndWait();
               if(newGameWindow.getStart()){
                   game = new Game(newGameWindow.getPlayer1(),
                                   newGameWindow.getPlayer2());
                   System.out.println(newGameWindow.getPlayer1());
                   System.out.println(newGameWindow.getPlayer2());
               }
               gameResume();
   
           } 
        });
        MenuItem addPause = new MenuItem("Pause");
        addPause.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent t) {
               gamePause();
           } 
        });
        MenuItem addResume = new MenuItem("Resume");
        addResume.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent t) {
               gameResume();
           } 
        });
        menuGame.getItems().addAll(addNew,addPause,addResume);
        
        Menu menuHelp = new Menu("Help");
        MenuItem addControls = new MenuItem("Controls");
        MenuItem addAbout = new MenuItem("About");
        menuHelp.getItems().addAll(addControls,addAbout);
        
        menuBar.getMenus().addAll(menuFile,menuGame,menuHelp);
        
        return menuBar;
    }
    
    private void initiateScoreboard(){
        pausedText = new Text(440,375,"PAUSED");
        pausedText.setFill(Color.DARKTURQUOISE);
        pausedText.setFont(Font.font(30));
        
        ArrayList<Player> thePlayers = game.getPlayers();
        player1score = new Text(48,667,thePlayers.get(0).getName()+" / "
                            +thePlayers.get(0).getScore().getKills()
                            +" / "+thePlayers.get(0).getScore().getDeaths());
        player1score.setFill(Color.WHITE);
        player1score.setFont(Font.font(25));
        root.getChildren().add(player1score);
        player2score = new Text(48,697,thePlayers.get(1).getName()+" / "
                            +thePlayers.get(1).getScore().getKills()
                            +" / "+thePlayers.get(1).getScore().getDeaths());
        player2score.setFill(Color.WHITE);
        player2score.setFont(Font.font(25));
        root.getChildren().add(player2score);
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
