package lab3b2;
import java.io.*;
/**
 * An author is an object that helps a book object set and add several authors.
 * @author Jakob Danielsson, Michael Hjälmö
 */
public class Author implements Serializable{
    
    private String name; //author name
    
    /**
     * Empty constructor creating a default name for the author.
     */
    public Author(){
        name="Unknown";
    }
    /**
     * The general class constructor.
     * @param name The name of the author
     */
    public Author(String name){
        this.name=name;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
   
    
    
    
}
