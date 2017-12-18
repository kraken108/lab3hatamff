/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Jakob
 */
public enum PlayerState implements Serializable{
    ALIVE, DEAD, SPAWNING, DYING
}
