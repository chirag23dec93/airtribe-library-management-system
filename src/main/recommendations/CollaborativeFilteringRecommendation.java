package recommendations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import library.LibraryBranch;
import libraryBooks.Book;
import patron.Patron;

// Collaborative Filtering: Recommend books based on similar patrons' history
class CollaborativeFilteringRecommendation implements RecommendationStrategy {
    @Override
    public List<Book> recommend(LibraryBranch libraryBranch, Patron patron) {
        Set<String> borrowedBooks = new HashSet<>(patron.getBorrowedBooks());
        Map<Book, Integer> bookFrequency = new HashMap<>();
        
        // get frequency of all books which have not been borrowed by patron 
        for (Patron other : libraryBranch.getPatrons().values()) {
            if (!other.equals(patron)) {
                for (String isbn : other.getBorrowedBooks()) {
                    if (!borrowedBooks.contains(isbn)) {
                        Book book = libraryBranch.getBooks().get(isbn);
                        bookFrequency.put(book, bookFrequency.getOrDefault(book, 0) + 1);
                    }
                }
            }
        }
        
        List<Book> recommendations = new ArrayList<>(bookFrequency.keySet());
        recommendations.sort((b1, b2) -> bookFrequency.get(b2) - bookFrequency.get(b1));
        return recommendations.subList(0, Math.min(recommendations.size(), 5));
    }
}
