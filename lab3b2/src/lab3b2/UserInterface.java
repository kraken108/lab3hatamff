package lab3b2;

import java.util.*;
import java.io.*;

public class UserInterface {

    private CollectionOfBooks theCollection;
    private Scanner theScanner;

    public UserInterface() {
        theScanner = new Scanner(System.in);
        theCollection = new CollectionOfBooks();
    }
    
    public void menu() {
        char choice;
        
        boolean running = true;
        while (running) {
            printMenu();
            switch (getChoice()) {
                case 'A': theCollection.addBook(addBook()); break;
                case 'B': theCollection.removeBook(getIndex()); break;
                case 'C': searchWhat(); break;
                case 'D': printTable(); break;
                case 'E': break;
                case 'X': running = false; break;
            }
        }
    }
    
    private void printTable(){
        System.out.println(theCollection.toString());
    }
    
    private void searchWhat(){
        char choice;
        printSearchMenu();
        switch(getChoice()){
            case 'A': printList(theCollection.searchAuthor(keyWord())); break;
            case 'B': printList(theCollection.searchISBN(keyWord())); break;
        }
    }
    
    private String keyWord(){
        System.out.println("Enter keyword");
        String author = theScanner.nextLine();
        return author;           
    }
        
        
    
    
    public void printSearchMenu(){
        System.out.println("====SEARCH-MENU====");
        System.out.println("A: Search Author");
        System.out.println("B: Search ISBN");
        System.out.println("C: Search Title");
        System.out.println("D: Search Price");
        System.out.println("E: Search Edition");
        System.out.println("X: Main menu");
        System.out.println("============");
    
    
    }
    

    private char getChoice() {
        String answer;
        char choice;
        answer = theScanner.nextLine();
        answer = answer.toUpperCase();
        choice = answer.charAt(0);
        return choice;
    }

    private void printMenu() {
        System.out.println("====MENU====");
        System.out.println("A: Add book");
        System.out.println("B: Remove Book");
        System.out.println("C: Search book");
        System.out.println("D: Print book list");
        System.out.println("E: Add author");
        System.out.println("X: Exit");
        System.out.println("============");
    }
    
    public int getIndex(){
        int index;
        index = theScanner.nextInt();
        return index;
        
    }
    
    private Book addBook(){
        
        System.out.println("New book.");
        System.out.println("ISBN: ");
        String isbn = theScanner.nextLine();
        System.out.println("Book title: ");
        String title = theScanner.nextLine();
        System.out.println("Edition: ");
        int edition = theScanner.nextInt();
        System.out.println("Price: ");
        double price = theScanner.nextDouble();
        String dump = theScanner.nextLine();
        System.out.println("Author: ");
        String author = theScanner.next();
        
        Book b = new Book(isbn,title,edition,price,author);
        return b;
    }
    
        public static void printList(ArrayList<Book> theList){
        String str = new String();
        for(Book b : theList){
            str += b.toString();
        }        
    }
            
        private void createFile(String fileName){
            
            FileOutputStream fout = null;

		// Serialize to file
	    try {
	      fout = new FileOutputStream("cards.ser");
	      ObjectOutputStream ois = new ObjectOutputStream(fout);
	      
              theCollection = (CollectionOfBooks) ois.readObject();
	      
	      
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
	public void deSerializeFromFile(String filename) throws IOException, 
                ClassNotFoundException {
		
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(
			new FileInputStream(filename));
			// readObject returns a reference of type Object, hence the down-cast
			theCollection = (CollectionOfBooks) in.readObject(); 
		}
		finally {
			try {
				if(in != null)	in.close();
			} catch(Exception e) {}			
		}
	}
        
        
}
