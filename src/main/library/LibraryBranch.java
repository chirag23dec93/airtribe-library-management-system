package library;

import java.util.HashMap;
import java.util.Map;
import libraryBooks.Book;
import patron.Patron;

// Library Branch Class
public class LibraryBranch {
    private String name;
    private Map<String, Book> books = new HashMap<>(); // books are mapped to their isbn
    private Map<String, Patron> patrons = new HashMap<>(); 
    
    public LibraryBranch(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
    public Map<String, Book> getBooks() { return books; }
    public Map<String, Patron> getPatrons() { return patrons; }
    
    public void addBook(Book book) {
        books.put(book.getISBN(), book);
    }
    
    public void transferBook(String ISBN, LibraryBranch targetBranch) {
        if (books.containsKey(ISBN)) {
            Book book = books.remove(ISBN);
            targetBranch.addBook(book);
            System.out.println("Book " + book.getTitle() + " transferred from " + name + " to " + targetBranch.getName());
        }
    }
    
}
