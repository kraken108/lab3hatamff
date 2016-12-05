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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Media;
import model.Person;



public class ComfirmBox{
    
    private Media albumToReturn;
    private Stage window;
    
    public ComfirmBox(){
        albumToReturn = new Media();
    }    
    
    public Media display(DatabaseCommunicator dbComm,ArrayList<Person> theArtists){
        albumToReturn = new Media();
        window = new Stage();     

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(3);
        grid.setHgap(5);
        
        Label artistLabel = new Label("Person name: ");
        GridPane.setConstraints(artistLabel, 0, 0);
        
        ComboBox comboBox = new ComboBox();
        for(Person p : theArtists){
            comboBox.getItems().add(p.getName()+", "+p.getCountry()+", "+p.getAge());
        }

        GridPane.setConstraints(comboBox,1,0);
        comboBox.setPromptText("Select artist");
        
        TextField personField = new TextField();
        personField.setPromptText("Person");         
        GridPane.setConstraints(personField, 1, 0);        
        
        Button addButton = new Button("Add another Person");
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
        
        Button doneButton = new Button("Submit");
        GridPane.setConstraints(doneButton, 1, 5);
        
        Button closeButton = new Button("Close");
        GridPane.setConstraints(closeButton, 2, 5);
        
        
        addButton.setOnMouseClicked(e ->{
            int n = comboBox.getSelectionModel().getSelectedIndex();
            System.out.println(n);
            if(n!=-1){
                System.out.println(n);
                System.out.println(theArtists.get(n).getPersonId());
                albumToReturn.addPerson(theArtists.get(n));
                theArtists.remove(n);
                comboBox.getItems().clear();
                for(Person p : theArtists){
                    comboBox.getItems().add(p.getName()+", "+p.getCountry()+", "+p.getAge());
                }
                
            }
        });
         
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setShowWeekNumbers(true); 
        datePicker.setEditable(false);
        grid.add(datePicker, 1, 4);
        
        
        
        doneButton.setOnMouseClicked(e->{
            
            
            if(titleField.getText().length()<2){
                AlertBox.display("Error!", "You must specify a title.");
            }
            else{
                albumToReturn.setTitle(titleField.getText());
                if(genreField.getText().length()<2){
                    AlertBox.display("Error!", "You must specify a genre.");
                }
                else{
                    albumToReturn.setGenre(genreField.getText());
                    int n = comboBox.getSelectionModel().getSelectedIndex();
                    if(n!=-1 || !albumToReturn.getThePersons().isEmpty()){
                        if(n!=-1){
                            albumToReturn.addPerson(theArtists.get(n));
                            theArtists.remove(n);
                        }
                        
                        comboBox.getItems().clear();
                        for(Person p : theArtists){
                            comboBox.getItems().add(p.getName()+", "+p.getCountry()+", "+p.getAge());
                        }
                        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        albumToReturn.setPublishDate(date);
                        sendAlbumRequest(dbComm);
                        
                    }
                    else
                        AlertBox.display("Error!", "You must pick an artist");
                }
            }
        });
        
        closeButton.setOnAction(e ->{
            window.close();
        });
                
        
        grid.getChildren().addAll(artistLabel, comboBox, titleLabel, titleField, doneButton, dateLabel, genreLabel, genreField, addButton, closeButton);

        Scene scene = new Scene(grid, 350, 200);
        window.setScene(scene);
        window.show();         
        return albumToReturn;
    }  
    
    private void sendAlbumRequest(DatabaseCommunicator dbComm){
        
        Thread thread = new Thread(){
            public void run(){
                int n = dbComm.newAlbumRequest(albumToReturn);
                javafx.application.Platform.runLater(
                        new Runnable(){
                            public void run(){
                                if(n==-1){
                                    AlertBox.display("Error!", "Failed to add new album.");
                                }
                                else{
                                    window.close();
                                }
                                    
                            }
                        }
                );
            }
        };
        thread.start();
        
    }
}