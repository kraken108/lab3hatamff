/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3b2;
import java.util.*;
/**
 *
 * @author micke1
 */


        
public class CollectionOfBooks {
    
    private ArrayList<Book> theBooks;
    
    
    public CollectionOfBooks(){
        
        theBooks = new ArrayList<Book>();
        
        
    }
        
    public void addBook(Book book){
        theBooks.add(book);
    }
    
    public ArrayList<Book> getBooksByTitle(String title){
        
        
    }
    
    public void removeBook(int index){
       theBooks.remove(index);        
    }
    
    
    
}
