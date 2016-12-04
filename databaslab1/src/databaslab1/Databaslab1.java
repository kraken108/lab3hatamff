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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 *
 * @author Jakob
 */
public class Databaslab1 extends Application{
    

    private DatabaseCommunicator dbComm;
    private TextField txt;
    private ListView listView;
    private TableView tableView;
    private ChoiceBox<String> choiceBox;
    private ArrayList<Media> listItems;
    private rateWindow rw;
    private ObservableList data;
    private TableView table = new TableView();

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
        artistsColumn.setCellValueFactory(new PropertyValueFactory("theArtistsString"));
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
        
        
        TableColumn firstCol = new TableColumn("Title");
        TableColumn secondCol = new TableColumn("Artist");
        TableColumn thirdCol = new TableColumn("Genre");
        TableColumn fourthCol = new TableColumn("Rating");
        TableColumn fifthCol = new TableColumn("Date");
        
        table.getColumns().addAll(firstCol, secondCol, thirdCol, fourthCol, fifthCol);
        table.setEditable(true);
        
        
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
            m1=c.display(dbComm);   
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
        
        
        //NYTT
        
 

        borderPane.setCenter(tableView);

        borderPane.setCenter(table);

        statusbar2.getChildren().addAll(add,rate);
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
                                                changeData(tempMusicAlbum);
                                                listView.getItems().add(ma.toString());
                                                listItems.add(ma);
                                            }
                                    else
                                        changeData(tempMusicAlbum);
                                        
                                else{
                                    AlertBox.display("Error!", "Search failed.");
                                    tableView.setItems(null);
                                    }   
                                }

                            }
                        });
        
            }
        };thread.start();
    }
    

    private Media getSelectedAlbum(){
        int n = table.getSelectionModel().getSelectedIndex();
        if(n == -1){
            return null;
        }
        return listItems.get(n);
    }

    private void changeData(ArrayList<Media> theMedia){
        data = FXCollections.observableList(theMedia);
        tableView.setItems(data);
    }
}



