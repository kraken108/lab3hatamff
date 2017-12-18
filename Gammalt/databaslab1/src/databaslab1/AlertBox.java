/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaslab1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Stage;

/**
 *
<<<<<<< HEAD
* @author Jakob Danielsson & Michael Hjälmö
=======
 * @author Jakob Danielsson & Michael Hjälmö
>>>>>>> 8c1acd9aabb1b0de6387e57a5e51cc8858d26309
 */
public class AlertBox {
    
    public static void display(String title, String message){
        Stage window = new Stage();
        
        window.initModality(APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
}