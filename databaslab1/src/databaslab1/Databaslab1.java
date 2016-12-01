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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import database.*;

/**
 *
 * @author Jakob
 */
public class Databaslab1 extends Application{
    
    Stage window;
    private DatabaseCommunicator dbComm;
    private TextField txt;
    private ListView listView;
    private ChoiceBox<String> choiceBox;
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("Search");
        
        Button rate = new Button();
        rate.setText("Rate");
        dbComm = null;
        try{
            dbComm = new DatabaseCommunicator();
        }catch(Exception E){
            System.out.println(E);
        }
        
        txt = new TextField("Enter search word");
        listView = new ListView<>();
        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Artist", "Title", "Genre", "Rating");
        choiceBox.setTooltip(new Tooltip("Search by"));
        choiceBox.getSelectionModel().selectFirst();
        
        btn.setOnAction(new EventHandler<ActionEvent>() {  
            @Override
            public void handle(ActionEvent event) {
                sendSearch();
            }
        });
        
        Button add = new Button();
        add.setText("Add CD");
        
        
        
        add.setOnAction(e -> {
            ComfirmBox.display("Title of Window", "Add Section");               
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
        
        
        
        
        
        
        //NYTT
        
 
        borderPane.setCenter(listView);
        statusbar2.getChildren().addAll(add,rate);
        statusbar.getChildren().addAll(btn,txt,choiceBox);
        Scene scene = new Scene(borderPane, 768, 512);
        
        
        Company theCompany = new Company();     
        
       
        
        //MusicAlbum m = new MusicAlbum("Torgnys stora äventyr","30-01-12","Adventure");
        //m.addArtist(new Artist("Jonas Fiskmås"));
        //dbComm.newAlbumRequest(m);
        
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        launch(args);
    }
    
    private void sendSearch(){
        listView.getItems().clear();
        ArrayList<MusicAlbum> tempMusicAlbum = new ArrayList<>();
        System.out.println((String)choiceBox.getSelectionModel().getSelectedItem());
        System.out.println(txt.getText());
        tempMusicAlbum = dbComm.searchRequest(txt.getText(),(String)choiceBox.getSelectionModel().getSelectedItem());
        if(tempMusicAlbum!=null)
        for(MusicAlbum ma : tempMusicAlbum){
            if(ma!=null)
                listView.getItems().add(ma.toString());
        }
    }
}
