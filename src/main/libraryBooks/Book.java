package libraryBooks;
import java.util.Queue;

import library.Library;
import library.LibraryBranch;
import patron.Patron;

// Book Class
public class Book {
    private String title;
    private String author;
    private String ISBN;
    private int publicationYear;
    private boolean isAvailable;
    private double rating;
    private String genre;
    private int borrowCount;
    private int ratingCount;
    
    public Book(String title, String author, String ISBN, int publicationYear, String genre) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
        this.rating = 0.0;
        this.genre = genre;
        this.borrowCount = 0;
        this.ratingCount = 0;
    }

    public void rateBook(int rating) {
        this.rating = ((this.rating * this.ratingCount) + rating) / (++this.ratingCount);
    }
    public void borrowBook(Patron patron, Library library) {
        Queue<Patron> queue = library.getReservationQueue(this);
        if (isAvailable && queue.isEmpty()) {
            isAvailable = false;
            borrowCount++;
            System.out.println(patron.getName() + " borrowed " + title);
            patron.addToHistory(this);
        } else {
            System.out.println(title + " is currently unavailable. Adding " + patron.getName() + " to the reservation queue.");
            queue.add(patron);
        }
    }
    // next patron checksout the book automatically once the book he was waiting for is available
    //this method takes care of on which branch patron returns the book
    public void returnBook(Library library,LibraryBranch branch) {
        isAvailable = true;
        Queue<Patron> queue = library.getReservationQueue(this);
        if (!queue.isEmpty()) {
            Patron nextPatron = queue.poll();
            nextPatron.notifyObserver("The book '" + title + "' you reserved is now available at the branch " + branch.getName());
            borrowBook(nextPatron, library);
        }
    }
    
    
    public boolean isAvailable() { return isAvailable; }
    public void incrementBorrowCount() { this.borrowCount++; }
    public int getBorrowCount() { return borrowCount; }
    public String getGenre() { return genre; }
    public int getPublicationYear() { return publicationYear; }
    public double getRating() { return rating; }
    public String getTitle() { return title; }
    public String getISBN() { return ISBN; }
    public String getAuthor() {return author; }
}