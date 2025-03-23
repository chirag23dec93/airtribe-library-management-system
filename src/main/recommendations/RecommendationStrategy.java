package recommendations;

import java.util.List;
import library.LibraryBranch;
import libraryBooks.Book;
import patron.Patron;

// Strategy Pattern for Recommendations
public interface RecommendationStrategy {
    List<Book> recommend(LibraryBranch libraryBranch, Patron patron);
}