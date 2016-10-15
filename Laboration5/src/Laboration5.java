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
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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
    private GraphicsContext gc,menuGc;
    private Canvas canvas, menuCanvas;
    private Game game;
    private Image theMap,player1,player2,bullet,menuBackground;
    private Circle altBullet;
    private Rectangle altPlayer1,altPlayer2;
    private FileChooser fileChooser;
    private NewGame newGameWindow;
    private FileHandler fileHandler;
    private Text player1score,player2score,pausedText,continueGame;
    private Group root,menu;
    private Scene theScene,menuScene;
    private Stage window;
    private boolean gameRunning,exit;
    private double deltaTime,frameStart,frameEnd;
    
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

            double newTime = System.nanoTime();

            
            drawBackground();
            drawPlayers();
            //drawBot();
            drawBullets();
            drawScoreboard();
            drawCar();
            
            if(newTime-game.checkIfDead()>FOURBILLION){
                game.randSpawn();
                game.addCar(new Image("Car.png"));
            }
                
            
            
            frameEnd = System.nanoTime();
        }
    }
    
    private void drawCar(){
        ArrayList<Car> theCar = game.getCar();
        for(Car c: theCar){
            c.tick();
            gc.drawImage(new Image("Car.png"), c.getPosX(), c.getPosY);
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
        Player p = thePlayers.get(0);
        if(p.getPlayerState()==PlayerState.ALIVE){
            p.tick();
            WritableImage croppedImage = new WritableImage(player1.getPixelReader(),p.getFrameX(),0,64,64);
            gc.drawImage(croppedImage, p.getX(), p.getY()); 
        }
        Player k = thePlayers.get(1);
        if(k.getPlayerState()==PlayerState.ALIVE){
            k.tick();
            WritableImage croppedImage2 = new WritableImage(player2.getPixelReader(),k.getFrameX(),0,64,64);
            gc.drawImage(croppedImage2, k.getX(), k.getY());
        }
    }
    
    private void drawBullets(){
        game.detectHit();
        ArrayList<Player> thePlayers = game.getPlayers();
        for(Player p: thePlayers){
            p.bulletsOutOfMap();
            ArrayList<Bullet> theBullets = p.getBullets();
            for(Bullet b: theBullets){
                b.tick();
                if(bullet!=null){
                    gc.drawImage(bullet, b.getPosX(), b.getPosY());
                }
                else
                    gc.strokeOval(b.getPosX(),b.getPosY(),altBullet.getRadius()*2,altBullet.getRadius()*2);
                
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
        window = primaryStage;
        root = new Group();
        menu = new Group();
        theScene = new Scene ( root );
        menuScene = new Scene ( menu );
        gameRunning = false;
        fileChooser = new FileChooser();
        MenuBar menuBar = initiateMenuBar(primaryStage);
        MenuBar gameBar = initiateMenuBar(primaryStage);
        canvas = new Canvas(1024,768);
        menuCanvas = new Canvas(1024,768);
        root.getChildren().addAll(canvas,gameBar);
        menu.getChildren().addAll(menuCanvas,menuBar);
                
        fileHandler = new FileHandler();
        newGameWindow = new NewGame();
        newGameWindow.initOwner(primaryStage);
        
        menuGc = menuCanvas.getGraphicsContext2D();
        gc = canvas.getGraphicsContext2D();
        
        game = new Game();
        
        loadImages();
        
        initiateScoreboard();
        
        timer = new GameTimer();
        initiateKeys(theScene);
        initMainMenu();
        startMenu();
        primaryStage.show();
        if(exit){
            window.close();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void setMenuTextAtt(Text text){
        text.setFill(Color.WHITE);
        text.setFont(Font.font(30));
        text.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                text.setFont(Font.font(40));
            }
        });
        text.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                text.setFont(Font.font(30));
            }
        });
    }
    
    private void loadImages() throws IllegalArgumentException{

        try{
            theMap = new Image("images/ImagePack/karta3.png");
        }catch(IllegalArgumentException i){
            noImageAlert("Failed to load image: Game Background.\nTerminating program..");
            exit = true;
        }
        
        try{
            player1 = new Image("images/ImagePack/BigBlueGuy.png");
        }catch(IllegalArgumentException i){
            try{
                player1 = new Image("images/standardBlueguy.png");
            }catch(IllegalArgumentException i2){
                noImageAlert("Failed to load image: Player 1.\nTerminating program..");
                exit = true;
            }
        }
        
        try{
            player2 = new Image("images/ImagePack/BigRedGuy.png");
        }catch(IllegalArgumentException i){
            try{
                player2 = new Image("images/standardRedguy.png");
            }catch(IllegalArgumentException i2){
                noImageAlert("Failed to load image: Player 2.\nTerminating program..");
                exit = true;
            }
        }
        
        try{
            bullet = new Image("images/BigBullet.png");
        }catch(IllegalArgumentException i){
            noImageAlert("Failed to load image: Bullet.\nTerminating program..");
            exit = true;
        }
        
        try{
            menuBackground = new Image("images/ImagePack/MenuBackground.png");
        }catch(IllegalArgumentException i){
            try{
                menuBackground = new Image("images/standardMenuBackground.png");
            }catch(IllegalArgumentException i2){
                noImageAlert("Failed to load image: Menu Background.\nTerminating program..");
                exit = true;
            }
        }
        
    }
    private void noImageAlert(String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Image not found");
        alert.setHeaderText("Sorry");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void initMainMenu(){
        menuGc.drawImage(menuBackground, 0, 0);
        
        continueGame = new Text(420,270,"CONTINUE");
        setMenuTextAtt(continueGame);
        continueGame.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent e){
               gameResume();
               startGame();
           }
        });
        
        Text newGame = new Text(420,320,"NEW GAME");
        setMenuTextAtt(newGame);
        newGame.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent e){
               initNewGame();
           }
        });
        
        Text loadGame = new Text(420,370,"LOAD GAME");
        setMenuTextAtt(loadGame);
        loadGame.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent e){
               loadGame();
           }
        });
        
        Text exitGame = new Text(420,420,"EXIT");
        setMenuTextAtt(exitGame);
        exitGame.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent e){
               window.close();
           }
        });
        menu.getChildren().addAll(newGame,loadGame,exitGame);
    }
    
    private void startMenu(){
        if(gameRunning){
            menu.getChildren().add(continueGame);
        }
        window.setScene(menuScene);
    }
    
    private void startGame(){
        menu.getChildren().remove(continueGame);
        window.setScene(theScene);
        gameRunning = true;
        timer.start();
    }
    
    public void showAlert(String message){
        if(!alert.isShowing()){
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText(message);
        alert.show();           
        }        
    }
    
    private void initNewGame(){
        newGameWindow.showAndWait();
        if(newGameWindow.getStart()){
            game = new Game(newGameWindow.getPlayer1(),
            newGameWindow.getPlayer2(),player1,player2);
            System.out.println(newGameWindow.getPlayer1());
            System.out.println(newGameWindow.getPlayer2());
        }
        gameResume();
        startGame();
    }
    
    private void loadGame(){
        File file = fileChooser.showOpenDialog(window);
        if(file!= null){
            if(fileHandler.loadFile(file)!=null){
                game = fileHandler.loadFile(file);
                startGame();
            }
        }
        file = null;
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
                loadGame();
            }
        });
        
        menuFile.getItems().addAll(addSave,addLoad);
        
        Menu menuGame = new Menu("Game");
        MenuItem addNew = new MenuItem("New game");
        addNew.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent t) {
               gamePause();
               initNewGame();
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
        player1score = new Text(317,708,thePlayers.get(0).getName()+" / "
                            +thePlayers.get(0).getScore().getKills()
                            +" / "+thePlayers.get(0).getScore().getDeaths());
        player1score.setFill(Color.WHITE);
        player1score.setFont(Font.font(25));
        root.getChildren().add(player1score);
        player2score = new Text(317,750,thePlayers.get(1).getName()+" / "
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
                        case "A": game.getPlayer(0).setVelX(-4);
                                  game.getPlayer(0).setDirection(LookDirection.LEFT);
                                  break;
                        case "D": game.getPlayer(0).setVelX(4);
                                  game.getPlayer(0).setDirection(LookDirection.RIGHT);
                                  break;
                        case "S": game.getPlayer(0).setVelY(4);
                                  game.getPlayer(0).setDirection(LookDirection.DOWN);
                                  break;
                        case "W": game.getPlayer(0).setVelY(-4);
                                  game.getPlayer(0).setDirection(LookDirection.UP);
                                  break;
                        case "SPACE": game.getPlayer(0).shoot(bullet);
                                      game.getPlayer(0).setGunLock(true);
                                  break;
                   
                        case "LEFT": game.getPlayer(1).setVelX(-4);
                                    game.getPlayer(1).setDirection(LookDirection.LEFT);
                                    break;
                        case "DOWN": game.getPlayer(1).setVelY(4);
                                    game.getPlayer(1).setDirection(LookDirection.DOWN);
                                  break;
                        case "RIGHT": game.getPlayer(1).setVelX(4);
                                    game.getPlayer(1).setDirection(LookDirection.RIGHT);
                                  break;
                        case "UP": game.getPlayer(1).setVelY(-4);
                                   game.getPlayer(1).setDirection(LookDirection.UP);
                                   break;
                        case "ENTER": game.getPlayer(1).shoot(bullet); game.getPlayer(1).setGunLock(true);break;
                        
                        case "ESCAPE": timer.stop();startMenu(); break;
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
                        case "SPACE": game.getPlayer(0).setGunLock(false);break;
                        case "LEFT": game.getPlayer(1).setVelX(0);break;
                        case "DOWN": game.getPlayer(1).setVelY(0);break;
                        case "RIGHT": game.getPlayer(1).setVelX(0);break;
                        case "UP": game.getPlayer(1).setVelY(0);break;
                        case "ENTER": game.getPlayer(1).setGunLock(false);break;
                        }

    
                    }
                }
        
        
        );
    }
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
}
