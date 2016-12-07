/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;


import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;
import database.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;


/**
 *
* @author Jakob Danielsson & Michael Hjälmö
 */
public class Databaslab1 extends Application{
    

    private DatabaseCommunicator dbComm;
    private TextField txt;
    private TableView tableView;
    private ChoiceBox<String> choiceBox;
    private ArrayList<Media> listItems;
    private rateWindow rw;
    private ObservableList data;

    
    @Override
    public void start(Stage primaryStage){
        
        tableView = new TableView();
        data = FXCollections.observableList(new ArrayList());
        tableView.setItems(data);
        
        TableColumn titleColumn = new TableColumn("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("title"));
        titleColumn.setMinWidth(150);
        TableColumn artistsColumn = new TableColumn("Artists");
        artistsColumn.setCellValueFactory(new PropertyValueFactory("thePersonsString"));
        artistsColumn.setMinWidth(200);
        TableColumn genreColumn = new TableColumn("Genre");
        genreColumn.setMinWidth(50);
        genreColumn.setCellValueFactory(new PropertyValueFactory("genre"));
        TableColumn ratingColumn = new TableColumn("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        ratingColumn.setMinWidth(50);
        ratingColumn.setMaxWidth(50);
        TableColumn releaseDateColumn = new TableColumn("Release Date");
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory("publishDate"));
        releaseDateColumn.setMinWidth(100);
        releaseDateColumn.setMaxWidth(100);

        tableView.getColumns().addAll(titleColumn,artistsColumn,genreColumn,ratingColumn,releaseDateColumn);
        listItems = new ArrayList<>();
        rw = new rateWindow();
        Boolean exit = false;
        Button btn = new Button();
        btn.setText("Search");
        Button rate = new Button();
        rate.setText("Rate");
        
        dbComm = null;
        try{
            dbComm = new DatabaseCommunicator();
        }catch(SQLException e){
            AlertBox.display("Error!", "Failed to connect to database: " + e);
            exit = true;
        }catch(Exception ex){
            AlertBox.display("Error!", "Failed to connect to database: " + ex);
            exit = true;
        }
        
        txt = new TextField();
        txt.setPromptText("Enter search word");
        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Artist", "Title", "Genre", "Rating");
        choiceBox.setTooltip(new Tooltip("Search by"));
        choiceBox.getSelectionModel().selectFirst();
        
        Button addNewArtist = new Button();
        addNewArtist.setText("Add new Artist");
        
        addNewArtist.setOnAction(e -> addNewArtist());
        
        rate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Media m = getSelectedAlbum();
                if(m!=null){
                    rw.rateAlbum(dbComm,m);
                }
            }
        });
        
        btn.setOnAction(new EventHandler<ActionEvent>() {  
            @Override
            public void handle(ActionEvent event) {
                sendSearch();
            }
        });
        
        Button addCD = new Button();
        addCD.setText("Add CD");
        
        addCD.setOnAction(e -> {
            ArrayList<Person> theArtists = dbComm.allArtistsRequest();
            addCD(theArtists);   
        });
        
        BorderPane borderPane = new BorderPane();
  
        
        HBox statusbar = new HBox();
        borderPane.setTop(statusbar);
        statusbar.setSpacing(10);
        statusbar.setPadding(new Insets(10, 0, 10, 10));
        HBox statusbar2 = new HBox();  
        statusbar2.setSpacing(10);
        borderPane.setBottom(statusbar2);
        statusbar2.setPadding(new Insets(10, 0, 10, 10));
        
        statusbar.setStyle("-fx-background-color: #00143a;");
        //NYTT
        
 

        borderPane.setCenter(tableView);

        borderPane.setCenter(tableView);

        statusbar2.getChildren().addAll(addCD,addNewArtist,rate);
        statusbar.getChildren().addAll(btn,txt,choiceBox);
        Scene scene = new Scene(borderPane, 768, 512);
        primaryStage.setOnCloseRequest(event->{
            dbComm.closeConnection();
        });
        primaryStage.setTitle("Album collection");
        primaryStage.setScene(scene);
        primaryStage.show();
        if(exit){
            primaryStage.close();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void sendSearch(){
        //table.getItems().clear();
        listItems.clear();
        
        Thread thread = new Thread(){
            public void run(){
                ArrayList<Media> tempMusicAlbum = 
                        dbComm.searchRequest(txt.getText(),(String)
                                choiceBox.getSelectionModel().getSelectedItem());
                javafx.application.Platform.runLater(
                        new Runnable(){
                            public void run(){
                                if(tempMusicAlbum!=null){
                                    if(!tempMusicAlbum.isEmpty())
                                        for(Media ma : tempMusicAlbum){
                                           if(ma!=null){
                                                System.out.println(ma.toString());
                                                changeData(tempMusicAlbum);
                                                listItems.add(ma);
                                            } 
                                        }
                                            
                                    else{
                                        changeData(tempMusicAlbum);
                                    }
                                }
                                        
                                else{
                                    AlertBox.display("Error!", "Search failed.");
                                    changeData(new ArrayList<Media>());
                                }   

                            }
                        });
        
            }
        };thread.start();
    }
    

    private Media getSelectedAlbum(){
        int n = tableView.getSelectionModel().getSelectedIndex();
        if(n == -1){
            return null;
        }
        return listItems.get(n);
    }

    private void changeData(ArrayList<Media> theMedia){
        data = FXCollections.observableList(theMedia);
        tableView.setItems(data);
    }
    
    public void addNewArtist(){
        
        Stage stage = new Stage();
        stage.setTitle("Add new artist");
        Button closeButton = new Button("Close");
        Button submitButton = new Button("Submit");
        
        ComboBox comboBox = new ComboBox();
        for(int i = 10; i<100; i++){
            comboBox.getItems().add(i);
        }
        comboBox.setPromptText("Pick age");
        
        GridPane grid = new GridPane();
        
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(3);
        grid.setHgap(5);
        
        Label infoLabel = new Label("Enter values: ");        
        Label nameLabel = new Label("Name: ");
        Label ageLabel = new Label("Age: ");
        Label nationalityLabel = new Label("Nationality: ");
        
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField nationalityField = new TextField();
        nameField.setPromptText("Name");
        ageField.setPromptText("Age");
        nationalityField.setPromptText("Nationality");
        
        GridPane.setConstraints(comboBox,4,5);    
        GridPane.setConstraints(infoLabel, 4, 0);
        GridPane.setConstraints(nameLabel, 2, 3);
        GridPane.setConstraints(ageLabel, 2, 5);
        GridPane.setConstraints(nationalityLabel, 2, 7);
        GridPane.setConstraints(nameField, 4, 3);
        GridPane.setConstraints(nationalityField, 4, 7);
        GridPane.setConstraints(closeButton, 6, 8);
        GridPane.setConstraints(submitButton, 4, 8);
        
        
                
        closeButton.setOnAction(e -> stage.close());
        submitButton.setOnAction(e -> {
        if(!(nameField.getText().length() >2 || nationalityField.getText().length() > 2 || comboBox.getSelectionModel().getSelectedIndex() == -1)){
            AlertBox.display("Error!", "You must enter correct data!");
        }
        else{
            Person p = new Person(nameField.getText(), comboBox.getSelectionModel().getSelectedIndex()+10, nationalityField.getText());
            System.out.println(p.getAge());
            sendNewArtist(p);
            stage.close();
        }
        });
        
        grid.getChildren().addAll(nameLabel, ageLabel, nationalityLabel, nameField, comboBox, nationalityField, closeButton, submitButton, infoLabel);
        Scene scene = new Scene(grid,350,200);
        stage.setScene(scene);
        stage.show();   
    }
    
    private void sendNewArtist(Person artist){
        Thread thread = new Thread(){
            public void run(){
                int n = dbComm.addArtistRequest(artist);
                javafx.application.Platform.runLater(
                        new Runnable(){
                            public void run(){
                                if(n==-1){
                                    AlertBox.display("Error!", "Failed to add new album.");
                                }
                            }
                        }
                );
            }
        };
        thread.start();
    }
    
    public void addCD(ArrayList<Person> theArtists){
        
        Media albumToReturn = new Media();
        Stage window = new Stage();     

        window.initModality(Modality.WINDOW_MODAL);
        window.setTitle("Add CD");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(3);
        grid.setHgap(5);
        
        Label artistLabel = new Label("Artist name: ");
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
        
        Button addButton = new Button("Add");
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
                        sendAlbumRequest(albumToReturn);
                        window.close();
                        
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
        
    }
    
    
    private void sendAlbumRequest(Media albumToReturn){
        
        Thread thread = new Thread(){
            public void run(){
                int n = dbComm.newAlbumRequest(albumToReturn);
                javafx.application.Platform.runLater(
                        new Runnable(){
                            public void run(){
                                if(n==-1){
                                    AlertBox.display("Error!", "Failed to add new album.");
                                }  
                            }
                        }
                );
            }
        };
        thread.start();
        
    }
}