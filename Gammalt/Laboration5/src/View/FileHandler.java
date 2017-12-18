package View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.*;
/**
 * Handler for loading and saving files.
 * @author Jakob Danielsson & Michael Hjälmö
 */
public class FileHandler {
    
    public FileHandler(){
        
    }
    public void saveFile(File file,ArrayList<Player> thePlayers) throws Exception{
        FileOutputStream fout = null;  

	    try {
	      fout = new FileOutputStream(file);
	      ObjectOutputStream ous = new ObjectOutputStream(fout); 
	      ous.writeObject(thePlayers);
	    }
	    catch (Exception e) {
	      throw new Exception();
	    }
	    finally {
	    	try {
                    if(fout != null) fout.close();
	    	}
	    	catch(IOException e) {}
	    }
    }
    
    public ArrayList<Player> loadFile(File file) throws ClassNotFoundException,IOException{
        ArrayList<Player> thePlayers = null;
        FileInputStream fin = null;
        
        try {
	    	
	      fin = new FileInputStream(file);
	      ObjectInputStream ois = new ObjectInputStream(fin);
	      
	      thePlayers = (ArrayList<Player>) ois.readObject();
	      
	      System.out.println("Deserializing successfully completed");
	    }
	    catch (IOException e) {
              thePlayers = null;
              throw new IOException("IO Exception");
	    }
	    catch (ClassNotFoundException e) {
              thePlayers = null;
              throw new ClassNotFoundException("Class not found");
	    }
	    finally {
		try {
                    if(fin != null) fin.close();
		}
		catch(IOException e) {}
	    }
        return thePlayers;
    }
}
