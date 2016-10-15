/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;

/**
 *
 * @author Jakob
 */
public class Loader {
    
    public Loader(){
    }
    
    public void loadImageExit(String fileName,Image image) throws IllegalArgumentException{
        try{
             image = new Image(fileName);
        }catch(IllegalArgumentException i){
            errorAlert("Failed to load image: "+fileName+".\nTerminating program..");
            throw new IllegalArgumentException();
        }
    }
    private void errorAlert(String message){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Sorry");
        alerta.setContentText(message);
        alerta.showAndWait();
    }
}
