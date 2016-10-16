package View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Game;

public class FileHandler {
    
    public FileHandler(){
        
    }
    public void saveFile(File file,Game game){
        FileOutputStream fout = null;  

	    try {
	      fout = new FileOutputStream(file);
	      ObjectOutputStream ous = new ObjectOutputStream(fout); 
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
    
    public Game loadFile(File file) throws ClassNotFoundException,IOException{
        Game game = null;
        FileInputStream fin = null;
        
        try {
	    	
	      fin = new FileInputStream(file);
	      ObjectInputStream ois = new ObjectInputStream(fin);
	      
	      game = (Game) ois.readObject();
	      
	      System.out.println("Deserializing successfully completed");
	    }
	    catch (IOException e) {
              game = null;
              throw new IOException("IO Exception");
	    }
	    catch (ClassNotFoundException e) {
              game = null;
              throw new ClassNotFoundException("Class not found");
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
