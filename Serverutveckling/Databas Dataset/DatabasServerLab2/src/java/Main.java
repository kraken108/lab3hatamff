
import BO.DatasetHandler;
import Model.DatasetValues;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class Main {
 
    
    public static void main(String[] args){
        
        DatasetValues dv = new DatasetValues(3,5);
        DatasetHandler handler = new DatasetHandler();
        try {
            handler.createNewDataset(dv.getX(), dv.getY());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
