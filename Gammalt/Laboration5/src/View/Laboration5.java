package View;

import java.io.File;
import java.io.IOException;
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
import java.util.Random;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class Laboration5 extends Application {
    
    private AnimationTimer timer;
    
    private GraphicsContext gc,menuGc;
    private Canvas canvas, menuCanvas;
    private Game game;
    private Image theMap,player1,player2,bullet,menuBackground,car;
    private ArrayList<Image> carImages,rightCarImages;
    private Circle altBullet;
    private FileChooser fileChooser;
    private NewGame newGameWindow;
    private ControlsWindow controlsWindow;
    private RulesWindow rulesWindow;
    private AboutWindow aboutWindow;
    private FileHandler fileHandler;
    private Text player1score,player2score,pausedText,continueGame;
    private Group root,menu;
    private Scene theScene,menuScene;
    private Stage window;
    private boolean gameRunning,exit;
    private double lastTopSpawn,lastBotSpawn,topCooldown,botCooldown;
    
    private final long FRAME_NS = 10_000_000;
    public static final double FOURBILLION = 4_000_000_000.0;
    public static final double TWOBILLION = 2_000_000_000.0;
    public static final double ONEBILLION = 1_000_000_000.0;
                                              
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
            drawBullets();
            drawScoreboard();
            drawCar();
            carSpawn();
            game.bulletHitCar();
            if(newTime-game.checkIfDead()>FOURBILLION){
                game.randSpawn();
            }
                
            checkWinner();
        }
    }
    private void carSpawn(){
        double newTime = System.nanoTime();
        if(newTime-lastTopSpawn>ONEBILLION+topCooldown){
            game.addCar(getRandomCar(LookDirection.LEFT),LookDirection.LEFT);
            Random rand = new Random();
            topCooldown = FOURBILLION*rand.nextDouble();
            lastTopSpawn = System.nanoTime();
        }
        if(newTime-lastBotSpawn>ONEBILLION+botCooldown){
            game.addCar(getRandomCar(LookDirection.RIGHT),LookDirection.RIGHT);
            Random rand = new Random();
            botCooldown = FOURBILLION*rand.nextDouble();
            lastBotSpawn = System.nanoTime();
        }
        
    }
    private void drawCar(){
        ArrayList<Car> theCar = game.getCar();
        ArrayList<Player> thePlayers = game.getPlayers();
        for(Player p: thePlayers){
            for(Car c: theCar){
                game.removeCar();                
                /*if(p.getPosY()<=490 && p.getPosY()>=270){
                if(c.getVelocity()<0 &&p.getPosX()<c.getPosX())
                    c.roadRage(-10);
                else if(c.getVelocity()>0 && p.getPosX()>c.getPosX())
                    c.roadRage(10);
                }      */                
                c.tick();
                gc.drawImage(c.getImage(), c.getPosX(), c.getPosY());            
             }
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
    
    
    
    private void drawBackground(){
        gc.drawImage(theMap, 0, 0);
    }
    
    private void drawPlayers(){
        ArrayList<Player> thePlayers = game.getPlayers();
        
        game.detectCarHit();
        
        Player p = thePlayers.get(0);
        if(p.getPlayerState()==PlayerState.ALIVE){
            p.tick();
            WritableImage croppedImage = new WritableImage(player1.getPixelReader(),p.getFrameX(),0,64,64);
            gc.drawImage(croppedImage, p.getPosX(), p.getPosY()); 
        }
        Player k = thePlayers.get(1);
        if(k.getPlayerState()==PlayerState.ALIVE){
            k.tick();
            WritableImage croppedImage2 = new WritableImage(player2.getPixelReader(),k.getFrameX(),0,64,64);
            gc.drawImage(croppedImage2, k.getPosX(), k.getPosY());
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
    
    private Image getRandomCar(LookDirection direction){
        Random rand = new Random();
        int n;
        if(direction == LookDirection.LEFT){
            n = rand.nextInt(carImages.size());
            return carImages.get(n);
        }
        else{
            n = rand.nextInt(rightCarImages.size());
            return rightCarImages.get(n);
        }

       
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dodgecar");
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
        controlsWindow = new ControlsWindow();
        controlsWindow.initOwner(primaryStage);
        rulesWindow = new RulesWindow();
        rulesWindow.initOwner(primaryStage);
        aboutWindow = new AboutWindow();
        aboutWindow.initOwner(primaryStage);
        
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
    
    private Image tryLoad(String fileName,String secFileName){
        try{
            Image image = new Image(fileName);
            return image;
        }catch(IllegalArgumentException i){
            if(secFileName!=null){
                try{
                    Image image = new Image(secFileName);
                    return image;
                }catch(IllegalArgumentException i2){
                    errorAlert("Failed to load image: "+fileName+".\nTerminating program..");
                    exit = true;
                }
            }
            else{
                errorAlert("Failed to load image: "+fileName+".\nTerminating program..");
                exit = true;
            }
        }
        return null;
    }
    
    private void loadImages(){
        
        theMap = tryLoad("images/ImagePack/karta3.png",null);
        player1 = tryLoad("images/ImagePack/BigBlueGuy.png","images/standardBlueguy.png");
        player2 = tryLoad("images/ImagePack/BigRedGuy.png","images/standardRedguy.png");
        bullet = tryLoad("images/BigBullet.png",null);
        menuBackground = tryLoad("images/ImagePack/MenuBackground.png","images/standardMenuBackground.png");
        car = tryLoad("images/Car.png",null);
        
        carImages = new ArrayList<Image>();
        rightCarImages = new ArrayList<Image>();
        carImages.add(tryLoad("images/carPack/Beamer-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/B-Type-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Bulwark-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/FuroreGT-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/HotDogVan-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Maurice-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Meteor-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Panto-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Pickup-GTA2-gang.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Romero-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Stinger-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/TaxiXpress-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/TVVan-GTA2.png","images/Car.png"));
        carImages.add(tryLoad("images/carPack/Z-Type-GTA2.png","images/Car.png"));
        
        rightCarImages.add(tryLoad("images/carPack/rotated/Beamer-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/B-Type-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Bulwark-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/FuroreGT-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/HotDogVan-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Maurice-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Meteor-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Panto-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Pickup-GTA2-gang.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Romero-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Stinger-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/TaxiXpress-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/TVVan-GTA2.png","images/Car.png"));
        rightCarImages.add(tryLoad("images/carPack/rotated/Z-Type-GTA2.png","images/Car.png"));
    }
    
    private void checkWinner(){
        ArrayList<Player> thePlayers = game.getPlayers();
        for(Player p : thePlayers){
            if(p.getScore().getKills()>=5){
                timer.stop();
                infoAlert("We have a winner!!!\nCongratulations "+p.getName()+" you have won!");
                gameRunning = false;
                startMenu();
            }
        }
    }
    public void stopGame(){
        timer.stop();
    }
    private void errorAlert(String message){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Sorry");
        alerta.setContentText(message);
        alerta.showAndWait();
    }
    
    private void infoAlert(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
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
   
    
    private void initNewGame(){
        newGameWindow.showAndWait();
        if(newGameWindow.getStart()){
            game = new Game(newGameWindow.getPlayer1(),
            newGameWindow.getPlayer2(),player1,player2);
            gameResume();
            startGame();
        }
    }
    
    private void controlsWindow(){
        controlsWindow.showAndWait();
    }
    
    private void rulesWindow(){
        rulesWindow.showAndWait();
    }
    
    private void aboutWindow(){
        aboutWindow.showAndWait();
    }
    
    private void loadGame(){
        File file = fileChooser.showOpenDialog(window);
        if(file!= null){
            try{
                ArrayList<Player> thePlayers =fileHandler.loadFile(file);
                for(int i = 0;i<thePlayers.size();i++){
                    game.addPlayer(thePlayers.get(i),i);
                }
                startGame();
            }catch(IOException e){
                errorAlert("Could not load file..");
            }
            catch(ClassNotFoundException c){
                errorAlert("Could not load file..");
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
                   try{
                       fileHandler.saveFile(file,game.getPlayers());
                   }catch(Exception e){
                       errorAlert("Could not save file..");
                   }
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
        addControls.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent t){
               controlsWindow();
           } 
        });
        MenuItem addAbout = new MenuItem("About");
        addAbout.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent t){
               aboutWindow();
           } 
        });
        MenuItem addRules = new MenuItem("Rules");
        addRules.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                rulesWindow();
            }
        });
        menuHelp.getItems().addAll(addControls,addAbout,addRules);
        
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
    
}
