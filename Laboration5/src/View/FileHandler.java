/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Game;

/**
 *
 * @author Jakob
 */
public class FileHandler {
    
    public FileHandler(){
        
    }
    public void saveFile(File file,Game game){
        FileOutputStream fout = null;  
                
            // Serialize to file
	    try {
	      fout = new FileOutputStream(file);
	      ObjectOutputStream ous = new ObjectOutputStream(fout); 
              //is used for communication with the file
	      ous.writeObject(game);
	      
	      System.out.println("Serializing successfully completed");
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }
	    finally {
	    	try {
	    		if(fout != null) fout.close();
	    	}
	    	catch(IOException e) {}
	    }
    }
    
    public Game loadFile(File file){
        Game game = null;
        FileInputStream fin = null;
        
        try {
	    	
	      fin = new FileInputStream(file);
	      ObjectInputStream ois = new ObjectInputStream(fin);
	      
	      game = (Game) ois.readObject(); // Downcast from Object
	      
	      System.out.println("Deserializing successfully completed");
	    }
	    catch (IOException e) {
	      System.out.println(e);
              game = null;
	    }
	    catch (ClassNotFoundException e) { // !!!
	      System.out.println("Class not found");
              game = null;
	    }
	    finally {
			try {
				if(fin != null) fin.close();
			}
			catch(IOException e) {}
	    }
        return game;
    }
}
