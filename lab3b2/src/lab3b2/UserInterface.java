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
    

    private void remove(){
        if(theCollection.removeBook(getIndex())==1){
            System.out.println("Could not remove. No book at specified index.");
        }
    }
        
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
    
    private void printTable(){
        System.out.print(theCollection.toString());
    }

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
    
    public void printSearchMenu(){
        System.out.println("====SEARCH MENU====");
        System.out.println("A: Search Author");
        System.out.println("B: Search ISBN");
        System.out.println("C: Search Title");
        System.out.println("X: Main menu");
        System.out.println("===================");
    }
    
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
    
    private String keyWord(){
        System.out.println("Enter keyword: ");
        String keyword = theScanner.nextLine();
        return keyword;
    }

    private void printMenu() {
        System.out.println("====MENU====");
        System.out.println("A: Add book");
        System.out.println("B: Remove Book");
        System.out.println("C: Search book");
        System.out.println("D: Print book list");
        System.out.println("X: Exit");
        System.out.println("============");
    }
    
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
    
    public static void printList(ArrayList<Book> theList){
        String str = new String();
        for(Book b : theList){
            str += b.toString();
        }
        
        System.out.println(str);
    }

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
