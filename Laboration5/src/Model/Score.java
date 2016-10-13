/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Jakob
 */
public class Score implements Serializable{
    private Text scoreBoard;
    
    public Score(){
        scoreBoard = new Text(20,500,"Testscoreboard");
    }
    
    public void paintScore(){
        TextFlow textFlow = new TextFlow();
        textFlow.setLayoutX(40);
        textFlow.setLayoutY(40);
        textFlow.getChildren().addAll(scoreBoard);
        scoreBoard.setFill(Color.BLACK);
    }
    //private textField
}
