/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;

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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jakob
 */
public class Databaslab1 extends Application{
    
    Stage window;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Main Menu");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(3);
        grid.setHgap(3);
        
        Button search = new Button("Search");
        GridPane.setConstraints(search, 0, 0);
        
        TextField nameInput = new TextField("Enter input here..");
        GridPane.setConstraints(nameInput, 15, 0);
        
        Label addLabel = new Label("Add");
        GridPane.setConstraints(addLabel, 0, 35);
        
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        GridPane.setConstraints(choiceBox, 25, 0);
        choiceBox.getItems().addAll("Author", "CD", "Panrike");
        
        
        
        TextField passInput = new TextField();
        passInput.setPromptText("To add");
        GridPane.setConstraints(passInput, 1, 35);
        
        grid.getChildren().addAll(search, nameInput, addLabel, passInput, choiceBox);
        
        Scene scene = new Scene(grid,300,200);
        window.setScene(scene);        
        window.show();
      
    }
    
    
    



}
