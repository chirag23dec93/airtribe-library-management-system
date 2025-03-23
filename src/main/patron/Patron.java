package patron;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Notifications.PatronObserver;
import library.Library;
import library.LibraryBranch;
import libraryBooks.Book;

// Patron Class
public class Patron {
    private String name;
    private Set<String> borrowedBooks = new HashSet<>();
    private List<Book> borrowingHistory = new ArrayList<>();
    private String favoriteGenre;
    private PatronObserver observer;
    private int borrowingCount;
    
    public Patron(String name, String favoriteGenre) {
        this.name = name;
        this.favoriteGenre = favoriteGenre;
        this.observer = new PatronObserver(name);
    }
    
    public void borrowBook(Book book,Library library) {
        if(borrowingCount < 2){
            borrowedBooks.add(book.getISBN());
            book.borrowBook(this,library);
            borrowingCount++;
        }
        else 
            System.out.println(this.getName() + " has alread borrowed 2 books. To issue new book kindly return atleast 1 book.");
    }

   public void returnBook(Book book , LibraryBranch branch ,Library library){
    if(!borrowedBooks.isEmpty()){
        book.returnBook(library,branch);
        borrowedBooks.remove(book.getISBN());
       }
    else {
        System.out.println(this.getName() + " has not borrowed book " + book.getTitle());
    }
   }
        
    public void notifyObserver(String message) {
        observer.update(message);
    }

        
    public void addToHistory(Book book) {
        borrowingHistory.add(book);
    }
    
    public List<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    public Set<String> getBorrowedBooks() { return borrowedBooks; }
    public String getFavoriteGenre() { return favoriteGenre; }
    public String getName() { return name; }
}