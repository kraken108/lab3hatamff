/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class Director extends Person{
    
    private ArrayList<Film> theFilms;
    
    
    public Director(String SSN, String firstName, String lastName, String eMail, String phoneNumber) {
        super(SSN, firstName, lastName, eMail, phoneNumber);
    }
    
    
    
    
    
}
