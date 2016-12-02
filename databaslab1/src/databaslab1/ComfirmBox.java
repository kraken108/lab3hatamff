/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;

import database.DatabaseCommunicator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private final String pattern = "yyyy-MM-dd";
    
    public ComfirmBox(){
        albumToReturn = new MusicAlbum();
    }    
    
    public MusicAlbum display(DatabaseCommunicator dbComm){
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
        
        Button addButton = new Button("Add another artist");
        GridPane.setConstraints(addButton, 1, 1);
        
        Label titleLabel = new Label("Title: ");
        GridPane.setConstraints(titleLabel, 0, 2);
        
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        GridPane.setConstraints(titleField, 1, 2);

        Button dateButton = new Button("Pick a Date"); //1,3
        GridPane.setConstraints(dateButton, 1, 4);
        Label dateLabel = new Label("Publish date: ");
        GridPane.setConstraints(dateLabel, 0, 4);
                
        TextField genreField = new TextField();
        genreField.setPromptText("Genre");
        GridPane.setConstraints(genreField, 1, 3);
        Label genreLabel = new Label("Genre: ");
        GridPane.setConstraints(genreLabel, 0, 3);
        
        Button doneButton = new Button("Done, next album");
        GridPane.setConstraints(doneButton, 1, 5);
        
        Button closeButton = new Button("Close");
        GridPane.setConstraints(closeButton, 2, 5);
        
        
        addButton.setOnMouseClicked(e ->{
            Artist tempArtist = new Artist(artistField.getText());
            if(tempArtist.getName().isEmpty() || tempArtist.getName().length()<2){
                AlertBox.display("Error!", "You must specify a name.");
            }                

            if(!(tempArtist.getName() instanceof String)){
                AlertBox.display("Error!", "Name must be a String.");
            }
            else{
                albumToReturn.addArtist(tempArtist);  
                artistField.clear();
            }
        });
         
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setShowWeekNumbers(true); 
        datePicker.setEditable(false);
        grid.add(datePicker, 1, 4);
        
        
        
        doneButton.setOnMouseClicked(e->{
            
            Artist tempArtist = new Artist(artistField.getText());
            if(titleField.getText().length()<2){
                AlertBox.display("Error!", "You must specify a title.");
            }
            else{
                albumToReturn.setTitle(titleField.getText());
            }
            
            if(genreField.getText().length()<2){
                AlertBox.display("Error!", "You must specify a genre.");
            }
            else{
                albumToReturn.setGenre(genreField.getText());
            }
            if(tempArtist.getName().isEmpty() || tempArtist.getName().length()<2){
                AlertBox.display("Error!", "You must specify a name.");
            }                

            if(!(tempArtist.getName() instanceof String)){
                AlertBox.display("Error!", "Name must be a String.");
            }
            else{
                albumToReturn.addArtist(tempArtist);  
                artistField.clear();
            }           
                       
            String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            albumToReturn.setPublishDate(date);            
            System.out.println("date: " + date);
            titleField.clear();            
            genreField.clear();
            dbComm.newAlbumRequest(albumToReturn);
        });
        
        closeButton.setOnAction(e ->{
            window.close();
        });
                
        
        grid.getChildren().addAll(artistLabel, artistField, titleLabel, titleField, doneButton, dateLabel, genreLabel, genreField, addButton, closeButton);

        Scene scene = new Scene(grid, 500, 300);
        window.setScene(scene);
        window.show();         
        return albumToReturn;
    }  
}