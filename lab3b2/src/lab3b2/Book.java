
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3b2.src.lab3b2;


import java.util.*;
import java.io.Serializable;

/**
 * A book is an object that represents a book with a title, author, ISBN, price
 * and edition.
 * @author Jakob Danielsson, Michael Hjälmö
 */
public class Book implements Serializable{

    private ArrayList<Author> theAuthors;
    private String isbn;
    private String title;
    private int edition;
    private double price;

    /**
     * The general class constructor.
     * @param isbn The books ISBN
     * @param title The title of the book
     * @param edition The books edition
     * @param price The price of the book
     * @param author The books author
     */
    public Book(String isbn, String title, int edition, double price,String author) {
        theAuthors = new ArrayList<Author>();
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
        this.price = price;
        theAuthors.add(new Author(author));
    }

    /**
     * Empty parameter constructor creating default names and values.
     */
    public Book() {
        theAuthors = new ArrayList<Author>();
        isbn = "0";
        title = "Unknown";
        edition = 0;
        price = 0;
    }
   
    /**
     * Sorts the authors of the book.
     */
    public void sortAuthors() {
        Collections.sort(theAuthors, new Comparator<Author>() {
            /**
             * Compares the name of two author objects.
             * @param a1 First author
             * @param a2 Second author
             * @return Value to determine how to sort the authors.
             */
            @Override
            public int compare(Author a1, Author a2) {
                return a1.getName().compareTo(a2.getName());
            }
        });

    }

    /**
     * Add an author to the book.
     * @param name The name of the author to add
     */
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


    /**
     * Get a table of the book displaying the name, author, ISBN
     * , edition and price.
     * @return A string of info of the book.
     */
    @Override
    public String toString() {
        String info = "";
        info += "===========\n";
        info += "Book title: " + title + " ISBN: " + isbn + " Edition: " + edition
                + " Price: " + price + ".\nAuthors: ";
        for (Author a : theAuthors) {
            info += a.getName();
            info += ", ";
        }
        info += "\n";
        return info;
    }

}
