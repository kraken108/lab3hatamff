package lab3b2.src.lab3b2;

import java.util.*;
import java.io.*;
/**
 * 
 * @author Jakob & Michael
 */
public class UserInterface implements Serializable{

    private CollectionOfBooks theCollection;
    private Scanner theScanner;

    public UserInterface() {
        theScanner = new Scanner(System.in, "Cp850");
        theCollection = new CollectionOfBooks();
    }
    
    /**
     * Checks if removeBook function in CollectionOfBooks class returned 1
     * If so, a message is printed that the book at index could not be removed
     */
    private void remove(){
        if(theCollection.removeBook(getIndex())==1){
            System.out.println("Could not remove. No book at specified index.");
        }
    }
    
    /**
    * Calls printMenu method and gets input from user about which option to 
    * execute. 
    */
    public void menu(){
        char choice;
        boolean running = true;
        createFile();
        while (running) {
            printMenu();
            switch (getChoice()) {
                case 'A': theCollection.addBook(addBook()); break;
                case 'B': printTable();remove();break;
                
                case 'C': searchWhat();break;
                case 'D': printTable();break;
                case 'X': running = false; break;
                default: break;
            }
        }
        writeToFile();
    }
    
    /**
     * Prints all the books in the program with their specified
     * index by calling the toString function in CollectionOfBooks class. 
     */   
    private void printTable(){
        System.out.print(theCollection.toString());
    }
    
    /**
     * A function that gets the users input. If-statement makes sure to catch
     * enter dump from user so the program doesn't fail.
     * @return returns the character that the user indicted.
     */
    private char getChoice() {
        String answer;
        char choice;
        answer = theScanner.nextLine();
        answer = answer.toUpperCase();
        if(answer.length()>0)
            choice = answer.charAt(0);
        else choice = 0;
        
        return choice;
    }
    
    /**
     * Prints the searchmenu if the user chooses to search for a book.
     */
    public void printSearchMenu(){
        System.out.println("====SEARCH MENU====");
        System.out.println("A: Search Author");
        System.out.println("B: Search ISBN");
        System.out.println("C: Search Title");
        System.out.println("X: Main menu");
        System.out.println("===================");
    }
    
    /**
     *Depending on user input, the method searches for the books based on 
     * Author, ISBN or Title.
     */
    private void searchWhat(){
        char choice;
        printSearchMenu();
        switch (getChoice()) {
                case 'A': printList(theCollection.searchAuthor(keyWord())); break;
                case 'B': printList(theCollection.searchISBN(keyWord())); break;
                case 'C': printList(theCollection.searchTitle(keyWord())); break;
                case 'X': break;
                default: break;
            }
    }
    
    /**
     * Takes a keyword from the user and scans it.
     * @return the keyword is returned.
     */
    private String keyWord(){
        System.out.println("Enter keyword: ");
        String keyword = theScanner.nextLine();
        return keyword;
    }
    
    /**
     * Prints the main menu
     */
    private void printMenu() {
        System.out.println("====MENU====");
        System.out.println("A: Add book");
        System.out.println("B: Remove Book");
        System.out.println("C: Search book");
        System.out.println("D: Print book list");
        System.out.println("X: Exit");
        System.out.println("============");
    }
    
    /**
     * Gets the index of the book that the user wants to remove.
     * if-statement is used to catch enter dump so the program doesn't fail.
     * @return the index is returned.
     */
    public int getIndex(){
        int index;
        System.out.println("Which book do you want to remove? (Type index)");
        String input = theScanner.nextLine();
        if(input.length()>0){
            index = Integer.parseInt(input);
        }
        else index = 0;
        return index;
        
    }
    
    /**
     * Takes all the parameters from the user necessary to create a book
     * Allows the user to add more than one author and sorts them by calling
     * the methods.
     * @return the Book created is returned.
     */
    private Book addBook(){
        int edition;
        double price;
        System.out.println("New book.");
        
        System.out.println("ISBN: ");
        String isbn = theScanner.nextLine();
        
        System.out.println("Book title: ");
        String title = theScanner.nextLine();
        
        System.out.println("Edition: ");
        String input = theScanner.nextLine();
        if(input.length()>0){
            edition = Integer.parseInt(input);
        } else edition = 0;
        
        System.out.println("Price: ");
        input = theScanner.nextLine();
        if(input.length()>0)price = Double.parseDouble(input);
        else price = 0;
        
        System.out.println("Author: ");
        String author = theScanner.nextLine();
        System.out.println(author);
        
        Book b = new Book(isbn,title,edition,price,author);
        moreAuthors(b);
        b.sortAuthors();
        return b;
    }
    
    /**
     * While the user wants to add authors, the program keeps running and gets
     * user input to create them. 
     * @param b Takes a book as parameter to which the authors are added.
     */
    private void moreAuthors(Book b){
        
        Boolean running = true;
        while(running){
            System.out.println("Do you want to add more authors? Y for yes");
            switch(getChoice()){
            case 'Y': b.addAuthor(new Author(keyWord())); break;
            default: running = false;
            }
        }
        
        
    }
    
    /**
     * Prints the list of books
     * @param theList takes a list of Books as parameter 
     */
    public static void printList(ArrayList<Book> theList){
        String str = new String();
        for(Book b : theList){
            str += b.toString();
        }
        
        System.out.println(str);
    }
    
    /**
     * Takes users input and writes to file using serialization.
     */
    public void writeToFile(){
	    
	    
	    FileOutputStream fout = null;

		// Serialize to file
	    try {
	      fout = new FileOutputStream("thecollection.ser");
	      ObjectOutputStream ous = new ObjectOutputStream(fout);
	      
	      ous.writeObject((ArrayList<Book>)theCollection.getBooks());
	      
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
    /**
     * Creates the file.
     */
    @SuppressWarnings("unchecked")
    private void createFile(){
        FileInputStream fin = null;
	  	
	    try {
	    	
	      fin = new FileInputStream("thecollection.ser");
	      ObjectInputStream ois = new ObjectInputStream(fin);
	      
	      ArrayList<Book> bookList = (ArrayList<Book>) ois.readObject(); // Downcast from Object
	      
	      System.out.println("Deserializing successfully completed");
	      for(Book b: bookList) {
	    	  theCollection.addBook(b);
	      }
	      
	    }
	    catch (IOException e) {
	      System.out.println(e);
	    }
	    catch (ClassNotFoundException e) { // !!!
	      System.out.println("Klassen fÃ¶r objekten pÃ¥ filen Ã¤r inte kÃ¤nd!");
	    }
	    finally {
			try {
				if(fin != null) fin.close();
			}
			catch(IOException e) {}
	    }
    }  

}
