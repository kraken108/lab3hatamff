/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Jakob
 */
public class NewGame extends Stage{
    private final TextField player1field;
    private final TextField player2field;
    private String player1name;
    private String player2name;
    private boolean startgame;
    public NewGame(){
        super();
        this.initModality(Modality.WINDOW_MODAL);
        
        player1field = new TextField();
        player2field = new TextField();
        
        GridPane form = new GridPane();
        
        form.setVgap(5);
        form.setHgap(5);
        
        form.setPadding(new Insets(10,10,10,10));
        form.add(new Label("Player 1 name"),0,0);
        form.add(player1field, 0, 1);
        form.add(new Label("Player 2 name"),1,0);
        form.add(player2field,1,1);
        
        Button startButton = new Button("Start game");
        Button cancelButton = new Button("Cancel");
        EventHandler buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                if(event.getSource().equals(startButton)){
                    if(player1field.getText() == null)
                        player1name = "Player 1";
                    else
                        player1name = player1field.getText();
                    if(player2field.getText() == null)
                        player2name = "Player 2";
                    else
                        player2name = player2field.getText();
                    startgame = true;
                }
                
                else if(event.getSource().equals(cancelButton)){
                    startgame = false;
                }
                NewGame.this.hide();
            }
                
        };
        startButton.setOnAction(buttonHandler);
        cancelButton.setOnAction(buttonHandler);
        HBox buttonPane = new HBox(5);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10,10,10,10));
        buttonPane.getChildren().addAll(startButton,cancelButton);
        BorderPane root = new BorderPane();
        root.setCenter(form);
        root.setBottom(buttonPane);
        Scene scene = new Scene(root);
        this.setResizable(false);
        this.setTitle("New game");
        this.setScene(scene);
    }
    public boolean getStart(){
        return startgame;
    }
    public String getPlayer1(){
        return player1name;
    }
    public String getPlayer2(){
        return player2name;
    }
}

class PlayerNames {
    private String player1;
    private String player2;
    
    PlayerNames(String player1, String player2){
        this.player1 = player1;
        this.player2 = player2;
    }
    
    String getPlayer1(){
        return player1;
    }
    
    String getPlayer2(){
        return player2;
    }
}
