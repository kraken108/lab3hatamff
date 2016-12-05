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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


/**
 *
 * @author Jakob
 */
public class Databaslab1 extends Application{
    

    private DatabaseCommunicator dbComm;
    private TextField txt;
    private TableView tableView;
    private ChoiceBox<String> choiceBox;
    private ArrayList<Media> listItems;
    private rateWindow rw;
    private ObservableList data;

    ComfirmBox c = new ComfirmBox();
    Media m1 = new Media();

    
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
        
        txt = new TextField("Enter search word");
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
        
        Button add = new Button();
        add.setText("Add CD");
        add.setOnAction(e -> {
            ArrayList<Person> theArtists = dbComm.allArtistsRequest();
            m1=c.display(dbComm,theArtists);   
        });

        System.out.println(m1.getGenre());
        System.out.println(m1.getTitle());
        System.out.println(m1.getPublishDate());
        System.out.println(m1.getRating());
        
        BorderPane borderPane = new BorderPane();
  
        
        HBox statusbar = new HBox();
        borderPane.setTop(statusbar);
        statusbar.setSpacing(10);
        statusbar.setPadding(new Insets(10, 0, 10, 10));
        HBox statusbar2 = new HBox();  
        statusbar2.setSpacing(10);
        borderPane.setBottom(statusbar2);
        statusbar2.setPadding(new Insets(10, 0, 10, 10));
        
        statusbar.setStyle("-fx-background-color: #029aff;");
        statusbar2.setStyle("-fx-background-color: #029aff;");
        //NYTT
        
 

        borderPane.setCenter(tableView);

        borderPane.setCenter(tableView);

        statusbar2.getChildren().addAll(add,addNewArtist,rate);
        statusbar.getChildren().addAll(btn,txt,choiceBox);
        Scene scene = new Scene(borderPane, 768, 512);
        primaryStage.setOnCloseRequest(event->{
            dbComm.closeConnection();
        });
        primaryStage.setTitle("Main Menu");
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
                                        for(Media ma : tempMusicAlbum)
                                            if(ma!=null){
                                                System.out.println(ma.toString());
                                                changeData(tempMusicAlbum);
                                                listItems.add(ma);
                                            }
                                    else
                                        changeData(tempMusicAlbum);
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
        
        Button closeButton = new Button("Close");
        Button submitButton = new Button("Submit");
        
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
            
        GridPane.setConstraints(infoLabel, 4, 0);
        GridPane.setConstraints(nameLabel, 2, 3);
        GridPane.setConstraints(ageLabel, 2, 5);
        GridPane.setConstraints(nationalityLabel, 2, 7);
        GridPane.setConstraints(nameField, 4, 3);
        GridPane.setConstraints(ageField, 4, 5);
        GridPane.setConstraints(nationalityField, 4, 7);
        GridPane.setConstraints(closeButton, 6, 8);
        GridPane.setConstraints(submitButton, 4, 8);
        
        
                
        closeButton.setOnAction(e -> stage.close());
        submitButton.setOnAction(e -> {
        if(!(nameField.getText().length() >2 || ageField.getText().length() > 0 || nationalityField.getText().length() > 2)){
            AlertBox.display("Error!", "You must enter correct data!");
        }
        else{
            int tempAge = Integer.parseInt(ageField.getText());
            Person p = new Person(nameField.getText(), tempAge, nationalityField.getText());
            sendNewArtist(p);
        }
        });
        
        grid.getChildren().addAll(nameLabel, ageLabel, nationalityLabel, nameField, ageField, nationalityField, closeButton, submitButton, infoLabel);
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
}



