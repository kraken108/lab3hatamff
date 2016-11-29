
package model;

import java.io.Serializable;

/**
 * GameState represents the state the game is in.
 * @author Jakob Danielsson, Michael Hjälmö
 */
public enum GameState implements Serializable{
    MENU, RUNNING, GAMEOVER,PAUSED
}
