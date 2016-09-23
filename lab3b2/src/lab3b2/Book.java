/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3b;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 *
 * @author micke1
 */
public class Book implements Comparable<Book> {
    
    private ArrayList<Author> theAuthors;
    private String isbn;
    private String title;
    private int edition;
    private double price;
    
    public Book(String isbn, String title, int edition, double price){
        theAuthors = new ArrayList<Author>();
        this.isbn=isbn;
        this.title=title;
        this.edition=edition;
        this.price=price;
    }
    
    public Book(){
        theAuthors = new ArrayList<Author>();
        isbn="1234567891011";
        title="Anders Lindström ockh the secret chamber of fire";
        edition=1;
        price=10;
    }
        
    public void sortAuthors(ArrayList<Author> theAuthors){
        Collections.sort(theAuthors);
    }
    
    public void addAuthor(Author name){
        theAuthors.add(name);
    }
    
    public ArrayList<Author> getAuthors(){
        return getTheAuthors();
    }

    /**
     * @return the theAuthors
     */
    public ArrayList<Author> getTheAuthors() {
        return theAuthors;
    }

    /**
     * @param theAuthors the theAuthors to set
     */
    public void setTheAuthors(ArrayList<Author> theAuthors) {
        this.theAuthors = theAuthors;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the edition
     */
    public int getEdition() {
        return edition;
    }

    /**
     * @param edition the edition to set
     */
    public void setEdition(int edition) {
        this.edition = edition;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Book other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        String info="";
        for(int i=0; i<theAuthors.size(); i++){
            info+=theAuthors.get(i);
        }
        return info;
    }
    
    
}
