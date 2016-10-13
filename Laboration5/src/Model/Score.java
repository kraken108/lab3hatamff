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
    private int kills;
    private int deaths;
    
    public Score(){
        kills = 0;
        deaths = 0;
    }

    /**
     * @return the kills
     */
    public int getKills() {
        return kills;
    }

    /**
     * @param kills the kills to set
     */
    public void addKill(){
        kills++;
    }
    public void addDeath(){
        deaths++;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }

    /**
     * @return the deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * @param deaths the deaths to set
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    
    
    
}
