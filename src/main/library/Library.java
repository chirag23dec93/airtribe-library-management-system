package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import libraryBooks.Book;
import patron.Patron;
// Library Class for Managing Books & Patrons
// each library contains multiple branches 
public class Library {
    private Map<String, LibraryBranch> branches = new HashMap<>();
     private Map<Book, Queue<Patron>> reservationQueues = new HashMap<>();
    
    public void addBranch(LibraryBranch branch) {
        branches.put(branch.getName(), branch);
    }
    
    public LibraryBranch getBranch(String name) {
        return branches.get(name);
    }

    // trending books from each of its branch collectively 
    public List<Book> getTrendingBooks() {
        List<Book> trending = new ArrayList<>();
        for (LibraryBranch branch : branches.values()) {
            trending.addAll(branch.getBooks().values());
        }
        trending.sort((b1, b2) -> Integer.compare(b2.getBorrowCount(), b1.getBorrowCount()));
        return trending.subList(0, Math.min(trending.size(), 5));
    }

    public Queue<Patron> getReservationQueue(Book book) {
        if(reservationQueues.get(book)==null){
        Queue<Patron> patronQueue  =  new LinkedList<Patron>();
        reservationQueues.putIfAbsent(book, patronQueue);
            return patronQueue;
        }
        else    
            return reservationQueues.get(book);
    }
}