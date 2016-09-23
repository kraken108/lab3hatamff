/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3b2;
<<<<<<< HEAD
import java.util.*;

=======

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
>>>>>>> ae40e4f40edf1c0cc7b6b6c069b4a53eef80061b

/**
 *
 * @author micke1
 */
public class Book {

    private ArrayList<Author> theAuthors;
    private String isbn;
    private String title;
    private int edition;
    private double price;
<<<<<<< HEAD
    
    
    public Book(String isbn, String title, int edition, double price){
=======

    public Book(String isbn, String title, int edition, double price,String author) {
>>>>>>> ae40e4f40edf1c0cc7b6b6c069b4a53eef80061b
        theAuthors = new ArrayList<Author>();
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
        this.price = price;
        theAuthors.add(new Author(author));
    }

    public Book() {
        theAuthors = new ArrayList<Author>();
<<<<<<< HEAD
        isbn="0";
        title="Unknown";
        edition=0;
        price=0;
    }
    
    
    
 
    
    public void sortAuthors(){
        Collections.sort(theAuthors, new Comparator<Author>(){
=======
        isbn = "0";
        title = "Unknown";
        edition = 0;
        price = 0;
    }

    public void sortAuthors() {
        Collections.sort(theAuthors, new Comparator<Author>() {
>>>>>>> ae40e4f40edf1c0cc7b6b6c069b4a53eef80061b
            @Override
            public int compare(Author a1, Author a2) {
                return a1.getName().compareTo(a2.getName());
            }
        });
<<<<<<< HEAD
                
=======

>>>>>>> ae40e4f40edf1c0cc7b6b6c069b4a53eef80061b
    }

    public void addAuthor(Author name) {
        theAuthors.add(name);
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

    //@Override
    //public int compareTo(Book other) {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // }
    @Override
<<<<<<< HEAD
    public int compareTo(Book other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        String info="";
        info = "Booktitle: " + title + " ISBN: " + isbn + " edition: "
                + edition + " price: " + price + ".\nAuthors: ";
        for(Author a : theAuthors){
            info += a.getName();
            info += ", ";
        }
        return info;        
=======
    public String toString() {
        String info = "";
        info += "======================\n";
        info += "Book title: " + title + " ISBN: " + isbn + " Edition: " + edition
                + " Price: " + price + ".\nAuthors: ";
        for (Author a : theAuthors) {
            info += a.getName();
            info += ", ";
        }
        info += "\n======================\n";
        return info;
>>>>>>> ae40e4f40edf1c0cc7b6b6c069b4a53eef80061b
    }

}
