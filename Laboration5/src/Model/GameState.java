/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author micke1
 */
public enum GameState implements Serializable{
    MENU, RUNNING, GAMEOVER,PAUSED
}
