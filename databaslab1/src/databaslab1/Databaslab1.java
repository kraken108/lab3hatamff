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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Jakob
 */
public class Databaslab1 extends Application{
    

    private DatabaseCommunicator dbComm;
    private TextField txt;
    private ChoiceBox<String> choiceBox;
    private ArrayList<MusicAlbum> listItems;
    private rateWindow rw;
    private TableView table = new TableView();
    
    
    
    
    ComfirmBox c = new ComfirmBox();
    MusicAlbum m1 = new MusicAlbum();

    
    @Override
    public void start(Stage primaryStage){
        
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
                MusicAlbum m = getSelectedAlbum();
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
                ArrayList<MusicAlbum> tempMusicAlbum = 
                        dbComm.searchRequest(txt.getText(),(String)
                                choiceBox.getSelectionModel().getSelectedItem());
                javafx.application.Platform.runLater(
                        new Runnable(){
                            public void run(){
                                if(tempMusicAlbum!=null){
                                    for(MusicAlbum ma : tempMusicAlbum){
                                        if(ma!=null){
                                            table.getItems().add(ma.toString());
                                            listItems.add(ma);
                                        }
                                    }
                                }
                                else AlertBox.display("Error!", "Search failed.");   
                            }
                        }
                );
            }
        };
        thread.start();
    }
    
    private MusicAlbum getSelectedAlbum(){
        int n = table.getSelectionModel().getSelectedIndex();
        if(n == -1){
            return null;
        }
        return listItems.get(n);
    }

}



