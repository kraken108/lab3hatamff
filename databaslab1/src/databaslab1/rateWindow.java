/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;

import database.DatabaseCommunicator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Media;

/**
 *
* @author Jakob Danielsson & Michael Hjälmö
 */
public class rateWindow extends Stage{      
    private GridPane theGrid; 
    private Scene theScene;

    public rateWindow(){
        super();
        this.initModality(Modality.WINDOW_MODAL);
        theGrid = new GridPane();
        theScene = new Scene(theGrid, 300, 200);
    }
    
    public void rateAlbum(DatabaseCommunicator dbComm,Media m){
        theGrid.setPadding(new Insets(5,5,5,5));
        theGrid.setVgap(3);
        theGrid.setHgap(5);
        
        Label artistLabel = new Label("Rate ");
        GridPane.setConstraints(artistLabel, 0, 0);
        
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
        "1","2","3","4","5"));
        GridPane.setConstraints(cb,0,1);
        cb.getSelectionModel().selectFirst();
        
        Label commentLabel = new Label("Comment ");
        GridPane.setConstraints(commentLabel, 0, 2);
        
        TextArea textArea = new TextArea();
        GridPane.setConstraints(textArea, 0, 3);
        textArea.setPromptText("Max 120 characters");
        Button submitButton = new Button();
        submitButton.setText("Submit");
        GridPane.setConstraints(submitButton, 0, 4);
        submitButton.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                if(textArea.getText().length() <= 120){
                    sendRateRequest(cb.getSelectionModel().getSelectedIndex()+1,textArea.getText(),m,dbComm);
                    rateWindow.this.hide();
                }
                else{
                    AlertBox.display("Error!", "Too many characters.");
                }
                
            }
        });
        
        theGrid.getChildren().addAll(artistLabel,cb,commentLabel,textArea,submitButton);
        this.setTitle("Rate album");
        this.setScene(theScene);
        this.show();
    }
    
    private void sendRateRequest(int index, String text, Media m,DatabaseCommunicator dbComm){
        Thread thread = new Thread(){
            public void run(){
                int n = dbComm.rateRequest(index, text, m);
                javafx.application.Platform.runLater(
                        new Runnable(){
                            public void run(){
                                if(n==-1){
                                    AlertBox.display("Error!", "Failed to rate album.");
                                }
                            }
                        }
                );
            }
        };
        thread.start();
        
    }
}
