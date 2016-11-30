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

/**
 *
 * @author Jakob
 */
public class Databaslab1 extends Application{
    
    Stage window;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("Search");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hej");
            }
        });
        
        Button add = new Button();
        add.setText("Add CD");
        
        BorderPane borderPane = new BorderPane();
  
      
        HBox statusbar = new HBox();
        borderPane.setTop(statusbar);
        statusbar.setSpacing(10);
        statusbar.setPadding(new Insets(10, 0, 10, 10));
        
        HBox statusbar2 = new HBox();  
        statusbar2.setSpacing(10);
        borderPane.setBottom(statusbar2);
        statusbar2.setPadding(new Insets(10, 0, 10, 10));
        
        TextField txt = new TextField("Enter search word");
        
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Author", "Title", "Genre", "Rating");
        choiceBox.setTooltip(new Tooltip("Search by"));
        choiceBox.setAccessibleText("Hej");
        
        
        //NYTT
        
        ListView listView = new ListView<>();
        listView.getItems().addAll("IronMan", "Bacon", "Contact", "Ham", "Ham");
        
 
        borderPane.setCenter(listView);
        
        statusbar2.getChildren().addAll(add);
        statusbar.getChildren().addAll(btn,txt,choiceBox);
        Scene scene = new Scene(borderPane, 768, 512);
        
        
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
}
