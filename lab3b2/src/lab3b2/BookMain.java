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
public class BookMain {
    
    public static void main(String[] args){
        
        UserInterface ui = new UserInterface();
        ui.menu();
        
        Book b1 = new Book();

        Author a1 = new Author("Linders Andström");
        b1.addAuthor(a1);
        b1.addAuthor(new Author("Ponny Janrike"));
        b1.addAuthor(new Author("Brandlas Nickefeldt"));
        System.out.println(b1.toString());
        b1.sortAuthors();
        System.out.println("===========================");
        System.out.println("COLLECTION TEST:");
        
        CollectionOfBooks theCollection = new CollectionOfBooks();
        theCollection.addBook(new Book("23190315","Perry Hogger and the Frogger",1,299,"Linders Stenman"));
        theCollection.addBook(new Book("19283182","Peter Crouchs fantastic Banana Head",3,499,"Gustav Gelin"));
        theCollection.addBook(new Book("91497123","Ponny Janrike om livet som homosexuell",5,199,"Ivan Turina"));
        theCollection.addBook(new Book("51828413","Jonas Wåhslén om livet som brödbakare",1,1299,"Gilla flibbens bajenfilmer"));
        System.out.println(theCollection.toString());
        System.out.println("Title Search:");
        printList(theCollection.searchTitle("livet"));
        System.out.println("Author search:");
        printList(theCollection.searchAuthor("Li"));
        theCollection.removeBook(4);
        System.out.println("Remove test (flibben):");
        System.out.println(theCollection.toString());
        
        
    }
    
    public static void printList(ArrayList<Book> theList){
        String str = new String();
        for(Book b : theList){
            str += b.toString();
        }
        
        System.out.println(str);
        
        
    }
}
