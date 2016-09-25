package lab3b2;
import java.util.*;
import java.io.*;

/**
 * A CollectionOfBooks is an object that contains a list of books.
 * @author Jakob Danielsson, Michael Hjälmö
 */
public class CollectionOfBooks implements Serializable{
    private ArrayList<Book> theBooks;
    
    /**
     * Create an empty book list.
     */
    public CollectionOfBooks(){
        theBooks = new ArrayList<Book>();
    }
    
    /**
     * Add a book to the book list.
     * @param book The book to add
     */
    public void addBook(Book book){
        theBooks.add(book);
    }
    
    /**
     * Remove a book from the list.
     * @param index
     * @return value if succeeded or not
     */
    public int removeBook(int index){
        --index;
        if(index < 0 || index >= theBooks.size()){
            return 1;
        }
        theBooks.remove(index);
        return 0;
    }
    
    /**
     * Get the book list.
     * @return a copy of the book list
     */
    public ArrayList<Book> getBooks(){
        return  (ArrayList<Book>) theBooks.clone();
    }
    
    /**
     * Search for books by title.
     * @param string The keyword to search for
     * @return a sorted list of matching results.
     */
    public ArrayList<Book> searchTitle(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        for(Book b : theBooks){
            if(b.getTitle().contains(string)){
                list.add(b);
            }
        }
        sortByTitle(list);
        return list;
    }
    
    /**
     * Sort a book list by book title.
     * @param list The list to sort
     */
    private void sortByTitle(ArrayList<Book> list){
        
        Collections.sort(list,new Comparator<Book>(){
            /**
             * Compares the title of two book objects.
             * @param b1 The first book
             * @param b2 The second book
             * @return return value to determine how to sort the books.
             */
            @Override
            public int compare(Book b1, Book b2) {
                int returnValue;
                returnValue = b1.getTitle().compareTo(b2.getTitle());
                if( returnValue == 0){ //If titles are the same, sort by author
                    return compareAuthors(b1.getTheAuthors(),b2.getTheAuthors());
                }
                else return returnValue;
            }
        });
    }
    
    /**
     * Search for books by author.
     * @param string The keyword to search for
     * @return a sorted list of matching results.
     */
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
        sortByAuthor(list);
        return list;
    }
    
    /**
     * Sort a book list by author.
     * @param list The list to sort.
     */
    private void sortByAuthor(ArrayList<Book> list){
        Collections.sort(list, new Comparator<Book>(){
            /**
             * Compares the authors of two book objects.
             * @param b1 The first book
             * @param b2 The second book
             * @return return value to determine how to sort the books.
             */
           @Override
           public int compare(Book b1, Book b2) {
               return compareAuthors(b1.getTheAuthors(),b2.getTheAuthors());
           }
        });
    }
    
    /**
     * Compares two lists of authors to determine how to sort them.
     * @param a1 The first list of authors.
     * @param a2 The second list of authors.
     * @return a return value to determine how to sort the two lists.
     */
    private int compareAuthors(ArrayList<Author> a1,ArrayList<Author> a2){
        int i = 0;
        int returnValue;
        for(Author a : a1){
            if(a2.get(i)==null){
                return 1;
            }
            if((returnValue = a.getName().compareTo(a2.get(i).getName()))!=0){
                return returnValue;
            }
            i++;
        }
        if(a2.size()>a1.size())
            return -1;
        return 0;
    }
    
    /**
     * Search for books by ISBN.
     * @param string The keyword to search for
     * @return a sorted list of matching results.
     */
    public ArrayList<Book> searchISBN(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        for(Book b : theBooks){
            if(b.getIsbn().contains(string)){
                list.add(b);
            }
        }
        sortByISBN(list);
        return list;
    }
    
    /**
     * Sort a book list by ISBN.
     * @param list The list to sort
     */
    private void sortByISBN(ArrayList<Book> list){
        Collections.sort(list, new Comparator<Book>(){
            /**
             * Compares the ISBN of two book objects.
             * @param b1 The first book
             * @param b2 The second book
             * @return return value to determine how to sort the books.
             */
           @Override
           public int compare(Book b1,Book b2){
               return b1.getIsbn().compareTo(b2.getIsbn());
           }
        });
    }
    
    /**
     * Search for books by edition.
     * @param string The keyword to search for.
     * @return a list of matching results.
     */
    public ArrayList<Book> searchEdition(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        
        return list;
    }
    
    /**
     * Search for books by price.
     * @param string The keyword to search for.
     * @return a list of matching results.
     */
    public ArrayList<Book> searchPrice(String string){
        ArrayList<Book> list = new ArrayList<Book>();
        
        return list;
    }
    
    /**
     * Get a book from the book list, by index.
     * @param index The index of the wanted book
     * @return the wanted book
     */
    public Book getBook(int index){
        Book bookToReturn = theBooks.get(index);
        return bookToReturn;
    }
    
    /**
     * Get a list of all books in the book list
     * @return list of all books
     */
    @Override
    public String toString(){
        String info = new String();
        int i = 1;
        for(Book b : theBooks){
            info += "===========" + i + b.toString();
            i++;
        }
        return info;
    }
    
}
