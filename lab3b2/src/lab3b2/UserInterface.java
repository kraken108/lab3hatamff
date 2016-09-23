package lab3b2;

import java.util.*;

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
                case 'C': break;
                case 'D': theCollection.toString(); break;
                case 'E': break;
                case 'X': running = false; break;
            }
        }
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
        System.out.println("Author: ");
        String author = theScanner.next();
        
        Book b = new Book(isbn,title,edition,price,author);
        return b;
    }

}
