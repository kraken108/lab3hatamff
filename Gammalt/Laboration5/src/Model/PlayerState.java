
package model;

import java.io.Serializable;

/**
 * PlayerState represents the state a player is in.
 * @author Jakob Danielsson & Michael Hjälmö
 */
public enum PlayerState implements Serializable{
    ALIVE, DEAD, SPAWNING, DYING
}
