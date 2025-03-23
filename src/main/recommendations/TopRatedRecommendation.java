package recommendations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import library.LibraryBranch;
import libraryBooks.Book;
import patron.Patron;

class TopRatedRecommendation implements RecommendationStrategy {
    @Override
    public List<Book> recommend(LibraryBranch libraryBranch, Patron patron) {
        List<Book> books = new ArrayList<>(libraryBranch.getBooks().values());
        books.sort(Comparator.comparingDouble(Book::getRating).reversed());
        return books.subList(0, Math.min(books.size(), 5));
    }
}
