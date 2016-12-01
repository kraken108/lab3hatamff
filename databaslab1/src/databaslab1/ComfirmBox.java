/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Artist;
import model.MusicAlbum;



public class ComfirmBox{
    
    private MusicAlbum albumToReturn;

    
    public ComfirmBox(){
        albumToReturn = new MusicAlbum();
    }
    
    
    public void display(String title, String message){
        Stage window = new Stage();     
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(3);
        grid.setHgap(5);
        
        Label artistLabel = new Label("Artist name: ");
        GridPane.setConstraints(artistLabel, 0, 0);
        
        TextField artistField = new TextField();
        artistField.setPromptText("Artist");         
        GridPane.setConstraints(artistField, 1, 0);        
        
        Button addButton = new Button("Add Artist");
        GridPane.setConstraints(addButton, 1, 1);
        
        Label titleLabel = new Label("Title: ");
        GridPane.setConstraints(titleLabel, 0, 2);
        
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        GridPane.setConstraints(titleField, 1, 2);

        TextField dateField = new TextField();
        dateField.setPromptText("YY-MM-DD");
        GridPane.setConstraints(dateField, 1, 3);
        Label dateLabel = new Label("Publish date: ");
        GridPane.setConstraints(dateLabel, 0, 3);
                
        TextField genreField = new TextField();
        genreField.setPromptText("Genre");
        GridPane.setConstraints(genreField, 1, 4);
        Label genreLabel = new Label("Genre: ");
        GridPane.setConstraints(genreLabel, 0, 4);
        
        Button doneButton = new Button("Done, next album");
        GridPane.setConstraints(doneButton, 1, 5);
        
        Button closeButton = new Button("Close");
        GridPane.setConstraints(closeButton, 2, 5);
        closeButton.setOnAction(e -> window.close());
        
        addButton.setOnAction(e ->{
            Artist tempArtist=null;
            tempArtist.setNickName(artistField.getText());
            albumToReturn.addArtist(tempArtist);            
            artistField.clear();
        });
        
        
        
        doneButton.setOnMouseClicked(e->{
        titleField.clear();
        dateField.clear();
        genreField.clear();        
        });
        
                
        
        grid.getChildren().addAll(artistLabel, artistField, titleLabel, titleField, doneButton, dateField, dateLabel, genreLabel, genreField, addButton, closeButton);
        
  
        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
        
         
    }

  
    
    
}
