/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3b2;
import java.util.*;
<<<<<<< HEAD
/**
 *
 * @author micke1
 */


        
public class CollectionOfBooks {
    
    private ArrayList<Book> theBooks;
    
    
    public CollectionOfBooks(){
        
        theBooks = new ArrayList<Book>();
        
        
    }
        
=======

/**
 *
 * @author Jakob
 */
public class CollectionOfBooks {
    private ArrayList<Book> theBooks;
    
    public CollectionOfBooks(){
        theBooks = new ArrayList<Book>();
    }
    
>>>>>>> ae40e4f40edf1c0cc7b6b6c069b4a53eef80061b
    public void addBook(Book book){
        theBooks.add(book);
    }
    
<<<<<<< HEAD
    public ArrayList<Book> getBooksByTitle(String title){
        
        
    }
    
    public void removeBook(int index){
       theBooks.remove(index);        
    }
    
    
=======
    public void removeBook(int index){
        index = index-1;
        theBooks.remove(index);
    }
    
    
    //public ArrayList<Book> getBooksByTitle(String title){
        
   // }
    
    public ArrayList<Book> searchTitle(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        for(Book b : theBooks){
            if(b.getTitle().contains(string)){
                list.add(b);
            }
        }
        return list;
    }
    
    public ArrayList<Book> searchAuthor(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        
        for(Book b : theBooks){
            ArrayList<Author> authors = b.getTheAuthors();
            for(Author a : authors){
                if(a.getName().contains(string)){
                    list.add(b);
                }
            }
        }
        return list;
    }
    
    public ArrayList<Book> searchISBN(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        
        return list;
    }
    
    public ArrayList<Book> searchEdition(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        
        return list;
    }
    
    public ArrayList<Book> searchPrice(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        
        return list;
    }
    
    public Book getBook(int index){
        Book bookToReturn = theBooks.get(index);
        return bookToReturn;
    }
    
    @Override
    public String toString(){
        String info = new String();
        int i = 1;
        for(Book b : theBooks){
            info += i + ". " + b.toString();
            i++;
        }
        return info;
    }
>>>>>>> ae40e4f40edf1c0cc7b6b6c069b4a53eef80061b
    
}
